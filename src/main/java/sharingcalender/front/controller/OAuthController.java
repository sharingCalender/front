package sharingcalender.front.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sharingcalender.front.adapter.AuthAdapter;
import sharingcalender.front.annotation.RedirectByException;
import sharingcalender.front.dto.oauth.request.GetOauthUriRequestDto;
import sharingcalender.front.dto.oauth.request.OauthTokenRequestDto;
import sharingcalender.front.dto.TokenIssueResponseDto;
import sharingcalender.front.exception.AuthenticationException;
import sharingcalender.front.exception.BadRequestException;
import sharingcalender.front.exception.UnAuthorizedException;
import sharingcalender.front.service.OauthService;
import sharingcalender.front.service.TokenService;
import sharingcalender.front.service.impl.OauthServiceImpl;

@Controller
@RequestMapping("/oauth")
@RequiredArgsConstructor
public class OAuthController {

    private final OauthService oauthService;

    private final TokenService tokenService;

    private static final String PROVIDER_NAVER = "NAVER";

    @GetMapping("/login")
    public String oauthLogin(@RequestParam("provider") String provider) {
        return oauthService.oauthLogin(new GetOauthUriRequestDto(provider));
    }

    //TODO 로그인 실패페이지로 리디렉션
    @RedirectByException(
        exception = {BadRequestException.class, UnAuthorizedException.class,
            AuthenticationException.class},
        title = "네이버 로그인 실패",
        redirect = "/user/login")
    @GetMapping("/naver/callback")
    public String naverGetToken(@RequestParam("code") String code,
        @RequestParam("state") String state, HttpServletResponse response) {

        TokenIssueResponseDto tokenResponse = oauthService.getOauthToken(
            new OauthTokenRequestDto(code, state, PROVIDER_NAVER));

        tokenService.addTokenToCookie(tokenResponse, response);

        return "redirect:/";

    }



}
