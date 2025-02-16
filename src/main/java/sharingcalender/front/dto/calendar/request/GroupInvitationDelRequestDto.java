package sharingcalender.front.dto.calendar.request;

import jakarta.validation.constraints.Min;

public record GroupInvitationDelRequestDto (
    @Min(0L)
    long groupInvitationId
){}

