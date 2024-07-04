package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// i just want enable for all origins
@CrossOrigin(origins = "*")
@RestController
public class test {
    @GetMapping("/hello-world")
    public String test()
    {
        return "hello world";
    }
}
