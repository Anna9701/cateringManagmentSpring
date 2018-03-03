package com.zai.caterings.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    final private String title = "Login";

    @GetMapping("/login/")
    public String index (Model model) {
        model.addAttribute("title", title);
        return "login";
    }
}
