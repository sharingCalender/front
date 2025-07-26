package sharingcalender.front.service;

import java.util.List;
import sharingcalender.front.dto.calendar.request.CalendarGroupDeleteRequestDto;
import sharingcalender.front.dto.calendar.request.CalendarGroupRegisterRequestDto;
import sharingcalender.front.dto.calendar.request.EventChangeColorRequestDto;
import sharingcalender.front.dto.calendar.request.EventDeleteRequestDto;
import sharingcalender.front.dto.calendar.request.EventModifyRequestDto;
import sharingcalender.front.dto.calendar.request.EventRegisterRequestDto;
import sharingcalender.front.dto.calendar.request.GroupInvitationAcceptRequestDto;
import sharingcalender.front.dto.calendar.request.GroupInvitationDelRequestDto;
import sharingcalender.front.dto.calendar.request.GroupInvitationSaveRequestDto;
import sharingcalender.front.dto.calendar.response.CalendarGroupListResponseDto;
import sharingcalender.front.dto.calendar.response.CalendarLookUpResponseDto;
import sharingcalender.front.dto.calendar.response.EventListResponseDto;
import sharingcalender.front.dto.calendar.response.EventRegisterResponseDto;
import sharingcalender.front.dto.calendar.response.GroupInvitationInfo;

public interface CalendarService {

    CalendarGroupListResponseDto getGroupInfoList();

    void registerGroup(CalendarGroupRegisterRequestDto calendarGroupRegisterReq);

    void deleteGroup(CalendarGroupDeleteRequestDto calendarGroupDeleteReq);

    EventListResponseDto getAllEventsInCalendar(long calendarGroupId, String start,
        String end);

    EventRegisterResponseDto registerEvent(EventRegisterRequestDto eventRegisterRequestDto);

    void modifyEvent(EventModifyRequestDto eventModifyRequestDto);

    void deleteEvent(EventDeleteRequestDto eventDeleteRequestDto);

    void changeEventColor(EventChangeColorRequestDto eventChangeColorRequestDto);

    List<GroupInvitationInfo> getInvitationList();

    void saveGroupInvitation(GroupInvitationSaveRequestDto groupInvitationSaveRequestDto);

    void saveWhenInvitationAccepted(
        GroupInvitationAcceptRequestDto groupInvitationAcceptRequestDto);

    void deleteGroupInvitation(GroupInvitationDelRequestDto groupInvitationDelRequestDto);

}
