package com.vanilalatte.couponsystem.service;

import lombok.RequiredArgsConstructor;
import com.vanilalatte.couponsystem.domain.Coupon;
import com.vanilalatte.couponsystem.repository.CouponRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CouponService {
    private final CouponRepository couponRepository;

    @Transactional
    public synchronized void issue(Long couponId) {
        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(() -> new RuntimeException("쿠폰을 찾을 수 없습니다."));

        coupon.issue(); // 이 안에서 수량 체크 후 증가
    }
}
