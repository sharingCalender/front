package sharingcalender.front.service.impl;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sharingcalender.front.adapter.AuthAdapter;
import sharingcalender.front.dto.TokenIssueResponseDto;
import sharingcalender.front.dto.oauth.request.GetOauthUriRequestDto;
import sharingcalender.front.dto.oauth.request.OauthTokenRequestDto;
import sharingcalender.front.service.OauthService;

@Service
@RequiredArgsConstructor
public class OauthServiceImpl implements OauthService {

    private final AuthAdapter authAdapter;

    public String oauthLogin(GetOauthUriRequestDto getOauthUriRequestDto) {

        ResponseEntity<Map<String, String>> response = authAdapter.oauthLogin(getOauthUriRequestDto);

        return response.getBody().get("redirectURL");

    }

    public TokenIssueResponseDto getOauthToken(OauthTokenRequestDto oauthTokenRequestDto) {
        ResponseEntity<TokenIssueResponseDto> tokenResponse = authAdapter.getOauthToken(
            oauthTokenRequestDto);

        return tokenResponse.getBody();
    }
}
