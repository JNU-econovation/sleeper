package econo.app.sleeper.util;

import io.swagger.models.auth.In;

import java.text.ChoiceFormat;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class ExperienceManager {

    public static Long convertExToLevel(Integer currentExperience, Integer plusExperience) {
        Integer experience = currentExperience + plusExperience;

        if (Level.ONE.getExperience() > experience) {
            return Level.ONE.getLevel();
        } else if (Level.TWO.getExperience() > experience) {
            return Level.TWO.getLevel();
        } else if (Level.THREE.getExperience() > experience) {
            return Level.THREE.getLevel();
        } else if (Level.FOUR.getExperience() > experience) {
            return Level.FOUR.getLevel();
        } else if (Level.FIVE.getExperience() > experience) {
            return Level.FIVE.getLevel();
        } else if (Level.SIX.getExperience() > experience) {
            return Level.SIX.getLevel();
        }
        return Level.SIX.getLevel();


    }

    public static Integer assessExperience(ZonedDateTime setSleepTime, ZonedDateTime setWakeTime, ZonedDateTime actualSleepTime, ZonedDateTime actualWakeTime) {
        if (setWakeTime.isAfter(actualSleepTime)) {
            long between = ChronoUnit.HOURS.between(actualSleepTime, setWakeTime);
            long total = ChronoUnit.HOURS.between(setSleepTime, actualWakeTime);
            long experience = (between*5) / total;
            return (int)experience;
        }
        return 0;
    }

    public static Boolean approachLevel(Integer experience){

        if (Level.ONE.getExperience()*0.9 < experience & Level.ONE.getExperience() > experience) {
            return true;
        } else if (Level.TWO.getExperience()*0.9 < experience & Level.TWO.getExperience() > experience) {
            return true;
        } else if (Level.THREE.getExperience()*0.9 < experience & Level.THREE.getExperience() > experience) {
            return true;
        } else if (Level.FOUR.getExperience()*0.9 < experience & Level.FOUR.getExperience() > experience) {
            return true;
        } else if (Level.FIVE.getExperience()*0.9 < experience & Level.FIVE.getExperience() > experience) {
            return true;
        } else if (Level.SIX.getExperience()*0.9 < experience & Level.SIX.getExperience() > experience) {
            return true;
        }
        return false;
    }
}
    
