package econo.app.sleeper;


import econo.app.sleeper.web.login.JwtInterceptor;
import econo.app.sleeper.web.login.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private JwtTokenProvider jwtTokenProvider;

    public WebConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptor(jwtTokenProvider))
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/users","/auth/login");
    }

}