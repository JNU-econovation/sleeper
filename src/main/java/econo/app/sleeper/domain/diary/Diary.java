package econo.app.sleeper.domain.diary;

import econo.app.sleeper.domain.common.SavingDate;
import econo.app.sleeper.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "DIARY")
public class Diary {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private Content content;
    @Embedded
    private SavingDate savingDate;

    @Column(name = "DIARY_DELETE_DATE", columnDefinition = "DATE")
    private LocalDate deleteLocalDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_FK")
    private User user; // 연관관계의 주인

    @Builder
    public Diary(Content content, User user){
        this.content = content;
        this.savingDate = new SavingDate();
        this.user = user;
    }

    public void associate(User user){
        this.user = user;
    }

    public static Diary create(User user,String content){
        Diary diary = new Diary();
        diary.associate(user);
        diary.content = new Content(content);
        diary.savingDate = new SavingDate();
        return diary;
    }


}
