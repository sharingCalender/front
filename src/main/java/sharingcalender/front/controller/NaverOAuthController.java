package sharingcalender.front.controller;

import jakarta.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sharingcalender.front.adapter.AuthAdapter;
import sharingcalender.front.dto.oauth.request.NaverTokenRequestDto;
import sharingcalender.front.dto.TokenIssueResponseDto;
import sharingcalender.front.service.TokenService;
import sharingcalender.front.service.impl.NaverServiceImpl;

@Controller
@RequestMapping("/oauth/naver")
@RequiredArgsConstructor
public class NaverOAuthController {

    private final AuthAdapter authAdapter;

    private final NaverServiceImpl naverService;

    private final TokenService tokenService;

    @Value("${spring.jwt.token.access-expiration-time}")
    private long accessExpirationTime;

    @Value("${spring.jwt.token.refresh-expiration-time}")
    private long refreshExpirationTime;

    @GetMapping("/login")
    public String naverOauthLogin() {
        return naverService.naverOauthLogin();
    }

    @GetMapping("/callback")
    public String naverGetToken(@RequestParam("code") String code,
        @RequestParam("state") String state, HttpServletResponse response) {

        TokenIssueResponseDto tokenResponse = naverService.naverGetToken(
            new NaverTokenRequestDto(code, state));

        // auth 에서 네이버 접근토큰 발급받고 우리서버에서 이용할 jwt 토큰을 여기로 반환하면
        // 여기서 쿠키에 넣어서 응답하면 될 거 같다.
        tokenService.addTokenToCookie(tokenResponse, response);

        return "test";

    }



}
