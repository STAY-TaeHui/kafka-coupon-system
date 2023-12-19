package com.example.api.service;

import static org.assertj.core.api.Assertions.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.example.api.repository.CouponRepository;
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

        //문제!!
        //Consumer에서는 이벤트를 받았으나, DB에 아직 쿠폰이 모두 생성되지 않았으므로,
        //DB에 쿠폰이 저장 될 시간을 벌어준다.

        //!! DB에 부하는 줄여줄 수 있으나, 데이터 간에 시간 텀이 발생한다.
        Thread.sleep(10000);

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

    // 3. Redis로 해결했을 때 문제
    // -> Redis의 처리는 빠르지만, RDB에 쿠폰을 생성하는 요청이 10000건이 들어오고,
    // MySQL에서 1분에 처리할 수 있는 쿠폰의 개수는 100개라고 하면, 뒤에 들어오는 다른 요청들이
    // 지연되게 되고, DB에 부하가 갈 수 밖에 없다.
}