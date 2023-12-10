package com.example.api.service;

import com.example.api.domain.Coupon;
import com.example.api.repository.CouponCountRepository;
import com.example.api.repository.CouponRepository;
import org.springframework.stereotype.Service;

@Service
public class ApplyService
{
    private final CouponRepository couponRepository;
    private final CouponCountRepository couponCountRepository;

    public ApplyService(CouponRepository couponRepository, CouponCountRepository couponCountRepository)
    {
        this.couponRepository = couponRepository;
        this.couponCountRepository = couponCountRepository;
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

        couponRepository.save(new Coupon(userId));
    }
}
