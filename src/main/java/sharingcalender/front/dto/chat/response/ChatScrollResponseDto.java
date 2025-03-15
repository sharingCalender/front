package sharingcalender.front.dto.chat.response;

import java.util.List;

public record ChatScrollResponseDto(
    List<ChatMessageResponseDto> messages
){}
