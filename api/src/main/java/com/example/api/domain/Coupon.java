package com.example.api.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Coupon
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long memberId;
    private String couponName;
    private LocalDateTime createDate;

    public Coupon()
    {
    }

    public Coupon(Long memberId, String couponName, LocalDateTime createDate)
    {
        this.memberId = memberId;
        this.couponName = couponName;
        this.createDate = createDate;
    }


}
