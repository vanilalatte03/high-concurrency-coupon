package com.vanilalatte.couponsystem.facade;

import com.vanilalatte.couponsystem.service.CouponIssueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
@Slf4j
public class CouponIssueFacade {
    private final RedissonClient redissonClient;
    private final CouponIssueService couponService;

    public void issue(Long couponId) {
        String lockName = "coupon_lock:" + couponId;
        RLock lock = redissonClient.getLock(lockName);

        try {
            boolean available = lock.tryLock(10, 1, TimeUnit.SECONDS);
            if (!available) {
                log.info("이벤트 참여자가 많아 잠시 후 다시 시도해주세요.");
                return;
            }

            couponService.issue(couponId);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        } finally {
            if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }
}
