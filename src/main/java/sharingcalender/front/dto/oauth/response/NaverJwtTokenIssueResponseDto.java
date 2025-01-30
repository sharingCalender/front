package sharingcalender.front.dto.oauth.response;

public record NaverJwtTokenIssueResponseDto(

    String accessToken,
    String refreshToken
){}
