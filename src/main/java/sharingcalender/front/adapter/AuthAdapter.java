package sharingcalender.front.adapter;

import java.util.Map;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import sharingcalender.front.config.FeignClientConfig;
import sharingcalender.front.dto.oauth.request.OauthTokenRequestDto;
import sharingcalender.front.dto.TokenIssueResponseDto;
import sharingcalender.front.dto.oauth.request.GetOauthUriRequestDto;

@FeignClient(name = "auth-service", url = "${gateway.url}", configuration = FeignClientConfig.class)
public interface AuthAdapter {

    @PostMapping("/api/auth/oauth/login")
    ResponseEntity<Map<String, String>> oauthLogin(@RequestBody GetOauthUriRequestDto getOauthUriRequestDto);

    @PostMapping("/api/auth/oauth/callback/redirect")
    ResponseEntity<TokenIssueResponseDto> getOauthToken(
        @RequestBody OauthTokenRequestDto oauthTokenRequestDto);


    @PostMapping("/api/auth/user/accessToken/reissue")
    ResponseEntity<TokenIssueResponseDto> reissueToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String refreshToken);


}
