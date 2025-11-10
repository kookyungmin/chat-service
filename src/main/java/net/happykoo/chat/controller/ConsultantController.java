package net.happykoo.chat.controller;

import lombok.RequiredArgsConstructor;
import net.happykoo.chat.dto.MemberDto;
import net.happykoo.chat.service.CustomUserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/consultants")
@RequiredArgsConstructor
public class ConsultantController {
    private final CustomUserDetailsService customUserDetailsService;

    @ResponseBody
    @PostMapping
    public MemberDto saveMember(@RequestBody MemberDto memberDto) {
        return customUserDetailsService.saveMember(memberDto);
    }

    @GetMapping
    public String index() {
        return "consultants/index.html";
    }
}
