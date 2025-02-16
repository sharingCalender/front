package sharingcalender.front.adapter;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import sharingcalender.front.config.FeignClientConfig;
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
import sharingcalender.front.dto.calendar.response.EventRegisterResponseDto;
import sharingcalender.front.dto.calendar.response.GroupInvitationInfoListResponseDto;

@FeignClient(name = "calendar-service", url = "${gateway.url}", configuration = FeignClientConfig.class)
public interface CalendarAdapter {

    @GetMapping("/api/calendar/group")
    ResponseEntity<CalendarGroupListResponseDto> getGroupInfoList();

    @PostMapping("/api/calendar/group")
    ResponseEntity<Void> registerGroup(
        @RequestBody CalendarGroupRegisterRequestDto calendarGroupRegisterRequestDto);

    @DeleteMapping("/api/calendar/group")
    ResponseEntity<Void> deleteGroup(
        @RequestBody CalendarGroupDeleteRequestDto calendarGroupDeleteRequestDto);

    @GetMapping("/api/calendar/event")
    ResponseEntity<CalendarLookUpResponseDto> getAllEventsInCalendar(
        @RequestParam("calendarGroupId") long calendarGroupId, @RequestParam("start") String start,
        @RequestParam("end") String end);

    @PostMapping("/api/calendar/event")
    ResponseEntity<EventRegisterResponseDto> registerEvent(
        @RequestBody EventRegisterRequestDto eventRegisterRequestDto);

    @PatchMapping("/api/calendar/event")
    ResponseEntity<Void> modifyEvent(@RequestBody EventModifyRequestDto eventModifyRequestDto);

    @PatchMapping("/api/calendar/event/color")
    ResponseEntity<Void> changeEventColor(
        @RequestBody EventChangeColorRequestDto eventChangeColorRequestDto);


    @DeleteMapping("/api/calendar/event")
    ResponseEntity<Void> deleteEvent(@RequestBody EventDeleteRequestDto eventDeleteRequestDto);

    @GetMapping("/api/calendar/group/invitation")
    ResponseEntity<GroupInvitationInfoListResponseDto> getInvitationList();

    @PostMapping("/api/calendar/group/invitation")
    ResponseEntity<Void> saveGroupInvitation(
        @RequestBody GroupInvitationSaveRequestDto groupInvitationSaveRequestDto);

    @PostMapping("/api/calendar/group/invitation/accept")
    ResponseEntity<Void> saveWhenInvitationAccepted(
        @RequestBody GroupInvitationAcceptRequestDto groupInvitationAcceptRequestDto);

    @DeleteMapping("/api/calendar/group/invitation")
    ResponseEntity<Void> deleteGroupInvitation(
        @RequestBody GroupInvitationDelRequestDto groupInvitationDelRequestDto);
}
