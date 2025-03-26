package kr.co.greendae.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// 학사안내
@Controller
@RequestMapping("/academic")
public class AcademicController {

    // 학사일정
    @GetMapping("/schedule")
    public String schedule(){
        return "/academic/schedule";
    }

    // 수강신청
    @GetMapping("/registration")
    public String registration(){
        return "/academic/registration";
    }

    // 수료 및 졸업
    @GetMapping("/graduation")
    public String graduation(){
        return "/academic/graduation";
    }

    // 성적
    @GetMapping("/grade")
    public String score(){
        return "/academic/grade";
    }

    // 공지사항
    @GetMapping("/notice")
    public String notice(){
        return "/academic/notice";
    }

    // 자주묻는질문
    @GetMapping("/faq")
    public String faq(){
        return "/academic/faq";
    }
}
