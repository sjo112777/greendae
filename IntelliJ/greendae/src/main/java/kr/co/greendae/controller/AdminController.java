package kr.co.greendae.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// 관리자
@Controller
@RequestMapping("/admin")
public class AdminController {

    // 메인 페이지
    @GetMapping("/main")
    public String main(){
        return "/admin/main";
    }
}
