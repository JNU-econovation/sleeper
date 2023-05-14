package econo.app.sleeper.domain.Auth;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "AUTH")
public class Auth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ACCESS_TOKEN")
    private String accessToken;

    @Column(name = "REFRESH_TOKEN")
    private String refreshToken;


    @Builder
    public Auth(String accessToken,String refreshToken){
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public static Auth createAuth(String accessToken,String refreshToken){
        Auth auth = Auth.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
        return auth;
    }

}
