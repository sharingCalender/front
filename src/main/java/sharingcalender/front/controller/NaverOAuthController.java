package sharingcalender.front.controller;

import java.time.Duration;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sharingcalender.front.adapter.NaverOAuthAdapter;
import sharingcalender.front.dto.oauth.request.NaverTokenRequestDto;
import sharingcalender.front.dto.oauth.response.NaverJwtTokenIssueResponseDto;

@Controller
@RequestMapping("/oauth/naver")
@RequiredArgsConstructor
public class NaverOAuthController {

    private final NaverOAuthAdapter naverOAuthAdapter;

    @GetMapping("/login")
    public String naverOauthLogin() {
        ResponseEntity<Map<String, String>> response = naverOAuthAdapter.naverOauthLogin();

        return response.getBody().get("redirectURL");
    }

    @GetMapping("/callback")
    public ResponseEntity<Void> naverGetToken(@RequestParam("code") String code,
        @RequestParam("state") String state) {

        ResponseEntity<NaverJwtTokenIssueResponseDto> tokenResponse = naverOAuthAdapter.naverGetToken(
            new NaverTokenRequestDto(code, state));

        // auth 에서 네이버 접근토큰 발급받고 우리서버에서 이용할 jwt 토큰을 여기로 반환하면
        // 여기서 쿠키에 넣어서 응답하면 될 거 같다.
        ResponseCookie accessToken = ResponseCookie.from("accessToken", tokenResponse.getBody().accessToken())
            .httpOnly(true)   // XSS 방지
            .secure(true)     // HTTPS에서만 쿠키 사용
            .path("/")        // 모든 경로에서 쿠키 사용 가능
            .maxAge(Duration.ofHours(1))  // 1시간 유지
            .build();

        ResponseCookie refreshToken = ResponseCookie.from("refreshToken",
                tokenResponse.getBody().refreshToken())
            .httpOnly(true)
            .secure(true)
            .path("/")
            .maxAge(Duration.ofHours(1))
            .build();

        return ResponseEntity.status(HttpStatus.OK)
            .header(HttpHeaders.SET_COOKIE, accessToken.toString(), refreshToken.toString())
            .build();

    }
}
