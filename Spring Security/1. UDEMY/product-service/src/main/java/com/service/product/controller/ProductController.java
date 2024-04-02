package com.service.product.controller;

import com.service.product.model.Coupon;
import com.service.product.model.Product;
import com.service.product.repo.ProductRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${coupon.url}")
    private String coupleBaseUrl;

    @PostMapping("/add")
    public Product createProduct(@RequestBody Product product) {

        String couponCode = product.getCouponCode();

        ResponseEntity<Coupon> exchange = restTemplate.exchange(coupleBaseUrl + couponCode,
                HttpMethod.GET, new HttpEntity<>(httpHeaders()), Coupon.class);

        if (exchange.getStatusCode() == HttpStatus.OK && exchange.getBody() != null) {
            product.setPrice(product.getPrice().subtract(exchange.getBody().getDiscount()));
        }
        return productRepository.save(product);
    }

    @GetMapping("/retrieve/{id}")
    public Product getProduct(@PathVariable("id") Integer productId) {
        Optional<Product> byId = productRepository.findById(productId);
        return byId.orElseGet(Product::new);
    }

    private HttpHeaders httpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic " + getBasicAuthHeader());
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        return httpHeaders;
    }

    private String getBasicAuthHeader() {
        String credentials = "doug@bailey.com:doug";
        return new String(Base64.encodeBase64(credentials.getBytes()));
    }

}
