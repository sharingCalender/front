package sharingcalender.front.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import sharingcalender.front.dto.TokenIssueResponseDto;

public class TokenUtil {

    // @Value 애노테이션은 인스턴스레벨에서 동작하기 때문에 static 에 사용하면 동작하지 않아 0으로 나온다...


    public static void addTokenToCookie(TokenIssueResponseDto tokenIssueResponseDto,
        HttpServletResponse response, int accessExpirationTime, int refreshExpirationTime) {

        Cookie accessToken = new Cookie("accessToken", tokenIssueResponseDto.accessToken());
        accessToken.setHttpOnly(true);
        accessToken.setSecure(true);
        accessToken.setPath("/");
        accessToken.setMaxAge(accessExpirationTime);
        response.addCookie(accessToken);

        Cookie refreshToken = new Cookie("refreshToken", tokenIssueResponseDto.refreshToken());
        refreshToken.setHttpOnly(true);
        refreshToken.setSecure(true);
        refreshToken.setPath("/");
        refreshToken.setMaxAge(refreshExpirationTime);
        response.addCookie(refreshToken);

    }

    public static void removeTokenFromCookie(HttpServletResponse response) {

        Cookie accessToken = new Cookie("accessToken", null);
        accessToken.setHttpOnly(true);
        accessToken.setSecure(true);
        accessToken.setPath("/");
        accessToken.setMaxAge(0);
        response.addCookie(accessToken);

        Cookie refreshToken = new Cookie("refreshToken", null);
        refreshToken.setHttpOnly(true);
        refreshToken.setSecure(true);
        refreshToken.setPath("/");
        refreshToken.setMaxAge(0);
        response.addCookie(refreshToken);

    }

}
