package com.service.coupon.controller;

import com.service.coupon.model.Coupon;
import com.service.coupon.repo.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.time.LocalDate;

@Controller
@EnableWebMvc
public class CouponController {

    @Autowired
    private CouponRepository repo;

    @GetMapping("/index")
    public String loadPage() {
        String view = "index";
        System.out.println("VIEW => " + view);
        return view;
    }

    @GetMapping("/showCreateCoupon")
    public String showCreateCoupon() {
        return "createCoupon";
    }

    @PostMapping("/saveCoupon")
    public String save(Coupon coupon) {
        coupon.setExpiryDate(LocalDate.now());
        repo.save(coupon);
        return "createResponse";
    }

    @GetMapping("/showGetCoupon")
    public String showGetCoupon() {
        return "getCoupon";
    }

    @PostMapping("/getCoupon")
    public ModelAndView getCoupon(String code) {
        ModelAndView mav = new ModelAndView("couponDetails");
        System.out.println(code);
        mav.addObject(repo.findByCode(code));
        return mav;
    }

}
