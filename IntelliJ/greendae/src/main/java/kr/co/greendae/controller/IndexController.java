package kr.co.greendae.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @Value("${spring.application.name}")
    private String appName;

    @Value("${spring.application.version}")
    private String version;


    @GetMapping(value = {"/", "/index"})
    public String index(Model model) {
        // model.addAttribute("appName", appName);
        // model.addAttribute("version", version);
        return "/index";
    }


}
