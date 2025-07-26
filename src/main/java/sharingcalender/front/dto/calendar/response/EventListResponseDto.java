package sharingcalender.front.dto.calendar.response;

import com.fasterxml.jackson.annotation.JsonValue;
import java.util.List;

public record EventListResponseDto (
//    @JsonValue
    List<EventInfoResponseDto> eventList
){}
