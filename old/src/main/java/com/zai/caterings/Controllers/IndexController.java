package com.zai.caterings.Controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class IndexController {
    final private String title = "Caterings Management";

    @GetMapping("/")
    public String index (Model model) {
        model.addAttribute("title", title);
        return "index";
    }
}
