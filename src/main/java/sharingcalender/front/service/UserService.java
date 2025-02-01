package sharingcalender.front.service;

import sharingcalender.front.dto.TokenIssueResponseDto;
import sharingcalender.front.dto.user.request.UserLoginRequestDto;
import sharingcalender.front.dto.user.request.UserRegisterRequestDto;

public interface UserService {

    void registerUser(UserRegisterRequestDto userRegisterRequestDto);

    TokenIssueResponseDto loginUser(UserLoginRequestDto userLoginRequestDto);

}
