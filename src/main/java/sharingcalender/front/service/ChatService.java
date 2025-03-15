package sharingcalender.front.service;

import sharingcalender.front.dto.chat.request.ChatLeaveRoomRequestDto;
import sharingcalender.front.dto.chat.response.ChatEnterRoomResponseDto;
import sharingcalender.front.dto.chat.response.ChatScrollResponseDto;

public interface ChatService {

    ChatEnterRoomResponseDto enterChatRoom(long calendarGroupId);

    ChatScrollResponseDto getMessagesWhenScrollUp(long chatRoomId, long chatMessageId);

    ChatScrollResponseDto getMessagesWhenScrollDown(long chatRoomId, long chatMessageId);

    ChatScrollResponseDto getAllUnReadMessagesWhenNewMessageInput(long chatRoomId,
        long chatMessageId);

    void leaveChatRoom(ChatLeaveRoomRequestDto chatLeaveRoomRequestDto);
}
