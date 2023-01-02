package econo.app.sleeper.web.character;

import econo.app.sleeper.domain.Character;
import econo.app.sleeper.repository.CharacterRepository;
import econo.app.sleeper.web.login.LoginUser;
import econo.app.sleeper.web.login.SessionConst;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "character", description = "케릭터 관련 API")
public class CharacterController{

    private final CharacterRepository characterRepository;

    @Operation(summary = "api simple explain", description = "api specific explain")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })


    @GetMapping("/character")
    public ResponseEntity<CharacterResponse> readCharacter(@SessionAttribute(SessionConst.LOGIN_USER) Object loginUser){
        LoginUser loginUser1 = (LoginUser)loginUser;
        String userId = loginUser1.getUserId();
        Character character = characterRepository.findById(userId).get();
        CharacterResponse characterResponse = CharacterResponse.builder()
                .color(character.getColor())
                .status(character.getStatus())
                .experience(character.getExperience())
                .level(character.getLevel())
                .speechBubble(character.getSpeechBubble())
                .build();
        return new ResponseEntity<>(characterResponse, HttpStatus.OK);
    }
}
