package econo.app.sleeper.service.diary;

import econo.app.sleeper.domain.diary.Diary;
import econo.app.sleeper.domain.user.User;
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
        User user = userRepository.find(diaryRequest.getUserPk()).get();
        Diary diary = Diary.create(user, diaryRequest.getContent());
        diaryRepository.save(diary);
        return diary.getId();
    }

    @Transactional
    public void updateDiary(Long diaryPk,String content) {
        Diary diary = diaryRepository.findByPk(diaryPk).get();
        diary.getContent().update(content);
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
        return diaryRepository.findAllByPk(user.getId());
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
