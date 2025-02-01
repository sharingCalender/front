package sharingcalender.front.adapter;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import sharingcalender.front.dto.TokenIssueResponseDto;
import sharingcalender.front.dto.user.request.UserLoginRequestDto;
import sharingcalender.front.dto.user.request.UserRegisterRequestDto;

@FeignClient(name = "user-service", url = "${gateway.url")
public interface UserAdapter {

    @PostMapping("/api/calender/user/register")
    ResponseEntity<Void> registerUser(@RequestBody UserRegisterRequestDto userRegisterRequestDto);

    @PostMapping("/api/auth/login")
    ResponseEntity<TokenIssueResponseDto> loginUser(
        @RequestBody UserLoginRequestDto userLoginRequestDto);

}
