package com.example.member.service;

import com.example.member.producer.CouponCreateProducer;
import com.example.member.repository.CouponCountRepository;
import com.example.member.repository.CouponRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService
{
    private final CouponRepository couponRepository;
    private final CouponCountRepository couponCountRepository;
    private final CouponCreateProducer couponCreateProducer;

    public MemberService(CouponRepository couponRepository, CouponCountRepository couponCountRepository, CouponCreateProducer couponCreateProducer)
    {
        this.couponRepository = couponRepository;
        this.couponCountRepository = couponCountRepository;
        this.couponCreateProducer = couponCreateProducer;
    }

   /* public void apply(Long userId){ //DB로만 쿠폰 생성 했을 때
        long count = couponRepository.count();

        if(count > 100){
            return;
        }

        couponRepository.save(new Coupon(userId));
    }*/
     public void apply(Long userId){//Redis의 incr 명령어로 count
         Long count = couponCountRepository.increment();

         if(count > 100){
            return;
        }

//        couponRepository.save(new Coupon(userId));
         couponCreateProducer.create(userId);
    }
}
