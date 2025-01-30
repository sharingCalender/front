package sharingcalender.front.adapter;

import java.util.Map;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import sharingcalender.front.config.FeignClientConfig;
import sharingcalender.front.dto.oauth.request.NaverTokenRequestDto;
import sharingcalender.front.dto.oauth.response.NaverJwtTokenIssueResponseDto;

@FeignClient(name = "auth-service" , url = "${gateway.url}", configuration = FeignClientConfig.class)
public interface NaverOAuthAdapter {

    @PostMapping("/api/auth/oauth/naver/login")
    ResponseEntity<Map<String, String>> naverOauthLogin();

    @PostMapping("/api/auth/oauth/naver/callback/redirect")
    ResponseEntity<NaverJwtTokenIssueResponseDto> naverGetToken(@RequestBody NaverTokenRequestDto naverTokenRequestDto);



}
