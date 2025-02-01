package sharingcalender.front.dto;

public record TokenIssueResponseDto(

    String accessToken,
    String refreshToken
){}
