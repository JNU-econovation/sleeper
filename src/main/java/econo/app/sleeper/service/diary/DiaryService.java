package econo.app.sleeper.service.diary;

import econo.app.sleeper.domain.diary.Diary;
import econo.app.sleeper.domain.character.Status;
import econo.app.sleeper.domain.user.User;
import econo.app.sleeper.repository.DiaryRepository;
import econo.app.sleeper.repository.UserRepository;
import econo.app.sleeper.domain.DateTimeManager;
import econo.app.sleeper.service.character.CharacterService;
import econo.app.sleeper.service.money.MoneyService;
import econo.app.sleeper.web.character.CharacterDto;
import econo.app.sleeper.web.diary.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final UserRepository userRepository;
    private final MoneyService moneyService;
    private final CharacterService characterService;

    @Transactional
    public void save(DiaryRequest diaryRequest){
        User user = userRepository.findById(diaryRequest.getUserId()).get();
        Diary diary = diaryRequest.toEntity(new DateTimeManager().giveSavingDate(), user);
        diaryRepository.save(diary);
        moneyService.obtain(DiaryRewardDto.of(diary.getContent().getContent(), user.getUserPk()));
        characterService.update(CharacterDto.of(user.getUserId(), diaryRequest.getContent()));
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

    public List<Diary> findDiariesByUser(String userId){
        User user = userRepository.findById(userId).get();
        return diaryRepository.findAllByPk(user.getUserPk());
    }

    public List<Diary> findDiariesByDate(DiaryFindDto diaryFindDto){
        User user = userRepository.findById(diaryFindDto.getUserId()).get();
        Long userPk = user.getUserPk();
        return diaryRepository.findByDate(userPk, diaryFindDto.getLocalDate());
    }

    public List<Diary> findDiariesBetWeenDates(DiaryFindDto diaryFindDto){
        User user = userRepository.findById(diaryFindDto.getUserId()).get();
        return diaryRepository.findBetweenDate(user.getUserPk(), diaryFindDto.getLocalDate().withDayOfMonth(1), DateTimeManager.giveEndDate(diaryFindDto.getLocalDate()));
    }



}
