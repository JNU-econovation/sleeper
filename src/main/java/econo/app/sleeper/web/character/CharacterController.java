package econo.app.sleeper.web.character;

<<<<<<< HEAD
import econo.app.sleeper.service.character.CharacterService;

=======

import econo.app.sleeper.service.character.CharacterService;
>>>>>>> 13fb79243c65d393812d75d293722ba4e115f0b9
import econo.app.sleeper.web.common.CommonRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
<<<<<<< HEAD

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

=======
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
>>>>>>> 13fb79243c65d393812d75d293722ba4e115f0b9

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

    @GetMapping("/character")
    public ResponseEntity<CharacterResponse> readCharacter(@Valid CommonRequest commonRequest){
        CharacterResponse characterResponse = characterService.readCharacter(commonRequest.getUserPk());
        return new ResponseEntity<>(characterResponse, HttpStatus.OK);
    }
}
