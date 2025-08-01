package sharingcalender.front.service;

import sharingcalender.front.dto.TokenIssueResponseDto;
import sharingcalender.front.dto.oauth.request.GetOauthUriRequestDto;
import sharingcalender.front.dto.oauth.request.OauthTokenRequestDto;

public interface OauthService {

    String oauthLogin(GetOauthUriRequestDto getOauthUriRequestDto);

    TokenIssueResponseDto getOauthToken(OauthTokenRequestDto oauthTokenRequestDto);

}
