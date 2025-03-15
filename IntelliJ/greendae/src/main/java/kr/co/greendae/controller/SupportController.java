package kr.co.greendae.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// 학생 지원
@Controller
@RequestMapping("/support")
public class SupportController {

    @GetMapping("/classes")
    public String classes(){
        return "/support/classes";
    }
}