package com.example.api.service;

import com.example.api.producer.ApiCouponCreateProducer;
import com.example.api.repository.ApiCouponCountRepository;
import com.example.api.repository.ApiCouponRepository;
import com.example.member.controller.MemberDto;
import org.springframework.stereotype.Service;

@Service
public class ApplyService
{
    private final ApiCouponRepository apiCouponRepository;
    private final ApiCouponCountRepository apiCouponCountRepository;
    private final ApiCouponCreateProducer apiCouponCreateProducer;

    public ApplyService(ApiCouponRepository apiCouponRepository, ApiCouponCountRepository apiCouponCountRepository, ApiCouponCreateProducer apiCouponCreateProducer)
    {
        this.apiCouponRepository = apiCouponRepository;
        this.apiCouponCountRepository = apiCouponCountRepository;
        this.apiCouponCreateProducer = apiCouponCreateProducer;
    }

   /* public void apply(Long userId){ //DB로만 쿠폰 생성 했을 때
        long count = couponRepository.count();

        if(count > 100){
            return;
        }

        couponRepository.save(new Coupon(userId));
    }*/
     public void apply(Long userId){//Redis의 incr 명령어로 count
         Long count = apiCouponCountRepository.increment();

         if(count > 100){
            return;
        }

//        couponRepository.save(new Coupon(userId));
//         apiCouponCreateProducer.create(userId);
    }

    public void apply(MemberDto memberDto)
    {

    }
}
