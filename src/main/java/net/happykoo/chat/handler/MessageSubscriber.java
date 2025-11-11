package net.happykoo.chat.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import net.happykoo.chat.dto.ChatMessageDto;
import net.happykoo.chat.dto.ChatRoomDto;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class MessageSubscriber implements MessageListener {
    private final SimpMessagingTemplate messagingTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        // redis 메시지 subscribe -> 도착 채널에 따라 STOMP 목적지로 라우팅
        String channel = new String(message.getChannel(), StandardCharsets.UTF_8);
        String json = new String(message.getBody(), StandardCharsets.UTF_8);

        try {
            if ("chats-updates".equals(channel)) {
                ChatRoomDto room = objectMapper.readValue(json, ChatRoomDto.class);
                messagingTemplate.convertAndSend("/sub/chats/updates", room);
                return;
            }

            if (channel.startsWith("chats-")) {
                String roomId = channel.substring("chats-".length());
                ChatMessageDto chatMessage = objectMapper.readValue(json, ChatMessageDto.class);
                messagingTemplate.convertAndSend("/sub/chats/" + roomId, chatMessage);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
