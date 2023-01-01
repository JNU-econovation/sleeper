package econo.app.sleeper.util;

import io.swagger.models.auth.In;

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
        System.out.println("setWakeTime = " + setWakeTime.isAfter(actualSleepTime));
        if (setWakeTime.isAfter(actualSleepTime)) {
            long between = ChronoUnit.HOURS.between(actualSleepTime, setWakeTime);
            System.out.println("between = " + between);
            long total = ChronoUnit.HOURS.between(setSleepTime, actualWakeTime);
            System.out.println("total = " + total);
            long experience = (between*5) / total;
            System.out.println("experience = " + experience);
            return (int)experience;
        }
        return 0;
    }
}
    
