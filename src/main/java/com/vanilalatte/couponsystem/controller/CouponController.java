package com.vanilalatte.couponsystem.controller;

import com.vanilalatte.couponsystem.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/coupons")
public class CouponController {
    private final CouponService couponService;

    @PostMapping("/{couponId}/issue")
    public String issueCoupon(@PathVariable Long couponId) {
        couponService.issue(couponId);

        return "응모가 완료되었습니다";
    }
}
