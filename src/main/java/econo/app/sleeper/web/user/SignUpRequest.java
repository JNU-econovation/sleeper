package econo.app.sleeper.web.user;

import econo.app.sleeper.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
//Setter()를 사용하지 않는 이유 : 계층간 이동시 데이터 변조 위험 x
@RequiredArgsConstructor
@Builder
public class SignUpRequest {

    private final String userId;
    private final String userPassword;
    private final String userNickName;
    private final Long userAge;

    private final Integer userMoney;


    public static SignUpRequest of(User user){
        return SignUpRequest.builder()
                .userId(user.getUserId())
                .userPassword(user.getUserPassword())
                .userNickName(user.getUserNickName())
                .userAge(user.getUserAge())
                .userMoney(0)
                .build();
    }

    public User toEntity(){
        return User.builder()
                .userId(userId)
                .userPassword(userPassword)
                .userNickName(userNickName)
                .userAge(userAge)
                .build();
    }

}


