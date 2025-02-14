package sharingcalender.front.dto.calendar.response;

import java.util.List;

public record CalendarLookUpResponseDto (
    List<EventInfoResponseDto> eventInfo
){}
