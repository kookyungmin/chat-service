package net.happykoo.chat.dto;

import lombok.Builder;
import net.happykoo.chat.constant.GenderType;
import net.happykoo.chat.entity.Member;

import java.time.LocalDate;

@Builder
public record MemberDto(
     Long id,
     String email,
     String nickName,
     String name,
     String password,
     String confirmPassword,
     GenderType gender,
     String phoneNumber,
     LocalDate birthDate,
     String role
) {
    public static MemberDto from(Member member) {
        return MemberDto.builder()
                .id(member.getId())
                .name(member.getName())
                .nickName(member.getNickName())
                .birthDate(member.getBirthDate())
                .phoneNumber(member.getPhoneNumber())
                .email(member.getEmail())
                .gender(member.getGender())
                .role(member.getRole())
                .build();
    }

    public Member toEntity() {
        return Member.builder()
                .id(id)
                .name(name)
                .nickName(nickName)
                .birthDate(birthDate)
                .phoneNumber(phoneNumber)
                .email(email)
                .gender(gender)
                .role(role)
                .build();
    }
}
