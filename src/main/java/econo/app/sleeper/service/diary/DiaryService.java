package econo.app.sleeper.service.diary;

import econo.app.sleeper.domain.common.DatePolicy;
import econo.app.sleeper.domain.diary.Diary;
import econo.app.sleeper.domain.member.Member;
import econo.app.sleeper.exception.RestApiException;
import econo.app.sleeper.exception.error.CommonErrorCode;
import econo.app.sleeper.domain.diary.DiaryRepository;
import econo.app.sleeper.domain.member.MemberRepository;
import econo.app.sleeper.web.diary.dto.DiaryFindDto;
import econo.app.sleeper.web.diary.dto.DiaryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final MemberRepository memberRepository;
    private final DatePolicy datePolicy;

    @Transactional
    public void save(DiaryRequest diaryRequest){
        Member member = memberRepository.find(diaryRequest.getUserPk())
                .orElseThrow(() -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND));
        LocalDate decidedDate = decideDate(diaryRequest.getWritingDiaryTime());
        Diary diary = Diary.create(diaryRequest.getContent(), decidedDate, diaryRequest.getWritingDiaryTime(), member);
        diaryRepository.save(diary);
    }
    private LocalDate decideDate(ZonedDateTime savedDateTime){
        LocalDate decidedDate = datePolicy.decideDate(savedDateTime);
        return decidedDate;
    }

    @Transactional
    public void updateDiary(Long diaryPk,String content) {
        Diary diary = diaryRepository.find(diaryPk)
                .orElseThrow(() -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND));
        diary.update(content);
    }

    @Transactional
    public void deleteDiary(Long diaryPk){
        Diary diary = diaryRepository.find(diaryPk)
                .orElseThrow(() -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND));
        diaryRepository.delete(diary);
    }

    public Diary findDiary(Long diaryPk) throws RestApiException{
        return diaryRepository.find(diaryPk)
                .orElseThrow(() -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND));
    }

    public List<Diary> findDiaries(Long userPk){
        Member member = memberRepository.find(userPk)
                .orElseThrow(() -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND));
        return diaryRepository.findAll(member.getId());
    }

    public Diary findDiaryByDate(DiaryFindDto diaryFindDto){
        Diary diary = diaryRepository.findDiaryByDate(diaryFindDto.getUserPk(), diaryFindDto.getLocalDate())
                .orElseThrow(() -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND));
        return diary;
    }




}
