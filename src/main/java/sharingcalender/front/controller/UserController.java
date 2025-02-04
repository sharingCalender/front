package sharingcalender.front.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;
import sharingcalender.front.annotation.RedirectByException;
import sharingcalender.front.dto.TokenIssueResponseDto;
import sharingcalender.front.dto.user.request.UserLoginRequestDto;
import sharingcalender.front.dto.user.request.UserRegisterRequestDto;
import sharingcalender.front.exception.BadRequestException;
import sharingcalender.front.exception.UnAuthorizedException;
import sharingcalender.front.service.TokenService;
import sharingcalender.front.service.UserService;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final TokenService tokenService;

    // 자바스크립트 fetch 로 요청이 올 것이고 예외가 발생하게 되면 alert 를 띄우지 redirection 은 하지 않을 거 같다.
    @PostMapping("/register")
    public ResponseEntity<Void> registerUser(
        @RequestBody @Valid UserRegisterRequestDto userRegisterReq,
        BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new BadRequestException("Register User Data Is Not Valid");
        }

        try {
            userService.registerUser(userRegisterReq);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

//    @GetMapping("/csrf")
//    public ResponseEntity<CsrfToken> getCsrfToken(HttpServletRequest request) {
//        return ResponseEntity.status(HttpStatus.OK)
//            .body((CsrfToken) request.getAttribute(CsrfToken.class.getName()));
//    }

    @GetMapping("/login")
    public String loginPage() {

        return "login";
    }

    //TODO 로그인 페이지가 아니라 오류페이지를 만들어서 리디렉션해야할 듯 ex) 로그인에 실패했습니다. 잠시후 다시 시도해주세요
    @RedirectByException(exception = UnAuthorizedException.class, title = "Login Fail", redirect = "/login")
    @PostMapping("/login")
    public String loginUser(@RequestBody UserLoginRequestDto userLoginReq,
        HttpServletResponse response) {

        TokenIssueResponseDto tokenIssueResponse = userService.loginUser(userLoginReq);

        // 쿠키에 담아야함..
        tokenService.addTokenToCookie(tokenIssueResponse, response);

        return "test";
    }

    //TODO 로그아웃시 문제가 발생했습니다. 페이지 띄워야 할 듯? 로그인이랑 같은 페이지 쓰되 메시지만 갈아끼우면 될 듯
    @RedirectByException(exception = UnAuthorizedException.class, title = "Logout Fail", redirect = "/")
    @PostMapping("/logout")
    public String logoutUser(HttpServletRequest request, HttpServletResponse response) {

        Cookie refreshToken = WebUtils.getCookie(request, "refreshToken");

        userService.logoutUser(refreshToken.getValue());

        tokenService.removeTokenFromCookie(response);

        return "login";
    }

    @GetMapping("/calendar")
    public String calendarTest() {
        return "calendar";
    }
}
