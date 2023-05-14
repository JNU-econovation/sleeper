package econo.app.sleeper.domain.diary;

import econo.app.sleeper.domain.member.Member;
import lombok.*;

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
    @JoinColumn(name = "MEMBER_FK")
    private Member member; // 연관관계의 주인

    @Column(name = "DIARY_DATE")
    private LocalDate diaryDate;

    @Column(name = "DIARY_WRITING_TIME")
    private ZonedDateTime writingTime;

    @Builder
    public Diary(String content, Member member){
        this.content = content;
        this.member = member;
    }

    public void associate(Member member){
        this.member = member;
    }

    public static Diary create(String content, LocalDate diaryDate, ZonedDateTime writingTIme, Member member){
        Diary diary = new Diary();
        diary.content = content;
        diary.diaryDate = diaryDate;
        diary.writingTime = writingTIme;
        diary.associate(member);
        return diary;
    }

    public void update(String content) {
        this.content = content;
    }

    public Integer getContentLength(){
        return this.content.length();
    }


}
