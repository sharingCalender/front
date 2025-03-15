package sharingcalender.front.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sharingcalender.front.adapter.ChatAdapter;
import sharingcalender.front.dto.chat.request.ChatLeaveRoomRequestDto;
import sharingcalender.front.dto.chat.response.ChatEnterRoomResponseDto;
import sharingcalender.front.dto.chat.response.ChatScrollResponseDto;
import sharingcalender.front.service.ChatService;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatAdapter chatAdapter;

    public ChatEnterRoomResponseDto enterChatRoom(long calendarGroupId) {

        ResponseEntity<ChatEnterRoomResponseDto> enterChatRoomResponse = chatAdapter.enterChatRoom(
            calendarGroupId);

        return enterChatRoomResponse.getBody();
    }

    public ChatScrollResponseDto getMessagesWhenScrollUp(long chatRoomId, long chatMessageId) {
        ResponseEntity<ChatScrollResponseDto> messagesWhenScrollUp = chatAdapter.getMessagesWhenScrollUp(
            chatRoomId, chatMessageId);

        return messagesWhenScrollUp.getBody();
    }


    public ChatScrollResponseDto getMessagesWhenScrollDown(long chatRoomId, long chatMessageId) {
        ResponseEntity<ChatScrollResponseDto> messagesWhenScrollDown = chatAdapter.getMessagesWhenScrollDown(
            chatRoomId, chatMessageId);

        return messagesWhenScrollDown.getBody();
    }

    public ChatScrollResponseDto getAllUnReadMessagesWhenNewMessageInput(long chatRoomId, long chatMessageId) {
        ResponseEntity<ChatScrollResponseDto> messagesWhenScrollDown = chatAdapter.getAllMessagesWhenNewMessageInput(
            chatRoomId, chatMessageId);

        return messagesWhenScrollDown.getBody();
    }

    public void leaveChatRoom(ChatLeaveRoomRequestDto chatLeaveRoomRequestDto) {
        chatAdapter.leaveChatRoom(chatLeaveRoomRequestDto);
    }

}
