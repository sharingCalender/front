package sharingcalender.front.service.impl;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sharingcalender.front.adapter.AuthAdapter;
import sharingcalender.front.dto.TokenIssueResponseDto;
import sharingcalender.front.service.TokenService;
import sharingcalender.front.util.TokenUtil;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final AuthAdapter authAdapter;

    @Value("${spring.jwt.token.access-expiration-time}")
    private int accessExpirationTime;

    @Value("${spring.jwt.token.refresh-expiration-time}")
    private int refreshExpirationTime;

    public void reissueToken(String refreshToken, HttpServletResponse response) {
        try {
            ResponseEntity<TokenIssueResponseDto> tokenIssue = authAdapter.reissueToken(
                "Bearer " + refreshToken);

            // 응답에 addCookie 해야함.
            TokenUtil.addTokenToCookie(tokenIssue.getBody(), response, accessExpirationTime,
                refreshExpirationTime);

        } catch (Exception e) {

        }

    }

    public void addTokenToCookie(TokenIssueResponseDto tokenIssueResponseDto,
        HttpServletResponse response) {

        TokenUtil.addTokenToCookie(tokenIssueResponseDto, response, accessExpirationTime,
            refreshExpirationTime);
    }
}
