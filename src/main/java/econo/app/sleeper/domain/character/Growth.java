package econo.app.sleeper.domain.character;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Growth {

    @Column(name = "CHARACTER_EXPERIENCE")
    private Integer experience;

    @Column(name = "CHARACTER_LEVEL")
    private Long level;

    protected Growth(Integer experience, Long level){
        this.experience = experience;
        this.level = level;
    }

    public Growth growUp(Integer plusExperience){
        Integer nextExperience = experience + plusExperience;
        Long nextLevel = convertExToLevel(nextExperience);
        return new Growth(nextExperience,nextLevel);
    }

    private Long convertExToLevel(Integer nextExperience) {

        if(Lx.SEVEN.getExperienceOfLevel() < nextExperience){
            return Lx.SEVEN.getLevel();
        } else if (Lx.SIX.getExperienceOfLevel() < nextExperience) {
            return Lx.SIX.getLevel();
        }else if (Lx.FIVE.getExperienceOfLevel() < nextExperience) {
            return Lx.FIVE.getLevel();
        }else if (Lx.FOUR.getExperienceOfLevel() < nextExperience) {
            return Lx.FOUR.getLevel();
        }else if (Lx.THREE.getExperienceOfLevel() < nextExperience) {
            return Lx.THREE.getLevel();
        }else if (Lx.TWO.getExperienceOfLevel() < nextExperience) {
            return Lx.TWO.getLevel();
        }else if (Lx.ONE.getExperienceOfLevel() < nextExperience){
            return Lx.ONE.getLevel();
        }else{
            return Lx.ZERO.getLevel();
        }

    }

    public Boolean approachLevel(){

        if (Lx.ONE.getExperienceOfLevel()*0.9 < experience & Lx.ONE.getExperienceOfLevel() > experience) {
            return true;
        } else if (Lx.TWO.getExperienceOfLevel()*0.9 < experience & Lx.TWO.getExperienceOfLevel() > experience) {
            return true;
        } else if (Lx.THREE.getExperienceOfLevel()*0.9 < experience & Lx.THREE.getExperienceOfLevel() > experience) {
            return true;
        } else if (Lx.FOUR.getExperienceOfLevel()*0.9 < experience & Lx.FOUR.getExperienceOfLevel() > experience) {
            return true;
        } else if (Lx.FIVE.getExperienceOfLevel()*0.9 < experience & Lx.FIVE.getExperienceOfLevel() > experience) {
            return true;
        } else if (Lx.SIX.getExperienceOfLevel()*0.9 < experience & Lx.SIX.getExperienceOfLevel() > experience) {
            return true;
        }
        return false;
    }
}
