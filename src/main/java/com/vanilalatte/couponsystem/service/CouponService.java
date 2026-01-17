package com.vanilalatte.couponsystem.service;

import com.vanilalatte.couponsystem.producer.CouponCreateProducer;
import com.vanilalatte.couponsystem.repository.CouponCountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponCountRepository couponCountRepository;
    private final CouponCreateProducer couponCreateProducer;

    public void issue(Long couponId) {
        // 1. Redis 수량 증가
        Long count = couponCountRepository.increment();

        System.out.println("현재 쿠폰 발급 순서: " + count);

        // 2. 100개 넘으면 종료
        if (count > 100) {
            System.out.println("매진됨! (Redis Count: " + count + ")");
            return;
        }

        // 3. Kafka 전송
        couponCreateProducer.create(couponId);
    }
}