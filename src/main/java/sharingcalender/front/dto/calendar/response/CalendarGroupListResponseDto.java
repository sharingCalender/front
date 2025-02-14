package sharingcalender.front.dto.calendar.response;

import java.util.List;

public record CalendarGroupListResponseDto (

    List<CalendarGroupInfoDto> groupInfoList
) {}
