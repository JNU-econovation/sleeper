package econo.app.sleeper.service.character;

import econo.app.sleeper.domain.Character;
import econo.app.sleeper.domain.Diary;
import econo.app.sleeper.domain.User;
import econo.app.sleeper.repository.CharacterRepository;
import econo.app.sleeper.repository.DiaryRepository;
import econo.app.sleeper.repository.UserRepository;
import econo.app.sleeper.service.user.UserService;
import econo.app.sleeper.util.DateJudgementUtil;
import econo.app.sleeper.util.MoneyManager;
import econo.app.sleeper.web.character.CharacterDto;
import econo.app.sleeper.web.diary.DiaryResponse;
import econo.app.sleeper.web.diary.DiaryTimeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CharacterService {

    private final CharacterRepository characterRepository;
    private final UserService userService;


}
