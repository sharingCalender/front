package sharingcalender.front.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

public class LoginCheckFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {


        String requestURI = request.getRequestURI();

        System.out.println("requestURI = " + requestURI);

        if (requestURI.startsWith("/calendar") || requestURI.equals("/user/logout")) {
            Cookie refreshToken = WebUtils.getCookie(request, "refreshToken");
            if (Objects.isNull(refreshToken)) {

                response.sendRedirect("/user/login");
                return;
            }

        }

        filterChain.doFilter(request, response);

    }
}
