package econo.app.sleeper.domain.member;

import econo.app.sleeper.domain.character.Character;
import econo.app.sleeper.domain.sleep.SleepAdvisor;
import lombok.*;
import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "MEMBER")
public class Member {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(nullable = false)
   private String memberId;

   @Column(nullable = false)
   private String memberPassword;

   @Column(length = 20, nullable = false)
   private String memberNickName;

   @Column(nullable = false)
   private Long memberAge;

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
   public Member(String memberId, String memberPassword, String memberNickName, Long memberAge, RoleType roleType){
      this.memberId = memberId;
      this.memberPassword = memberPassword;
      this.memberNickName = memberNickName;
      this.memberAge = memberAge;
      this.roleType = roleType;
   }

   public void mappingCharacter(Character character){
      this.character = character;
   }

   public void mappingSleepAdvisor(SleepAdvisor sleepAdvisor){
      this.sleepAdvisor = sleepAdvisor;
   }

   public static Member createMember(String userId, String userPassword, String userNickName, Long userAge, RoleType roleType){
      Member member = new Member(userId,userPassword,userNickName,userAge,roleType);
      return member;
   }


}
