package net.happykoo.chat.repository;

import net.happykoo.chat.entity.Message;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {
    List<Message> findAllByRoomId(Long roomId);
    boolean existsByRoomIdAndCreatedAtAfter(Long roomId, LocalDateTime lastCheckedAt);
}
