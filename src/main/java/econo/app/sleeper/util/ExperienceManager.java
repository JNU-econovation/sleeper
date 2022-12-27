package econo.app.sleeper.util;

public class ExperienceManager {

    public static Long convertExToLevel(Integer experience) {
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

    
}