package econo.app.sleeper.service.diary;

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

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        // 감사일기 하루에 2번 작성하는 경우 처리하기!
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

    public List<Diary> findDiariesByUser(Long userPk){
        User user = userRepository.find(userPk)
                .orElseThrow(() -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND));
        return diaryRepository.findAllByPk(user.getId());
    }

    public List<Diary> findDiariesByDate(DiaryFindDto diaryFindDto){
        User user = userRepository.find(diaryFindDto.getUserPk())
                .orElseThrow(() -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND));
        Stream<Diary> diaryStream = user.getDiaries().stream()
                .filter(d -> d.getSavingDate().getSavingDate().isEqual(diaryFindDto.getLocalDate()));
        return diaryStream.collect(Collectors.toList());
    }

    public List<Diary> findDiariesBetWeenDates(DiaryFindDto diaryFindDto){
        User user = userRepository.find(diaryFindDto.getUserPk())
                .orElseThrow(() -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND));
        Stream<Diary> diaryStream = user.getDiaries().stream()
                .filter(d -> d.getSavingDate().getSavingDate().getMonth().equals(diaryFindDto.getLocalDate().getMonth()));
        return diaryStream.collect(Collectors.toList());
    }



}
