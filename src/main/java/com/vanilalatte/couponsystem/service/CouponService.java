package com.vanilalatte.couponsystem.service;

import lombok.RequiredArgsConstructor;
import com.vanilalatte.couponsystem.domain.Coupon;
import com.vanilalatte.couponsystem.repository.CouponRepository;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class CouponService {
    private final CouponRepository couponRepository;
    private final RedissonClient redissonClient;

    public void issue(Long couponId) {
        String lockName = "conpon_lock:" + couponId;
        RLock lock = redissonClient.getLock(lockName);

        try {
            boolean available = lock.tryLock(10, 1, TimeUnit.SECONDS);

            if (!available) {
                System.out.println("락 획득 실패");
                return;
            }

            Coupon coupon = couponRepository.findById(couponId)
                    .orElseThrow(() -> new RuntimeException("쿠폰을 찾을 수 없습니다."));

            coupon.issue();
            couponRepository.save(coupon);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }

    }
}
