package econo.app.sleeper.domain.character;

import econo.app.sleeper.domain.user.User;
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
    @Embedded
    private Growth growth;

    @Enumerated(EnumType.STRING)
    @Column(name = "CHARACTER_SPEECH_BUBBLE")
    private SpeechBubble speechBubble;
    @OneToOne(mappedBy = "character", fetch = FetchType.LAZY)
    private User user;

    @Builder
    public Character(Color color, Status status, Growth growth, SpeechBubble speechBubble, User user){
        this.color = color;
        this.status = status;
        this.growth = growth;
        this.speechBubble = speechBubble;
        this.user = user;
    }

    public void updateCharacter(Growth growth, Status status, SpeechBubble speechBubble) {
        this.growth = growth;
        this.status = status;
        this.speechBubble = speechBubble;
    }

    public void updateCharacter(SpeechBubble speechBubble, Status status) {
        this.speechBubble = speechBubble;
        this.status = status;
    }

    public void updateCharacter(SpeechBubble speechBubble) {
        this.speechBubble = speechBubble;
    }

    public static Character initCharacter(User user){
        Character character = Character.builder()
                .color(Color.GRAY)
                .status(Status.NO_SLEEP)
                .growth(new Growth(0,0L))
                .speechBubble(SpeechBubble.NO_SLEEP)
                .user(user)
                .build();
        user.associate(character);
        return character;
    }

}
