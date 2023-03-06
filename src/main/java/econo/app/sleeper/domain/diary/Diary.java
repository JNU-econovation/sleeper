package econo.app.sleeper.domain.diary;

import econo.app.sleeper.domain.user.User;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZonedDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "DIARY")
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "DIARY_CONTENT", columnDefinition = "TEXT")
    private String content;

    @Column(name = "DIARY_DELETE_DATE", columnDefinition = "DATE")
    private LocalDate deleteDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_FK")
    private User user; // 연관관계의 주인
    @Column(name = "DIARY_DATE")
    private LocalDate diaryDate;

    @Column(name = "DIARY_WRITING_TIME")
    private ZonedDateTime writingTime;

    @Builder
    public Diary(String content, User user){
        this.content = content;
        this.user = user;
    }

    public void associate(User user){
        this.user = user;
    }

    public static Diary create(String content, LocalDate diaryDate, ZonedDateTime writingTIme, User user){
        Diary diary = new Diary();
        diary.content = content;
        diary.diaryDate = diaryDate;
        diary.writingTime = writingTIme;
        diary.associate(user);
        return diary;
    }

    public void update(String content) {
        this.content = content;
    }

    public Integer getContentLength(){
        return this.content.length();
    }


}
