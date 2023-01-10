package econo.app.sleeper.web.user;

import econo.app.sleeper.domain.user.RoleType;
import econo.app.sleeper.domain.user.User;
import lombok.*;

import javax.persistence.Access;

@Data
public class SignUpRequest {

    private String userId;
    private String userPassword;
    private String userNickName;
    private Long userAge;

    public User toEntity(){
        return User.builder()
                .userId(userId)
                .userPassword(userPassword)
                .userNickName(userNickName)
                .userAge(userAge)
                .roleType(RoleType.USER)
                .build();
    }

}


