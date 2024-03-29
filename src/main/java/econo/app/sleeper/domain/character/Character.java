package econo.app.sleeper.domain.character;

import econo.app.sleeper.domain.member.Member;
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
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "CHARACTER_COLOR")
    private Color color;
    @Enumerated(EnumType.STRING)
    @Column(name = "CHARACTER_STATUS")
    private Status status;

    @Column(name = "CHARACTER_EXPERIENCE")
    private Integer cumulativeXp;

    @Column(name = "CHARACTER_LEVEL")
    private Long level;

    @Builder
    public Character(Color color, Status status, Integer cumulativeXp, Long level){
        this.color = color;
        this.status = status;
        this.cumulativeXp = cumulativeXp;
        this.level = level;
    }

    public void oppositeStatus() {
        Status oppositeStatus = status.opposite(status.name());
        this.status = oppositeStatus;
    }

    public void plusXp(Integer xp){
        this.cumulativeXp += xp;
        levelUpOrStay();
    }

    private void levelUpOrStay(){
        XpPerLevel.getCurrentLevel(cumulativeXp,level);
    }

    public static Character createCharacter(Member member){
        Character character = Character.builder()
                .color(Color.YELLOW)
                .status(Status.NO_SLEEP)
                .cumulativeXp(0)
                .level(1L)
                .build();
        member.mappingCharacter(character);
        return character;
    }


}
