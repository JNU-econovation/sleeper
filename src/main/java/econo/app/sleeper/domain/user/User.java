package econo.app.sleeper.domain.user;

import econo.app.sleeper.domain.character.Character;
import lombok.*;
import javax.persistence.*;
import java.time.LocalTime;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "USER")
public class User {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   //todo 나머지 부분도 Pk를 id로 고치기
   private Long id;
   @Column(nullable = false)
   private String userId;
   @Column(nullable = false)
   private String userPassword;
   @Column(length = 20, nullable = false)
   private String userNickName;
   @Column(nullable = false)
   private Long userAge;
   @Enumerated(EnumType.STRING)
   @Column(name = "USER_ROLE_TYPE")
   private RoleType roleType;
   @Embedded
   private GoalTime goalTime;

   @OneToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "CHARACTER_FK")
   private Character character;

   @Builder
   public User(String userId, String userPassword, String userNickName, Long userAge, RoleType roleType, LocalTime goalSleepTime, LocalTime goalWakeTime){
      this.userId = userId;
      this.userPassword = userPassword;
      this.userNickName = userNickName;
      this.userAge = userAge;
      this.roleType = roleType;
      this.goalTime = new GoalTime(goalSleepTime,goalWakeTime);
   }

   public void mappingCharacter(Character character){
      this.character = character;
   }

   public static User createUser(String userId, String userPassword, String userNickName, Long userAge, RoleType roleType, LocalTime goalSleepTime, LocalTime goalWakeTime){
      User user = new User(userId,userPassword,userNickName,userAge,roleType, goalSleepTime, goalWakeTime);
      return user;
   }


}
