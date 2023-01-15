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
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "CHARACTER_COLOR")
    private Color color;
    @Enumerated(EnumType.STRING)
    @Column(name = "CHARACTER_STATUS")
    private Status status;
    @Embedded
    private Growth growth;
    @OneToOne(mappedBy = "character", fetch = FetchType.LAZY)
    private User user;

    @Builder
    public Character(Color color, Status status, Growth growth, User user){
        this.color = color;
        this.status = status;
        this.growth = growth;
        this.user = user;
    }


    public Character(Long id, Color color, Status status, Growth growth) {
        this.id = id;
        this.color = color;
        this.status = status;
        this.growth = growth;
    }

    public void updateGrowthAndStatus(Growth growth, Status status){
        this.growth = growth;
        this.status = status;
    }

    public void updateStatusToSleep(){
        this.status = Status.SLEEP;
    }

    public void updateColor(Color color){
        this.color = color;
    }


    public void mappingUser(User user){
        this.user = user;
        user.mappingCharacter(this);
    }

    public static Character createCharacter(User user){
        Character character = Character.builder()
                .color(Color.GRAY)
                .status(Status.NO_SLEEP)
                .growth(new Growth(0,0L))
                .user(user)
                .build();
        character.mappingUser(user);
        return character;
    }

}
