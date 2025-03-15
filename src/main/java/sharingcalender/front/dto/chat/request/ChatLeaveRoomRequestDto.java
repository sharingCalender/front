package sharingcalender.front.dto.chat.request;

import jakarta.validation.constraints.Min;

public record ChatLeaveRoomRequestDto(

    @Min(0L)
    long chatRoomId
) {}
