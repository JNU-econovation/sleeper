package econo.app.sleeper.web.character;

<<<<<<< HEAD

=======
>>>>>>> 13fb79243c65d393812d75d293722ba4e115f0b9
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class CharacterDto {

    private final Long userPk;
    private final Integer plusExperience;

    public static CharacterDto of(Long userPk, Integer plusExperience){
        return CharacterDto.builder()
                .userPk(userPk)
                .plusExperience(plusExperience)
                .build();
    }
}
