package econo.app.sleeper.service.diary;

import econo.app.sleeper.domain.diary.Diary;
import econo.app.sleeper.domain.user.User;
import econo.app.sleeper.repository.DiaryRepository;
import econo.app.sleeper.repository.UserRepository;
import econo.app.sleeper.util.DateTimeManager;
import econo.app.sleeper.service.character.CharacterService;
import econo.app.sleeper.service.money.MoneyService;
import econo.app.sleeper.service.sleep.SleepService;
import econo.app.sleeper.web.character.CharacterDto;
import econo.app.sleeper.web.diary.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final UserRepository userRepository;
    private final MoneyService moneyService;
    private final CharacterService characterService;

    private final SleepService sleepService;

    @Transactional
    public Diary save(DiaryRequest diaryRequest){
        User user = userRepository.find(diaryRequest.getUserPk()).get();
        Diary diary = Diary.create(user, diaryRequest.getContent());
        diaryRepository.save(diary);
        sleepService.updateActualSleepTime(user.getUserPk());
        moneyService.obtain(DiaryRewardDto.of(diary.getContent().getContent(), user.getUserPk()));
        characterService.update(CharacterDto.of(user.getUserPk(), diaryRequest.getContent()));
        return diary;
    }

    @Transactional
    public void updateDiary(Long diaryPk,String content) {
        Diary diary = diaryRepository.findByPk(diaryPk).get();
        diary.update(content);
    }

    @Transactional
    public void deleteDiary(Long diaryPk){
        Diary diary = diaryRepository.findByPk(diaryPk).get();
        diaryRepository.delete(diary);
    }

    // 관리자에서 이용
    public Diary findDiary(Long diaryPk) {
        return diaryRepository.findByPk(diaryPk).orElseThrow(NullPointerException::new);
    }

    public List<Diary> findDiariesByUser(Long userPk){
        User user = userRepository.find(userPk).get();
        return diaryRepository.findAllByPk(user.getUserPk());
    }

    public List<Diary> findDiariesByDate(DiaryFindDto diaryFindDto){
        User user = userRepository.find(diaryFindDto.getUserPk()).get();
        Stream<Diary> diaryStream = user.getDiaries().stream()
                .filter(d -> d.getSavingDate().getSavingDate().isEqual(diaryFindDto.getLocalDate()));
        return diaryStream.collect(Collectors.toList());
    }

    public List<Diary> findDiariesBetWeenDates(DiaryFindDto diaryFindDto){
        User user = userRepository.find(diaryFindDto.getUserPk()).get();
        Stream<Diary> diaryStream = user.getDiaries().stream()
                .filter(d -> d.getSavingDate().getSavingDate().getMonth().equals(diaryFindDto.getLocalDate().getMonth()));
        return diaryStream.collect(Collectors.toList());
    }



}
