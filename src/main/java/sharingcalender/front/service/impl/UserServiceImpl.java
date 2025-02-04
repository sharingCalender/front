package sharingcalender.front.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sharingcalender.front.adapter.UserAdapter;
import sharingcalender.front.dto.TokenIssueResponseDto;
import sharingcalender.front.dto.user.request.UserLoginRequestDto;
import sharingcalender.front.dto.user.request.UserRegisterRequestDto;
import sharingcalender.front.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserAdapter userAdapter;

    @Override
    public void registerUser(UserRegisterRequestDto userRegisterRequestDto) {

        userAdapter.registerUser(userRegisterRequestDto);
    }

    public TokenIssueResponseDto loginUser(UserLoginRequestDto userLoginRequestDto) {

        ResponseEntity<TokenIssueResponseDto> tokenIssueResponse = userAdapter.loginUser(
            userLoginRequestDto);

        return tokenIssueResponse.getBody();

    }


    public void logoutUser(String refreshToken) {

        userAdapter.logoutUser("Bearer " + refreshToken);

    }
}
