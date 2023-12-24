package com.example.api.repository;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ApiCouponCountRepository
{
    private final RedisTemplate<String, String> redisTemplate;

    public ApiCouponCountRepository(RedisTemplate<String, String> redisTemplate)
    {
        this.redisTemplate = redisTemplate;
    }

    public Long increment(){
        return redisTemplate.opsForValue().increment("coupon_count");
    }
}
