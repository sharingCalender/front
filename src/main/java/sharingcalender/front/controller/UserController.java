package sharingcalender.front.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;
import sharingcalender.front.dto.TokenIssueResponseDto;
import sharingcalender.front.dto.user.request.UserLoginRequestDto;
import sharingcalender.front.dto.user.request.UserRegisterRequestDto;
import sharingcalender.front.exception.BadRequestException;
import sharingcalender.front.service.TokenService;
import sharingcalender.front.service.UserService;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final TokenService tokenService;

    @GetMapping("/register")
    public String registerForm() {
        return "register";
    }

    // 자바스크립트 fetch 로 요청이 올 것이고 예외가 발생하게 되면 alert 를 띄우지 redirection 은 하지 않을 거 같다.
    @PostMapping("/register")
    public ResponseEntity<Void> registerUser(
        @RequestBody @Valid UserRegisterRequestDto userRegisterReq,
        BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new BadRequestException("Register User Data Is Not Valid");
        }

        userService.registerUser(userRegisterReq);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @GetMapping("/login")
    public String loginPage() {

        return "login";
    }


//    @RedirectByException(exception = {UnAuthorizedException.class, ResourceNotFoundException.class,
//        BadRequestException.class}, title = "Login Fail", redirect = "/user/login")
    @PostMapping("/login")
    public ResponseEntity<Void> loginUser(@RequestBody UserLoginRequestDto userLoginReq,
        HttpServletResponse response) {

        TokenIssueResponseDto tokenIssueResponse = userService.loginUser(userLoginReq);

        // 쿠키에 담아야함..
        tokenService.addTokenToCookie(tokenIssueResponse, response);

        return ResponseEntity.status(HttpStatus.OK).build();

    }


//    @RedirectByException(exception = {UnAuthorizedException.class,
//        BadRequestException.class}, title = "Logout Fail", redirect = "/")
    @PostMapping("/logout")
    public ResponseEntity<Void> logoutUser(HttpServletRequest request,
        HttpServletResponse response) {

        Cookie refreshToken = WebUtils.getCookie(request, "refreshToken");

        userService.logoutUser(refreshToken.getValue());

        tokenService.removeTokenFromCookie(response);

        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
