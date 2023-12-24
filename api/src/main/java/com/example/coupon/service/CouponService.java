package com.example.coupon.service;

import com.example.coupon.producer.CouponCreateProducer;
import com.example.coupon.repository.CouponCountRepository;
import com.example.coupon.repository.CouponRepository;
import com.example.member.controller.MemberDto;
import org.springframework.stereotype.Service;

@Service
public class CouponService
{
    private final CouponRepository couponRepository;
    private final CouponCountRepository couponCountRepository;
    private final CouponCreateProducer couponCreateProducer;


    public CouponService(CouponRepository couponRepository, CouponCountRepository couponCountRepository, CouponCreateProducer couponCreateProducer)
    {
        this.couponRepository = couponRepository;
        this.couponCountRepository = couponCountRepository;
        this.couponCreateProducer = couponCreateProducer;
    }

    public void couponCreateEvent(MemberDto memberDto)
    {
        Long count = couponCountRepository.increment();

        if(count > 100)
        {
            //TODO 쿠폰 생성 실패
            return;
        }
        couponCreateProducer.send(memberDto);

    }
}
