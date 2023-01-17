package econo.app.sleeper.domain.user;

import econo.app.sleeper.domain.diary.Diary;
import econo.app.sleeper.domain.Money;
import econo.app.sleeper.domain.Sleep;
import econo.app.sleeper.domain.character.Character;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "USER")
public class User {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long userPk;

   @OneToMany(mappedBy = "user")
   private List<Diary> diaries = new ArrayList<>();

   @OneToMany(mappedBy = "user")
   private List<Sleep> sleeps = new ArrayList<>();
   private String userId;
   private String userPassword;
   @Column(length = 20)
   private String userNickName;
   private Long userAge;
   @Enumerated(EnumType.STRING)
   @Column(name = "USER_ROLE_TYPE")
   private RoleType roleType;

   private String userMessage;

   @OneToOne
   @JoinColumn(name = "MONEY_FK")
   private Money money;

   @Column(name = "USER_GOAL_SLEEP_TIME", columnDefinition = "TIME")
   private LocalTime goalSleepTime;

   @Column(name = "USER_GOAL_WAKE_TIME", columnDefinition = "TIME")
   private LocalTime goalWakeTime;

   @OneToOne
   @JoinColumn(name = "CHARACTER_FK")
   private Character character;

   @Builder
   public User(String userId, String userPassword, String userNickName, Long userAge, RoleType roleType){
      this.userId = userId;
      this.userPassword = userPassword;
      this.userNickName = userNickName;
      this.userAge = userAge;
      this.roleType = roleType;
   }

   public void associate(Character character){
      this.character = character;
   }

   public void associate(Money money){
      this.money = money;
   }

   public void update(LocalTime goalSleepTime, LocalTime goalWakeTime) {
      this.goalSleepTime = goalSleepTime;
      this.goalWakeTime = goalWakeTime;
   }

   public List<LocalDateTime> toLocalDateTime(){

      LocalDate savingDate = LocalDate.now(ZoneId.of("Asia/Seoul"));

      // 목표 수면 시간이 5시~23시 59이면 savingDate 걍 쓰면 됨, 목표 기상시간은 savingDate +1 해야함
      if(goalSleepTime.isAfter(LocalTime.of(5,0)) & goalSleepTime.isBefore(LocalTime.of(23,59))){
         List<LocalDateTime> localDateTimes = new ArrayList<>();
         localDateTimes.add(LocalDateTime.of(savingDate,goalSleepTime));
         localDateTimes.add(LocalDateTime.of(savingDate.plusDays(1L),goalWakeTime));
         return localDateTimes;
      }
      List<LocalDateTime> localDateTimes = new ArrayList<>();
      localDateTimes.add(LocalDateTime.of(savingDate.plusDays(1L),goalSleepTime));
      localDateTimes.add(LocalDateTime.of(savingDate.plusDays(1L),goalWakeTime));
      return localDateTimes;
      // 목표 수면 시간이 0시~5시이면 savingDate + 1 해야함, 목표 기상시간은 savingDate +1 해야함

   }
   //todo 내부 패스워드랑 외부 패스워드를 배교하는 메서드 만들기

}
