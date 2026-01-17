package com.vanilalatte.couponsystem.service;

import lombok.RequiredArgsConstructor;
import com.vanilalatte.couponsystem.domain.Coupon;
import com.vanilalatte.couponsystem.repository.CouponRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CouponIssueService {
    private final CouponRepository couponRepository;

    @Transactional
    public void issue(Long couponId) {
        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(() -> new IllegalArgumentException("쿠폰이 존재하지 않습니다."));

        coupon.issue();
    }
}
