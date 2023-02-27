package econo.app.sleeper.domain.diary;

import econo.app.sleeper.domain.user.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "DIARY")
public class Diary {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "DIARY_CONTENT", columnDefinition = "TEXT")
    private String content;

    @Column(name = "DIARY_DELETE_DATE", columnDefinition = "DATE")
    private LocalDate deleteDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_FK")
    private User user; // 연관관계의 주인
    @Column
    private LocalDate diaryDate;

    private boolean isFirstWriting;

    @Builder
    public Diary(String content, User user){
        this.content = content;
        this.user = user;
    }

    public void associate(User user){
        this.user = user;
    }

    public static Diary create(User user,String content, LocalDate diaryDate){
        Diary diary = new Diary();
        diary.associate(user);
        diary.content = content;
        diary.diaryDate = diaryDate;
        diary.isFirstWriting = false;
        return diary;
    }

    public Boolean isFirstWriting(){
        return isFirstWriting;
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
