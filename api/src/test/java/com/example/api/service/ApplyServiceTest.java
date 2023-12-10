package com.example.api.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.example.api.repository.CouponRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplyServiceTest
{
    @Autowired
    private ApplyService applyService;

    @Autowired
    private CouponRepository couponRepository;

    @Test
    void 한번에_한_쿠폰만_발급()
    {
        applyService.apply(1L);

        long count = couponRepository.count();

        assertThat(count).isEqualTo(1);
    }

    @Test
    void 여러명이_한번에_응모() throws InterruptedException
    {
        int threadCount = 1000;
        ExecutorService executorService = Executors.newFixedThreadPool(32);

        // 모든 Thread들의 수행이 끝날때 까지 대기
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for(int i=0; i<threadCount; i++){
            long userId = i;
            executorService.submit(()->{
                try
                {
                    applyService.apply(userId);
                }finally
                {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();

        long count = couponRepository.count();

        assertThat(count).isEqualTo(100);
        //FAIL
        //레이스 컨디션 : 두 개 이상의 스레드가 공유 데이터에 접근하고, 동시에 작업을 하려 할 때 발생하는 문제.
        //
    }

    //레이스 컨디션 해결방법
    // 1. JAVA의 Synchronized - 서버가 여러대가 된다면 다시 발생할 가능성이 있음.
    // 2. MySQL + Redis 로 LOCK 걸기 - 쿠폰 개수부터 쿠폰 생성까지 LOCK을 걸어야 함. -> LOCK의 구간이 길어짐...
    // 3. Redis
    // incr 명령어 : key에 대한 value를 1씩 증가시켜줌, 성능도 굉장히 빠름
    // 싱글스레드 기반으로 동작하여 레이스 컨디션을 해결할 수 있음
}