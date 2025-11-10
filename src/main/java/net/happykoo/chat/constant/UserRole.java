package net.happykoo.chat.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
public enum UserRole {
    USER("ROLE_USER"),
    CONSULTANT("ROLE_CONSULTANT");

    @Getter
    private final String code;

    public static UserRole from(String code) {
        return Arrays.stream(UserRole.values())
                .filter(role -> role.code.equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
