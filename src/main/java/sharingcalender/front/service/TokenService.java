package sharingcalender.front.service;

import jakarta.servlet.http.HttpServletResponse;
import sharingcalender.front.dto.TokenIssueResponseDto;

public interface TokenService {

    void reissueToken(String refreshToken, HttpServletResponse response);

    void addTokenToCookie(TokenIssueResponseDto tokenIssueResponseDto,
        HttpServletResponse response);
}
