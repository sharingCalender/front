package sharingcalender.front.dto.calendar.response;

public record CalendarGroupInfoDto (
    String name,
    String groupName,
    long calendarGroupId,
    long calendarId
){}
