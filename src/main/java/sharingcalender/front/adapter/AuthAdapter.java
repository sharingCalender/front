package sharingcalender.front.adapter;

import java.util.Map;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import sharingcalender.front.config.FeignClientConfig;
import sharingcalender.front.dto.TokenReissueRequestDto;
import sharingcalender.front.dto.oauth.request.NaverTokenRequestDto;
import sharingcalender.front.dto.TokenIssueResponseDto;

@FeignClient(name = "auth-service", url = "${gateway.url}", configuration = FeignClientConfig.class)
public interface AuthAdapter {

    @PostMapping("/api/auth/oauth/naver/login")
    ResponseEntity<Map<String, String>> naverOauthLogin();

    @PostMapping("/api/auth/oauth/naver/callback/redirect")
    ResponseEntity<TokenIssueResponseDto> naverGetToken(
        @RequestBody NaverTokenRequestDto naverTokenRequestDto);


    @PostMapping("/api/auth/user/accessToken/reissue")
    ResponseEntity<TokenIssueResponseDto> reissueToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String refreshToken);


}
