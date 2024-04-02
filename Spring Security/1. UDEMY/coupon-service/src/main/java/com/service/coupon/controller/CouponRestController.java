package com.service.coupon.controller;

import com.service.coupon.dto.CouponDto;
import com.service.coupon.model.Coupon;
import com.service.coupon.repo.CouponRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/coupon")
public class CouponRestController {

    @Autowired
    CouponRepository repository;

    @PostMapping("/add")
    public Coupon createCoupon(@RequestBody Coupon coupon) {

        coupon.setExpiryDate(LocalDate.now());
        return repository.save(coupon);
    }

    @GetMapping("/retrieve/{code}")
    public CouponDto getCoupon(@PathVariable("code") String couponCode) {

        CouponDto couponDto = new CouponDto();
        Coupon coupon = repository.findByCode(couponCode);
        BeanUtils.copyProperties(coupon, couponDto);
        return couponDto;
    }
}
