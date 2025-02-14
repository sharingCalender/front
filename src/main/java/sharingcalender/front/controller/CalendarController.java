package sharingcalender.front.controller;

import jakarta.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import sharingcalender.front.dto.calendar.request.CalendarGroupDeleteRequestDto;
import sharingcalender.front.dto.calendar.request.CalendarGroupRegisterRequestDto;
import sharingcalender.front.dto.calendar.request.EventChangeColorRequestDto;
import sharingcalender.front.dto.calendar.request.EventDeleteRequestDto;
import sharingcalender.front.dto.calendar.request.EventModifyRequestDto;
import sharingcalender.front.dto.calendar.request.EventRegisterRequestDto;
import sharingcalender.front.dto.calendar.response.CalendarGroupListResponseDto;
import sharingcalender.front.dto.calendar.response.CalendarLookUpResponseDto;
import sharingcalender.front.dto.calendar.response.EventInfoResponseDto;
import sharingcalender.front.dto.calendar.response.EventRegisterResponseDto;
import sharingcalender.front.exception.BadRequestException;
import sharingcalender.front.service.CalendarService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/calendar")
public class CalendarController {

    private final CalendarService calendarService;

    @GetMapping("/group")
    public String getGroupInfoList(Model model,HttpServletResponse response) {


        CalendarGroupListResponseDto groupInfoList = calendarService.getGroupInfoList();

        model.addAttribute("groupList", groupInfoList.groupInfoList());

        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");

        return "group-list";
    }

    @GetMapping("/group/register")
    public String groupRegisterPage() {

        return "group-register";
    }

    @PostMapping("/group")
    public ResponseEntity<Void> registerGroup(
        @RequestBody CalendarGroupRegisterRequestDto calendarGroupRegisterReq,
        BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new BadRequestException("Request Body Is Not Valid");
        }

        calendarService.registerGroup(calendarGroupRegisterReq);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/group")
    public ResponseEntity<Void> deleteGroup(
        @RequestBody CalendarGroupDeleteRequestDto calendarGroupDeleteRequestDto,
        BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new BadRequestException("Request Body Is Not Valid");
        }

        calendarService.deleteGroup(calendarGroupDeleteRequestDto);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/page")
    public ModelAndView calendarPage(HttpServletResponse response,
        @RequestParam("calendarGroupId") String calendarGroupId,
        @RequestParam("calendarId") String calendarId, @RequestParam("name") String name,
        @RequestParam("groupName") String groupName) {

        ModelAndView mv = new ModelAndView("calendar");

        String decodedName = new String(
            Base64.getUrlDecoder().decode(URLDecoder.decode(name, StandardCharsets.UTF_8)),
            StandardCharsets.UTF_8);

        String decodedGroupName = new String(
            Base64.getUrlDecoder().decode(URLDecoder.decode(groupName, StandardCharsets.UTF_8)),
            StandardCharsets.UTF_8);

        long decodedCalendarId = Long.parseLong(new String(
            Base64.getUrlDecoder().decode(URLDecoder.decode(calendarId, StandardCharsets.UTF_8))));
        long decodedCalendarGroupId = Long.parseLong(new String(Base64.getUrlDecoder()
            .decode(URLDecoder.decode(calendarGroupId, StandardCharsets.UTF_8))));


        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");

        mv.addObject("name", decodedName);
        mv.addObject("groupName", decodedGroupName);
        mv.addObject("calendarGroupId", decodedCalendarGroupId);
        mv.addObject("calendarId", decodedCalendarId);

        return mv;
    }

    @GetMapping("/events")
    public ResponseEntity<List<EventInfoResponseDto>> getAllEventsInCalendar(
        @RequestParam("calendarGroupId") String calendarGroupId, @RequestParam("start") String start,
        @RequestParam("end") String end) {

        if (Objects.isNull(calendarGroupId)) {
            throw new BadRequestException("Request Is Not Valid");
        }
        long decodedCalendarGroupId = Long.parseLong(new String(
            Base64.getUrlDecoder().decode(URLDecoder.decode(calendarGroupId, StandardCharsets.UTF_8))));


        if (decodedCalendarGroupId < 0) {
            throw new BadRequestException("Request Is Not Valid");
        }

        CalendarLookUpResponseDto allEventsInCalendar = calendarService.getAllEventsInCalendar(
            decodedCalendarGroupId, start, end);

        return ResponseEntity.status(HttpStatus.OK).body(allEventsInCalendar.eventInfo());

    }

    @PostMapping("/event")
    public ResponseEntity<EventRegisterResponseDto> registerEvent(
        @RequestBody EventRegisterRequestDto eventRegisterRequestDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new BadRequestException("Request Body Is Not Valid");
        }

        EventRegisterResponseDto eventRegisterResponseDto = calendarService.registerEvent(
            eventRegisterRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(eventRegisterResponseDto);
    }




    @PatchMapping("/event")
    public ResponseEntity<Void> modifyEvent(
        @RequestBody EventModifyRequestDto eventModifyRequestDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new BadRequestException("Request Body Is Not Valid");
        }

        calendarService.modifyEvent(eventModifyRequestDto);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/event/color")
    public ResponseEntity<Void> changeEventColor(
        @RequestBody EventChangeColorRequestDto eventChangeColorRequestDto,
        BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new BadRequestException("Request Body Is Not Valid");
        }

        calendarService.changeEventColor(eventChangeColorRequestDto);

        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @DeleteMapping("/event")
    public ResponseEntity<Void> deleteEvent(
        @RequestBody EventDeleteRequestDto eventDeleteRequestDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new BadRequestException("Request Body Is Not Valid");
        }

        calendarService.deleteEvent(eventDeleteRequestDto);

        return ResponseEntity.status(HttpStatus.OK).build();
    }



}
