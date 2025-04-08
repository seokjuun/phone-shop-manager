package me.hongseokjun.myapp.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/pages/register")
    public String register() { return "register";};
}
