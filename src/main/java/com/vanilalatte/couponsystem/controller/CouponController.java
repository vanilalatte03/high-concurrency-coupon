package com.vanilalatte.couponsystem.controller;

import lombok.RequiredArgsConstructor;
import com.vanilalatte.couponsystem.service.CouponService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/coupons")
public class CouponController {
    private final CouponService couponService;

    @PostMapping("/{id}/issue")
    public String issueCoupon(@PathVariable Long id) {
        couponService.issue(id);
        return "쿠폰 발급 성공!";
    }
}
