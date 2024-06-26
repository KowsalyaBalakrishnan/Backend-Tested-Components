package com.service.coupon.repo;

import com.service.coupon.dto.CouponDto;
import com.service.coupon.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {
    Coupon findByCode(String couponCode);
}
