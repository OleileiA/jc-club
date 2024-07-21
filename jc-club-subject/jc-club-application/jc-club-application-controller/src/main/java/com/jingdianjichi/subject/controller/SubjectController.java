package com.jingdianjichi.subject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 刷题controller
 */
@RestController
public class SubjectController {

    @GetMapping("/test")
    public String test() {
        return "hello world!";
    }
}
