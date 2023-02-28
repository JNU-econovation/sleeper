package econo.app.sleeper.domain.money;

public interface RewardPolicy {

    Integer decideReward(Long level);
}
