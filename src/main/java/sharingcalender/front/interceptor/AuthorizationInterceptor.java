package sharingcalender.front.interceptor;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.WebUtils;
import sharingcalender.front.threadlocal.AuthorizationTokenHolder;

public class AuthorizationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) throws Exception {

        Cookie accessToken = WebUtils.getCookie(request, "accessToken");
        String token = accessToken != null ? accessToken.getValue() : "ANONYMOUS";

        AuthorizationTokenHolder.setToken(token);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
        Object handler, Exception ex) throws Exception {

        AuthorizationTokenHolder.free();
    }
}
