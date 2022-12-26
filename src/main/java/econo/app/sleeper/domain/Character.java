package econo.app.sleeper.domain;

import lombok.AccessLevel;
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

    @Column(name = "CHARACTER_COLOR")
    private Color color;

    @Column(name = "CHARACTER_STATUS")
    private Status status;

    @Column(name = "CHARACTER_EXPERIENCE")
    private Integer experience;

    @Column(name = "CHARACTER_LEVEL")
    private Long level;

    @OneToOne(mappedBy = "character", fetch = FetchType.LAZY)
    private User user;

}
