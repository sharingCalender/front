package sharingcalender.front.dto.oauth.request;

import jakarta.validation.constraints.NotBlank;

public record NaverTokenRequestDto(
    @NotBlank
    String code,

    @NotBlank
    String state
){}