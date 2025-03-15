package sharingcalender.front.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;
import sharingcalender.front.service.TokenService;

@RequiredArgsConstructor
public class TokenExpirationCheckFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {

        String requestURI = request.getRequestURI();


        if (requestURI.startsWith("/vendor") || requestURI.startsWith("/css")
            || requestURI.startsWith("/img") || requestURI.startsWith("/js")) {
            filterChain.doFilter(request, response);

            return;
        }


        Cookie accessToken = WebUtils.getCookie(request, "accessToken");

        Cookie refreshToken = WebUtils.getCookie(request, "refreshToken");

        if (accessToken == null && refreshToken != null) {
            // auth 서버에 reissue 호출
            tokenService.reissueToken(refreshToken.getValue(), response);
            response.sendRedirect(request.getRequestURI());
            return;
        }

        filterChain.doFilter(request, response);

    }
}
