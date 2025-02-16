package sharingcalender.front.adapter;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import sharingcalender.front.config.FeignClientConfig;
import sharingcalender.front.dto.TokenIssueResponseDto;
import sharingcalender.front.dto.user.request.UserLoginRequestDto;
import sharingcalender.front.dto.user.request.UserRegisterRequestDto;

@FeignClient(name = "user-service", url = "${gateway.url}",configuration = FeignClientConfig.class)
public interface UserAdapter {

    @PostMapping("/api/calendar/user/register")
    ResponseEntity<Void> registerUser(@RequestBody UserRegisterRequestDto userRegisterRequestDto);

    @PostMapping("/api/auth/login")
    ResponseEntity<TokenIssueResponseDto> loginUser(
        @RequestBody UserLoginRequestDto userLoginRequestDto);

    @PostMapping("/api/auth/logout")
    ResponseEntity<Void> logoutUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String refreshToken);


}
