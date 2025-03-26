package kr.co.greendae.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// 입학안내
@Controller
@RequestMapping("/admission")
public class AdmissionController {

    @GetMapping("/early")
    public String early(){
        return "admission/early";
    }
}
