package com.example.api.controller;

import com.example.api.service.ApplyService;
import com.example.member.controller.MemberDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ApiController
{
    private final ApplyService applyService;

    public ApiController(ApplyService applyService)
    {
        this.applyService = applyService;
    }

    @PostMapping("/apply")
    private void couponApply(MemberDto memberDto)
    {
        applyService.apply(memberDto.getMemberId());
    }
}
