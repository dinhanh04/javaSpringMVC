package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/")
    public String index() {
        return "hello spring boot with dinh anh upadate part 2";
    }

    @GetMapping("/user")
    public String user() {
        return "hello user dinh anh update p2";
    }

}

