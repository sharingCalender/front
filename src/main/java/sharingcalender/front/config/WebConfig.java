package sharingcalender.front.config;

import java.util.logging.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import sharingcalender.front.filter.LoginCheckFilter;
import sharingcalender.front.filter.TokenExpirationCheckFilter;
import sharingcalender.front.interceptor.AuthorizationInterceptor;
import sharingcalender.front.interceptor.LoginCheckInterceptor;
import sharingcalender.front.service.TokenService;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final TokenService tokenService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthorizationInterceptor());
        registry.addInterceptor(new LoginCheckInterceptor());
    }

    @Bean
    public FilterRegistrationBean<TokenExpirationCheckFilter> tokenExpirationCheckFilter() {
        FilterRegistrationBean<TokenExpirationCheckFilter> filterRegistrationBean = new FilterRegistrationBean<>();

        filterRegistrationBean.setFilter(new TokenExpirationCheckFilter(tokenService));

        filterRegistrationBean.addUrlPatterns("/*");

        filterRegistrationBean.setOrder(1);

        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<LoginCheckFilter> loginCheckFilterFilter() {
        FilterRegistrationBean<LoginCheckFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LoginCheckFilter());

        filterRegistrationBean.addUrlPatterns("/calendar/*","/user/logout");
        filterRegistrationBean.setOrder(2);

        return filterRegistrationBean;
    }

}
