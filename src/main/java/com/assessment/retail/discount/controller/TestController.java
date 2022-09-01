package com.assessment.retail.discount.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/v1/discount")
public class TestController {

    @GetMapping
    public String test() {
        System.out.println("Welcome to Discount");
        return "Welcome to Discount";
    }
}
