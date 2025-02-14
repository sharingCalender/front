package sharingcalender.front.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sharingcalender.front.adapter.CalendarAdapter;
import sharingcalender.front.dto.calendar.request.CalendarGroupDeleteRequestDto;
import sharingcalender.front.dto.calendar.request.CalendarGroupRegisterRequestDto;
import sharingcalender.front.dto.calendar.request.EventChangeColorRequestDto;
import sharingcalender.front.dto.calendar.request.EventDeleteRequestDto;
import sharingcalender.front.dto.calendar.request.EventModifyRequestDto;
import sharingcalender.front.dto.calendar.request.EventRegisterRequestDto;
import sharingcalender.front.dto.calendar.response.CalendarGroupListResponseDto;
import sharingcalender.front.dto.calendar.response.CalendarLookUpResponseDto;
import sharingcalender.front.dto.calendar.response.EventRegisterResponseDto;
import sharingcalender.front.service.CalendarService;

@Service
@RequiredArgsConstructor
public class CalendarServiceImpl implements CalendarService {

    private final CalendarAdapter calendarAdapter;

    public CalendarGroupListResponseDto getGroupInfoList() {
        ResponseEntity<CalendarGroupListResponseDto> groupInfoList = calendarAdapter.getGroupInfoList();

        return groupInfoList.getBody();
    }

    public void registerGroup(CalendarGroupRegisterRequestDto calendarGroupRegisterReq) {

        calendarAdapter.registerGroup(calendarGroupRegisterReq);

    }

    public void deleteGroup(CalendarGroupDeleteRequestDto calendarGroupDeleteReq) {

        calendarAdapter.deleteGroup(calendarGroupDeleteReq);

    }

    public CalendarLookUpResponseDto getAllEventsInCalendar(long calendarGroupId, String start,
        String end) {

        return calendarAdapter.getAllEventsInCalendar(calendarGroupId, start, end).getBody();
    }

    public EventRegisterResponseDto registerEvent(EventRegisterRequestDto eventRegisterRequestDto) {
        ResponseEntity<EventRegisterResponseDto> eventRegisterResponseDto = calendarAdapter.registerEvent(
            eventRegisterRequestDto);

        return eventRegisterResponseDto.getBody();

    }

    public void modifyEvent(EventModifyRequestDto eventModifyRequestDto) {
        calendarAdapter.modifyEvent(eventModifyRequestDto);
    }

    public void deleteEvent(EventDeleteRequestDto eventDeleteRequestDto) {
        calendarAdapter.deleteEvent(eventDeleteRequestDto);
    }

    public void changeEventColor(EventChangeColorRequestDto eventChangeColorRequestDto) {
        calendarAdapter.changeEventColor(eventChangeColorRequestDto);
    }
}
