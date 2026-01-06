package com.vanilalatte.couponsystem.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title; // 쿠폰 이름

    @Column(nullable = false)
    private int totalQuantity; // 총 발급 수량

    @Column(nullable = false)
    private int issuedQuantity; // 현재까지 발급된 수량

    public Coupon(String title, int totalQuantity) {
        this.title = title;
        this.totalQuantity = totalQuantity;
        this.issuedQuantity = 0;
    }

    // 쿠폰 발급 가능 여부 확인 및 수량 증가
    public void issue() {
        if (this.issuedQuantity >= this.totalQuantity) {
            throw new RuntimeException("모든 쿠폰이 소진되었습니다.");
        }
        this.issuedQuantity++;
    }
}
