package econo.app.sleeper.domain;

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

}
