package econo.app.sleeper.domain.character;

public interface XpPolicy {
    Integer calculateXp(Integer increasingExperience, Long level);

}
