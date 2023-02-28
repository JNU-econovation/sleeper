package econo.app.sleeper.web.character;
import econo.app.sleeper.service.character.CharacterService;
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
import javax.validation.Valid;



@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "character", description = "케릭터 관련 API")
public class CharacterController{

    private final CharacterService characterService;

    @Operation(summary = "api simple explain", description = "api specific explain")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })

    @GetMapping("/character/{nu}")
    public ResponseEntity<CharacterResponse> readCharacter(@PathVariable("nu")Long characterPk){
        CharacterResponse characterResponse = characterService.readCharacter(characterPk);
        return new ResponseEntity<>(characterResponse, HttpStatus.OK);
    }
}
