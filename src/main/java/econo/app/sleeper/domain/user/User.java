package econo.app.sleeper.domain.user;

import econo.app.sleeper.domain.diary.Diary;
import econo.app.sleeper.domain.money.Money;
import econo.app.sleeper.domain.sleep.Sleep;
import econo.app.sleeper.domain.character.Character;
import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;
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
   private String userId;
   private String userPassword;
   @Column(length = 20)
   private String userNickName;
   private Long userAge;

   private String userMessage;
   @Enumerated(EnumType.STRING)
   @Column(name = "USER_ROLE_TYPE")
   private RoleType roleType;
   @Embedded
   private GoalTime goalTime;

   @OneToMany(mappedBy = "user")
   private List<Diary> diaries = new ArrayList<>();
   @OneToMany(mappedBy = "user")
   private List<Sleep> sleeps = new ArrayList<>();
   @OneToOne(cascade = CascadeType.ALL)
   @JoinColumn(name = "MONEY_FK")
   private Money money;
   @OneToOne(cascade = CascadeType.ALL)
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

   public void associate(Sleep sleep){
      sleeps.add(sleep);
   }

   public void associate(Character character){
      character.associate(this);
      this.character = character;
   }

   public void associate(Money money){
      money.associate(this);
      this.money = money;
   }

   public void update(LocalTime goalSleepTime, LocalTime goalWakeTime) {
      this.goalTime = new GoalTime(goalSleepTime,goalWakeTime);
   }

   public static User create(String userId, String userPassword, String userNickName, Long userAge, RoleType roleType){
      User user = new User(userId,userPassword,userNickName,userAge,roleType);
      Money money = Money.init(user);
      Character character = Character.init(user);
      user.associate(money);
      user.associate(character);
      return user;
   }


}
