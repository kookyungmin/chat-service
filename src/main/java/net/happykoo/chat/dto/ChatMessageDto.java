package net.happykoo.chat.dto;

import net.happykoo.chat.entity.Message;

import java.io.Serializable;

public record ChatMessageDto(
    String sender,
    String message
) {
    public static ChatMessageDto from(Message message) {
        return new ChatMessageDto(message.getMember().getName(), message.getText());
    }
}
