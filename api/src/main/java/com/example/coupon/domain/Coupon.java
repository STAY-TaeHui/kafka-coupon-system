package com.example.coupon.domain;

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
    private String couponName;
    private LocalDateTime createDate;

    protected Coupon()
    {
    }

    public Coupon(String couponName, LocalDateTime createDate)
    {
        this.couponName = couponName;
        this.createDate = createDate;
    }

    public Long getId()
    {
        return id;
    }

    public String getCouponName()
    {
        return couponName;
    }

    public LocalDateTime getCreateDate()
    {
        return createDate;
    }
}
