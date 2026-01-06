package com.vanilalatte.couponsystem.repository;

import com.vanilalatte.couponsystem.domain.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
