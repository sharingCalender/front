package sharingcalender.front.adapter;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import sharingcalender.front.config.FeignClientConfig;
import sharingcalender.front.dto.chat.request.ChatLeaveRoomRequestDto;
import sharingcalender.front.dto.chat.response.ChatEnterRoomResponseDto;
import sharingcalender.front.dto.chat.response.ChatScrollResponseDto;

@FeignClient(name = "chat-service", url = "${gateway.url}", configuration = FeignClientConfig.class)
public interface ChatAdapter {

    @GetMapping("/api/calendar/chat/enter")
    ResponseEntity<ChatEnterRoomResponseDto> enterChatRoom(
        @RequestParam("calendarGroupId") long calendarGroupId);

    @GetMapping("/api/calendar/chat/scrollUp")
    ResponseEntity<ChatScrollResponseDto> getMessagesWhenScrollUp(
        @RequestParam("chatRoomId") long chatRoomId,
        @RequestParam("chatMessageId") long chatMessageId
    );

    @GetMapping("/api/calendar/chat/scrollDown")
    ResponseEntity<ChatScrollResponseDto> getMessagesWhenScrollDown(
        @RequestParam("chatRoomId") long chatRoomId,
        @RequestParam("chatMessageId") long chatMessageId
    );

    @GetMapping("/api/calendar/chat/unReadMessages")
    ResponseEntity<ChatScrollResponseDto> getAllMessagesWhenNewMessageInput(
        @RequestParam("chatRoomId") long chatRoomId,
        @RequestParam("chatMessageId") long chatMessageId
    );

    @PostMapping("/api/calendar/chat/leave")
    ResponseEntity<Void> leaveChatRoom(
        @RequestBody ChatLeaveRoomRequestDto chatLeaveRoomRequestDto);
}
