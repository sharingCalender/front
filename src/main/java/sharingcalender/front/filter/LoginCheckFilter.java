package sharingcalender.front.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

@Slf4j
public class LoginCheckFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {


        String requestURI = request.getRequestURI();

        log.info("RequestURI : {} . Method: {} ", requestURI, request.getMethod());

        Cookie refreshToken = WebUtils.getCookie(request, "refreshToken");

        if (requestURI.startsWith("/calendar") || requestURI.equals("/user/logout")
            || requestURI.startsWith("/chat")) {

            if (Objects.isNull(refreshToken)) {

                response.sendRedirect("/user/login");
                return;
            }
        }

        request.setAttribute("isLoggedIn", refreshToken != null);

        filterChain.doFilter(request, response);

    }
}
