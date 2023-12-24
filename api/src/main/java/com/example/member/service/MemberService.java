package com.example.member.service;

import com.example.coupon.repository.CouponCountRepository;
import com.example.member.controller.MemberDto;
import com.example.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService
{
    private final MemberRepository memberRepository;
//    private final CouponCreateProducer couponCreateProducer;

    public MemberService(MemberRepository memberRepository, CouponCountRepository couponCountRepository)
    {
        this.memberRepository = memberRepository;
    }

    public void login(MemberDto memberDto)
    {
        //TODO LOGIN 구현.
        // Login 할 member가 없으면, 생성 후 로그인 구현
    }
}
