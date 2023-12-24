package com.example.coupon.producer;

import com.example.member.controller.MemberDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class CouponCreateProducer
{
    private final KafkaTemplate<String, String> kafkaTemplate;

    public CouponCreateProducer(KafkaTemplate<String, String> kafkaTemplate)
    {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(MemberDto memberDto)
    {
        kafkaTemplate.send("coupon-create", memberDto.toJson());
    }
}
