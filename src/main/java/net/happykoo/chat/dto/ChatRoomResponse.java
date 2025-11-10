package net.happykoo.chat.dto;

import net.happykoo.chat.entity.Room;

import java.time.LocalDateTime;

public record ChatRoomResponse(
    Long id,
    String title,
    Integer memberCount,
    boolean hasNewMessage,
    LocalDateTime createdAt
) {
    public static ChatRoomResponse from(Room room) {
        return from(room, false);
    }

    public static ChatRoomResponse from(Room room, boolean hasNewMessage) {
        return new ChatRoomResponse(room.getId(), room.getTitle(), room.getMemberSet().size(), hasNewMessage, room.getCreatedAt());
    }
}
