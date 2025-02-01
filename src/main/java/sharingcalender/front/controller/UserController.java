package sharingcalender.front.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @PostMapping("/register")
    public String registerUser(
        @RequestBody @Valid UserRegisterRequestDto userRegisterReq,
        BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new BadRequestException("Register User Data Is Not Valid");
        }

        userService.registerUser(userRegisterReq);

        return "test";
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody UserLoginRequestDto userLoginReq,
        HttpServletResponse response) {

        TokenIssueResponseDto tokenIssueResponse = userService.loginUser(userLoginReq);

        // 쿠키에 담아야함..
        tokenService.addTokenToCookie(tokenIssueResponse, response);

        return "test";
    }
}
