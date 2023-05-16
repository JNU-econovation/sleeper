package econo.app.sleeper.service.sleep;

import econo.app.sleeper.domain.common.DatePolicy;
import econo.app.sleeper.domain.sleep.Sleep;
import econo.app.sleeper.domain.member.Member;
import econo.app.sleeper.exception.RestApiException;
import econo.app.sleeper.exception.error.CommonErrorCode;
import econo.app.sleeper.domain.sleep.SleepRepository;
import econo.app.sleeper.domain.member.MemberRepository;
import econo.app.sleeper.web.sleep.dto.SleepDto;
import econo.app.sleeper.web.sleep.dto.SleepRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SleepService {

    private final SleepRepository sleepRepository;
    private final MemberRepository memberRepository;
    private final DatePolicy datePolicy;

    @Transactional
    public Long saveSleep(SleepRequest sleepRequest){
        Member member = memberRepository.find(sleepRequest.getUserPk())
                .orElseThrow(() -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND));
        LocalDate decidedDate = decideDate(sleepRequest.getActualSleepTime());
        Sleep sleep = Sleep.createSleep(sleepRequest.getSetSleepTime(),sleepRequest.getSetWakeTime(),sleepRequest.getActualSleepTime(),decidedDate, member);
        sleepRepository.save(sleep);
        return sleep.getId();
    }

    private LocalDate decideDate(ZonedDateTime savedDateTime){
        LocalDate decidedDate = datePolicy.decideDate(savedDateTime);
        return decidedDate;
    }

    @Transactional
    public Integer updateActualWakeTime(SleepDto sleepDto){
        Sleep sleep = sleepRepository.find(sleepDto.getSleepPk())
                .orElseThrow(() -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND));
        sleep.updateActualWakeTime(sleepDto.getActualWakeTime());
        return calculateXp(sleepDto.getSleepPk());
    }

    private Integer calculateXp(Long sleepPk){
        Sleep sleep = sleepRepository.find(sleepPk)
                .orElseThrow(() -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND));
        Integer xp = sleep.calculateXp();
        return xp;
    }





}
