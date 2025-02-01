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
        try {
            userAdapter.registerUser(userRegisterRequestDto);

        } catch (Exception e) {

        }
    }

    public TokenIssueResponseDto loginUser(UserLoginRequestDto userLoginRequestDto) {
        try {
            ResponseEntity<TokenIssueResponseDto> tokenIssueResponse = userAdapter.loginUser(
                userLoginRequestDto);

            return tokenIssueResponse.getBody();

        } catch (Exception e) {
            return null;
        }
    }
}
