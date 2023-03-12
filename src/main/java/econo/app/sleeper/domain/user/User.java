package econo.app.sleeper.domain.user;

import econo.app.sleeper.domain.character.Character;
import econo.app.sleeper.domain.sleep.SleepAdvisor;
import lombok.*;
import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "USER")
public class User {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
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

   @OneToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "CHARACTER_FK")
   private Character character;

   @OneToOne(fetch =  FetchType.LAZY)
   @JoinColumn(name = "USER_SLEEP_INFO_FK")
   private SleepAdvisor sleepAdvisor;

   @Builder
   public User(String userId, String userPassword, String userNickName, Long userAge, RoleType roleType){
      this.userId = userId;
      this.userPassword = userPassword;
      this.userNickName = userNickName;
      this.userAge = userAge;
      this.roleType = roleType;
   }

   public void mappingCharacter(Character character){
      this.character = character;
   }

   public void mappingSleepAdvisor(SleepAdvisor sleepAdvisor){
      this.sleepAdvisor = sleepAdvisor;
   }

   public static User createUser(String userId, String userPassword, String userNickName, Long userAge, RoleType roleType){
      User user = new User(userId,userPassword,userNickName,userAge,roleType);
      return user;
   }


}
