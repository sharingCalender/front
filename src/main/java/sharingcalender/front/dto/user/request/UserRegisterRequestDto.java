package sharingcalender.front.dto.user.request;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record UserRegisterRequestDto(

    @NotBlank(message = "이름을 입력하세요")
    String name,

    @NotBlank(message = "이메일을 입력하세요")
    String email,

    @NotBlank(message = "휴대폰 번호를 입력해주세요")
    @Length(min = 8, max = 30)
    String mobile,

    @NotBlank(message = "아이디를 입력해주세요")
    String username,

    String password,

    String provider

){}
