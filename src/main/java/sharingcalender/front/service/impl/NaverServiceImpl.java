package sharingcalender.front.service.impl;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sharingcalender.front.adapter.AuthAdapter;
import sharingcalender.front.dto.TokenIssueResponseDto;
import sharingcalender.front.dto.oauth.request.NaverTokenRequestDto;

@Service
@RequiredArgsConstructor
public class NaverServiceImpl {

    private final AuthAdapter authAdapter;

    //TODO feing Client 예외처리..

    public String naverOauthLogin() {

        ResponseEntity<Map<String, String>> response = authAdapter.naverOauthLogin();

        return response.getBody().get("redirectURL");

    }

    public TokenIssueResponseDto naverGetToken(NaverTokenRequestDto naverTokenRequestDto) {
        ResponseEntity<TokenIssueResponseDto> tokenResponse = authAdapter.naverGetToken(
            naverTokenRequestDto);

        return tokenResponse.getBody();
    }
}
