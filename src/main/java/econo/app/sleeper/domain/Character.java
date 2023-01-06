package econo.app.sleeper.domain;

import econo.app.sleeper.util.SpeechBubbleKind;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "CHARACTER")
public class Character {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long characterPk;
    @Enumerated(EnumType.STRING)
    @Column(name = "CHARACTER_COLOR")
    private Color color;
    @Enumerated(EnumType.STRING)
    @Column(name = "CHARACTER_STATUS")
    private Status status;
    @Column(name = "CHARACTER_EXPERIENCE")
    private Integer experience;
    @Column(name = "CHARACTER_LEVEL")
    private Long level;

    @Column(name = "CHARACTER_SPEECH_BUBBLE")
    private String speechBubble;
    @OneToOne(mappedBy = "character", fetch = FetchType.LAZY)
    private User user;
    @Builder
    public Character(Color color, Status status, Integer experience, Long level, String speechBubble, User user){
        this.color = color;
        this.status = status;
        this.experience = experience;
        this.level = level;
        this.speechBubble = speechBubble;
        this.user = user;
    }

    public void updateCharacter(Integer experience, Long level, Status status, String speechBubble) {
        this.experience = experience;
        this.level = level;
        this.status = status;
        this.speechBubble = speechBubble;
    }

    public void updateCharacter(String speechBubble, Status status) {
        this.speechBubble = speechBubble;
        this.status = status;
    }

    public void updateCharacter(String speechBubble) {
        this.speechBubble = speechBubble;
    }

    public static Character initCharacter(User user){
        Character character = Character.builder()
                .color(Color.GRAY)
                .status(Status.NO_SLEEP)
                .experience(0)
                .level(1L)
                .speechBubble(SpeechBubbleKind.NO_SLEEP.message())
                .user(user)
                .build();
        user.associate(character);
        return character;
    }

}
