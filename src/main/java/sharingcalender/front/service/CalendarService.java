package sharingcalender.front.service;

import sharingcalender.front.dto.calendar.request.CalendarGroupDeleteRequestDto;
import sharingcalender.front.dto.calendar.request.CalendarGroupRegisterRequestDto;
import sharingcalender.front.dto.calendar.request.EventChangeColorRequestDto;
import sharingcalender.front.dto.calendar.request.EventDeleteRequestDto;
import sharingcalender.front.dto.calendar.request.EventModifyRequestDto;
import sharingcalender.front.dto.calendar.request.EventRegisterRequestDto;
import sharingcalender.front.dto.calendar.response.CalendarGroupListResponseDto;
import sharingcalender.front.dto.calendar.response.CalendarLookUpResponseDto;
import sharingcalender.front.dto.calendar.response.EventRegisterResponseDto;

public interface CalendarService {

    CalendarGroupListResponseDto getGroupInfoList();

    void registerGroup(CalendarGroupRegisterRequestDto calendarGroupRegisterReq);

    void deleteGroup(CalendarGroupDeleteRequestDto calendarGroupDeleteReq);

    CalendarLookUpResponseDto getAllEventsInCalendar(long calendarGroupId, String start,
        String end);

    EventRegisterResponseDto registerEvent(EventRegisterRequestDto eventRegisterRequestDto);

    void modifyEvent(EventModifyRequestDto eventModifyRequestDto);

    void deleteEvent(EventDeleteRequestDto eventDeleteRequestDto);

    void changeEventColor(EventChangeColorRequestDto eventChangeColorRequestDto);

}
