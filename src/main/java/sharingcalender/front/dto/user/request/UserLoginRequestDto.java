package sharingcalender.front.dto.user.request;

import jakarta.validation.constraints.NotBlank;

public record UserLoginRequestDto (
    @NotBlank
    String username,
    @NotBlank
    String password
){}


