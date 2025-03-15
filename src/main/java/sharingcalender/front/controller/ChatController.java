package sharingcalender.front.controller;

import jakarta.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import sharingcalender.front.dto.chat.request.ChatLeaveRoomRequestDto;
import sharingcalender.front.dto.chat.response.ChatEnterRoomResponseDto;
import sharingcalender.front.dto.chat.response.ChatScrollResponseDto;
import sharingcalender.front.exception.BadRequestException;
import sharingcalender.front.service.ChatService;

@RequestMapping("/chat")
@Controller
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @Value("${websocket.url}")
    private String websocketURL;

    @GetMapping("/enter")
    public ModelAndView getChatPage(@RequestParam("calendarGroupId") String calendarGroupId) {
        if (calendarGroupId.isBlank()) {
            throw new BadRequestException("request param is wrong");
        }

        long decodedCalendarGroupId = decodeFromStringToLong(calendarGroupId);
        ChatEnterRoomResponseDto chatEnterRoomResponseDto = chatService.enterChatRoom(
            decodedCalendarGroupId);

        ModelAndView mv = new ModelAndView("chat");
        mv.addObject("email", chatEnterRoomResponseDto.email());
        mv.addObject("roomId", chatEnterRoomResponseDto.roomId());
        mv.addObject("messages", chatEnterRoomResponseDto.messages());
        mv.addObject("unReadMessageId", chatEnterRoomResponseDto.unReadMessageId());
        mv.addObject("groupName", chatEnterRoomResponseDto.groupName());
        mv.addObject("name", chatEnterRoomResponseDto.name());
        mv.addObject("websocketURL", websocketURL);
        return mv;
    }

    @GetMapping("/scrollUp")
    public ResponseEntity<ChatScrollResponseDto> getMessageListWhenScrollUp(
        @RequestParam("roomId") String chatRoomId,
        @RequestParam("messageId") String chatMessageId
        ) {

        if (chatRoomId.isBlank()) {
            throw new BadRequestException("request param is wrong");
        }
        if (chatMessageId.isBlank()) {
            throw new BadRequestException("request param is wrong");
        }

        long decodedChatRoomId = decodeFromStringToLong(chatRoomId);
        long decodedChatMessageId = decodeFromStringToLong(chatMessageId);

        ChatScrollResponseDto messagesWhenScrollUp = chatService.getMessagesWhenScrollUp(
            decodedChatRoomId, decodedChatMessageId);

        return ResponseEntity.status(HttpStatus.OK).body(messagesWhenScrollUp);
    }

    @GetMapping("/scrollDown")
    public ResponseEntity<ChatScrollResponseDto> getMessageListWhenScrollDown(
        @RequestParam("roomId") String chatRoomId,
        @RequestParam("messageId") String chatMessageId
    ) {

        if (chatRoomId.isBlank()) {
            throw new BadRequestException("request param is wrong");
        }
        if (chatMessageId.isBlank()) {
            throw new BadRequestException("request param is wrong");
        }

        long decodedChatRoomId = decodeFromStringToLong(chatRoomId);
        long decodedChatMessageId = decodeFromStringToLong(chatMessageId);

        ChatScrollResponseDto messagesWhenScrollDown = chatService.getMessagesWhenScrollDown(
            decodedChatRoomId, decodedChatMessageId);

        return ResponseEntity.status(HttpStatus.OK).body(messagesWhenScrollDown);
    }

    @GetMapping("/unReadMessages")
    public ResponseEntity<ChatScrollResponseDto> getAllMessageListWhenNewMessageInput(
        @RequestParam("roomId") String chatRoomId,
        @RequestParam("messageId") String chatMessageId
    ) {

        if (chatRoomId.isBlank()) {
            throw new BadRequestException("request param is wrong");
        }
        if (chatMessageId.isBlank()) {
            throw new BadRequestException("request param is wrong");
        }

        long decodedChatRoomId = decodeFromStringToLong(chatRoomId);
        long decodedChatMessageId = decodeFromStringToLong(chatMessageId);

        ChatScrollResponseDto allUnReadMessages = chatService.getAllUnReadMessagesWhenNewMessageInput(
            decodedChatRoomId, decodedChatMessageId);

        return ResponseEntity.status(HttpStatus.OK).body(allUnReadMessages);
    }

    @PostMapping("/leave")
    public ResponseEntity<Void> leaveChatRoom(
        @RequestBody ChatLeaveRoomRequestDto chatLeaveRoomRequestDto) {

        chatService.leaveChatRoom(chatLeaveRoomRequestDto);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    private long decodeFromStringToLong(String encodedString) {

        return Long.parseLong(new String(Base64.getUrlDecoder()
            .decode(URLDecoder.decode(encodedString, StandardCharsets.UTF_8)),
            StandardCharsets.UTF_8));
    }

}
