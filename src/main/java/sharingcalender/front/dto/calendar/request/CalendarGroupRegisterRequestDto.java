package sharingcalender.front.dto.calendar.request;

import jakarta.validation.constraints.NotBlank;

public record CalendarGroupRegisterRequestDto(
    @NotBlank
    String groupName

){}
