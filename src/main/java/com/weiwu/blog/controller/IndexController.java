package com.weiwu.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        int a = 0/0;
        return "index";
    }

    @GetMapping("/blog")
    public String blog() {
        return "blog";
    }
}
