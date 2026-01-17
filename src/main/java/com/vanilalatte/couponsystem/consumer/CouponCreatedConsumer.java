package com.vanilalatte.couponsystem.consumer;

import com.vanilalatte.couponsystem.service.CouponIssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CouponCreatedConsumer {
    private final CouponIssueService couponIssueService;

    // "coupon_create" 토픽을 감시하다가 메시지가 오면 실행됨
    @KafkaListener(topics = "coupon_create", groupId = "group_final_fix")
    public void listener(Long userId) {
        System.out.println("Consumer 메시지 수신: " + userId);
        couponIssueService.issue(userId);
    }
}
