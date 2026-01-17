package com.vanilalatte.couponsystem.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class CouponCreateProducer {
    private final KafkaTemplate<String, Long> kafkaTemplate;

    public CouponCreateProducer(KafkaTemplate<String, Long> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void create(Long userId) {
        // "coupon_create"라는 토픽(우체통)에 userId를 넣음
        kafkaTemplate.send("coupon_create", userId);
    }
}
