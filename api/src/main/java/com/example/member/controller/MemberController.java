package com.example.member.controller;

import com.example.member.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController
{
    private final MemberService memberService;

    public MemberController(MemberService memberService)
    {
        this.memberService = memberService;
    }

    @PostMapping("/login")
    private void couponApply(MemberDto memberDto)
    {
        memberService.login(memberDto);
    }
}
