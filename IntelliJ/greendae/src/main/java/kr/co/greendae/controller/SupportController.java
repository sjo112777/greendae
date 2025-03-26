package kr.co.greendae.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// 학생 지원
@RequiredArgsConstructor
@Controller
@RequestMapping("/support")
public class SupportController {

    //교육과정
    @GetMapping("/classes")
    public String classes(){
        return "/support/classes";
    }

    //성적
    @GetMapping("/grade")
    public String grade(){
        return "/support/grade";
    }

    //수강신청
    @GetMapping("/register")
    public String register(){
        return "/support/register";
    }

    //내역
    @GetMapping("/register_list")
    public String register_list(){
        return "/support/register_list";
    }

    //학적
    @GetMapping("/record")
    public String record(){
        return "/support/record";
    }
}