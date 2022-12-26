package econo.app.sleeper.domain;

import lombok.*;

import javax.persistence.*;
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

   private String userId;

   private String userPassword;
   @Column(length = 20)
   private String userNickName;
   private Long userAge;
   @Enumerated(EnumType.STRING)
   @Column(name = "USER_ROLE_TYPE")
   private RoleType roleType;

   private String userMessage;

   private Integer userMoney;

   @Builder
   public User(String userId, String userPassword, String userNickName, Long userAge, RoleType roleType){
      this.userId = userId;
      this.userPassword = userPassword;
      this.userNickName = userNickName;
      this.userAge = userAge;
      this.roleType = roleType;
   }


   public void updateMoney(Integer userMoney){
      this.userMoney = userMoney;
   }

}
