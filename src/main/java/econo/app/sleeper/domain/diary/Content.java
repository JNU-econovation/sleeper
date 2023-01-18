package econo.app.sleeper.domain.diary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Content {

    @Column(name = "DIARY_CONTENT", columnDefinition = "TEXT")
    private String content;

    public Content(String content) {
        this.content = content;
    }

    public void update(String content) {
        this.content = content;
    }

    public Integer reward(){

        Integer contentLength = content.length();

        if(contentLength < 1){
            return RewardStage.BRONZE_REWARD.getReward();
        }else if(contentLength < 10){
            return RewardStage.SILVER_REWARD.getReward();
        }else{
            return RewardStage.BRONZE_REWARD.getReward();
        }
    }
    @RequiredArgsConstructor
    @Getter
    private enum RewardStage{
        GOLD_REWARD(1,10),
        SILVER_REWARD(2,5),
        BRONZE_REWARD(3,1);

        private final Integer grade;
        private final Integer reward;
    }

}
