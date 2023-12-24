package com.example.member.repository;

import com.example.api.domain.ApiCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<ApiCoupon, Long>, MemberCustomRepository
{
}
