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
    public void issue(Long couponId) {
        Coupon coupon = couponRepository.findByIdWithPessimisticLock(couponId)
                .orElseThrow(() -> new RuntimeException("쿠폰을 찾을 수 없습니다."));

        coupon.issue();
    }
}
