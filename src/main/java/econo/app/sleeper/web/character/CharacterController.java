package econo.app.sleeper.web.character;

import econo.app.sleeper.domain.character.Character;
import econo.app.sleeper.domain.common.SpeechBubble;
import econo.app.sleeper.domain.user.User;
import econo.app.sleeper.repository.CharacterRepository;
import econo.app.sleeper.repository.UserRepository;
import econo.app.sleeper.web.common.CommonRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "character", description = "케릭터 관련 API")
public class CharacterController{

    private final UserRepository userRepository;
    private final SpeechBubble speechBubble;

    @Operation(summary = "api simple explain", description = "api specific explain")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })

    @GetMapping("/character")
    public ResponseEntity<CharacterResponse> readCharacter(CommonRequest commonRequest){
        User user = userRepository.find(commonRequest.getUserPk()).get();
        Character character = user.getCharacter();
        CharacterResponse characterResponse = CharacterResponse.builder()
                .color(character.getColor())
                .status(character.getStatus())
                .growth(character.getGrowth())
                .speechBubble(speechBubble.getSpeechBubble())
                .build();
        return new ResponseEntity<>(characterResponse, HttpStatus.OK);
    }
}
