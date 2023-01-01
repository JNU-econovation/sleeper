package econo.app.sleeper.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "DIARY")
public class Diary {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long diaryPk;

    @Column(name = "DIARY_CONTENT", columnDefinition = "TEXT")
    private String content;
    @Column(name = "DIARY_DATE", columnDefinition = "DATE")
    private LocalDate savingDate;

    @Column(name = "DIARY_WRITING_TIME", columnDefinition = "TIMESTAMP")
    private LocalDateTime writingTime;

    @Column(name = "DIARY_DELETE_DATE", columnDefinition = "DATE")
    private LocalDate deleteLocalDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_FK")
    private User user; // 연관관계의 주인

    @Builder
    public Diary(String content, LocalDate savingDate, LocalDateTime writingTime, User user){
        this.content = content;
        this.writingTime = writingTime;
        this.savingDate = savingDate;
        this.user = user;
    }

    public void updateContent(String content){
        this.content = content;
    }

}
