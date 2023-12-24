package com.example.coupon.controller;

import com.example.coupon.service.CouponService;
import com.example.member.controller.MemberDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CouponController
{
    private CouponService couponService;

    public CouponController(CouponService couponService)
    {
        this.couponService = couponService;
    }

    @PostMapping(value = "/coupon")
    public void createCoupon(@RequestBody MemberDto memberDto)
    {
        couponService.couponCreateEvent(memberDto);
        //TODO response
    }
}
