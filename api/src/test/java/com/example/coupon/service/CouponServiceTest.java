package com.example.coupon.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.member.controller.MemberDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CouponServiceTest
{
    @Autowired
    private CouponService couponService;

    @Test
    void couponCreateEvent()
    {
        //when
        MemberDto dto = new MemberDto(1L, "member_1");

        couponService.couponCreateEvent(dto);

    }
}