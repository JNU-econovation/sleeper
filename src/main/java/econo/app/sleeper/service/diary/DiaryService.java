package econo.app.sleeper.service.diary;

import econo.app.sleeper.domain.common.SavingDate;
import econo.app.sleeper.domain.diary.Diary;
import econo.app.sleeper.domain.user.User;
import econo.app.sleeper.exception.RestApiException;
import econo.app.sleeper.exception.error.CommonErrorCode;
import econo.app.sleeper.repository.DiaryRepository;
import econo.app.sleeper.repository.UserRepository;
import econo.app.sleeper.web.diary.*;
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

    @Transactional
    public Long save(DiaryRequest diaryRequest){
        User user = userRepository.find(diaryRequest.getUserPk())
                .orElseThrow(() -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND));
        Diary diary = Diary.create(user, diaryRequest.getContent());
        diaryRepository.save(diary);
        return diary.getId();
    }

    @Transactional
    public void updateDiary(Long diaryPk,String content) {
        Diary diary = diaryRepository.findByPk(diaryPk)
                .orElseThrow(() -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND));
        diary.getContent().update(content);
    }

    @Transactional
    public void deleteDiary(Long diaryPk){
        Diary diary = diaryRepository.findByPk(diaryPk)
                .orElseThrow(() -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND));
        diaryRepository.delete(diary);
    }

    public Diary findDiary(Long diaryPk) throws RestApiException{
        return diaryRepository.findByPk(diaryPk)
                .orElseThrow(() -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND));
    }

    public DiaryCheckDto giveIfDiaryExists(Long userPk){
        SavingDate savingDate = new SavingDate();
        LocalDate dateSavingDate = savingDate.getSavingDate();
        Optional<Diary> diaryByDate = diaryRepository.findDiaryByDate(userPk, dateSavingDate);
        if (diaryByDate.isPresent()){
            return DiaryCheckDto.of(diaryByDate.get().getId(),diaryByDate.get().getContent().getContent(),true);
        }
        return DiaryCheckDto.of(null,null,false);
    }


    public List<Diary> findDiariesByUser(Long userPk){
        User user = userRepository.find(userPk)
                .orElseThrow(() -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND));
        return diaryRepository.findAllByPk(user.getId());
    }
<<<<<<< HEAD

    public Diary findDiaryByDate(DiaryFindDto diaryFindDto){
        Diary diary = diaryRepository.findDiaryByDate(diaryFindDto.getUserPk(), diaryFindDto.getLocalDate())
                .orElseThrow(() -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND));
        return diary;
=======
    public DiaryCheckDto giveIfDiaryExists(Long userPk){
        SavingDate savingDate = new SavingDate();
        LocalDate dateSavingDate = savingDate.getSavingDate();
        Optional<Diary> diaryByDate = diaryRepository.findDiaryByDate(userPk, dateSavingDate);
        if (diaryByDate.isPresent()){
            return DiaryCheckDto.of(diaryByDate.get().getId(),diaryByDate.get().getContent().getContent(),true);
        }
        return DiaryCheckDto.of(null,null,false);
>>>>>>> 13fb79243c65d393812d75d293722ba4e115f0b9
    }


    public List<Diary> findDiariesBetWeenDates(DiaryFindDto diaryFindDto){
        List<Diary> diaryBetweenDates = diaryRepository.findDiaryBetweenDates(diaryFindDto.getUserPk(), diaryFindDto.getLocalDate().withDayOfMonth(1),
                diaryFindDto.getLocalDate().withDayOfMonth(diaryFindDto.getLocalDate().lengthOfMonth()));
        return diaryBetweenDates;
    }
    public Diary findDiaryByDate(DiaryFindDto diaryFindDto){
        Diary diary = diaryRepository.findDiaryByDate(diaryFindDto.getUserPk(), diaryFindDto.getLocalDate())
                .orElseThrow(() -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND));
        return diary;
    }



}
