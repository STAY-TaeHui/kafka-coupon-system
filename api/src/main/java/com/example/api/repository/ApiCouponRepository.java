package com.example.api.repository;

import com.example.api.domain.ApiCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiCouponRepository extends JpaRepository<ApiCoupon, Long>
{
}
