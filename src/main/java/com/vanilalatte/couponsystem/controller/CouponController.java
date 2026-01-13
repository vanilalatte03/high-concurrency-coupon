package com.vanilalatte.couponsystem.controller;

import com.vanilalatte.couponsystem.facade.CouponIssueFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/coupons")
public class CouponController {
    private final CouponIssueFacade couponIssueFacade;

    @PostMapping("/{couponId}/issue")
    public String issueCoupon(@PathVariable Long couponId) {
        couponIssueFacade.issue(couponId);

        return "쿠폰 발급 성공!";
    }
}
