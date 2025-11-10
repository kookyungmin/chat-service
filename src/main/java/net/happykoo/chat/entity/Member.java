package net.happykoo.chat.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.happykoo.chat.constant.GenderType;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@Entity(name = "member")
@Table(name = "chat_member")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String email;

    private String password;

    private String nickName;

    private String name;

    @Enumerated(EnumType.STRING)
    private GenderType gender;

    private String phoneNumber;

    private LocalDate birthDate;

    private String role;

    public void updatePassword(String password, String confirmPassword, PasswordEncoder passwordEncoder) {
        if (!password.equals(confirmPassword)) {
            throw new IllegalArgumentException("패스워드가 일치하지 않습니다.");
        }
        this.password = passwordEncoder.encode(password);
    }
}
