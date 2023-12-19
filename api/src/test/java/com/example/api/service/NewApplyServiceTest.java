package com.example.api.service;

import com.example.member.controller.MemberDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NewApplyServiceTest
{
    @Autowired
    private ApplyService applyService;

    @Test
    void apply(){
        //when
        //TODO Member가 쿠폰을 발급
        MemberDto memberDto = new MemberDto(1L, "member_1");
        applyService.apply(memberDto);

        //given

        //then
    }

}