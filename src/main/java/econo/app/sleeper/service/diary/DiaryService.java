package econo.app.sleeper.service.diary;

import econo.app.sleeper.domain.Diary;
import econo.app.sleeper.domain.Status;
import econo.app.sleeper.domain.User;
import econo.app.sleeper.repository.DiaryRepository;
import econo.app.sleeper.repository.UserRepository;
import econo.app.sleeper.service.character.CharacterService;
import econo.app.sleeper.service.user.UserService;
import econo.app.sleeper.util.DateManager;
import econo.app.sleeper.util.MoneyManager;
import econo.app.sleeper.util.SpeechBubbleJudgement;
import econo.app.sleeper.web.character.CharacterDto;
import econo.app.sleeper.web.diary.DiaryFindDto;
import econo.app.sleeper.web.diary.DiarySaveDto;
import econo.app.sleeper.web.diary.DiaryResponse;
import econo.app.sleeper.web.user.MoneyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final CharacterService characterService;

    @Transactional
    public DiaryResponse saveDiary(DiarySaveDto diarySaveDto){
        LocalDate savingDate = DateManager.checkSavingDate(diarySaveDto.getLocalDateTime());
        User user = userRepository.findById(diarySaveDto.getUserId()).get();
        Diary diary = diarySaveDto.toEntity(savingDate, diarySaveDto.getLocalDateTime(), user);
        diaryRepository.save(diary);
        MoneyDto moneyDto = MoneyDto.of(diarySaveDto.getContent(), diarySaveDto.getUserId());
        userService.updateMoney(moneyDto);
        CharacterDto characterDto = CharacterDto.of(diarySaveDto.getUserId(), SpeechBubbleJudgement.judgeSpeechBubble(diarySaveDto.getContent()), Status.SLEEP);
        characterService.updateCharacter(characterDto);
        return DiaryResponse.of(diary.getDiaryPk());
    }

    @Transactional
    public void updateDiary(Long diaryPk,String content) {
        Diary diary = diaryRepository.findByPk(diaryPk).get();
        diary.updateContent(content);
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
        return diaryRepository.findBetweenDate(user.getUserPk(), diaryFindDto.getLocalDate().withDayOfMonth(1),DateManager.giveEndDate(diaryFindDto.getLocalDate()));
    }

}
