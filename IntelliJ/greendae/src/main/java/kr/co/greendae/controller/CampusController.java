package kr.co.greendae.controller;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// 대학생활
@Controller
@RequestMapping("/campus")
public class CampusController {

    // 동아리, 스터디
    @GetMapping("/club_study")
    public String clubStudy(){
        return "/campus/club_study";
    }
    //학생회 소개
    @GetMapping("/introduce")
    public String introduce(){
        return "/campus/introduce";
    }

    //식단안내
    @GetMapping("/menu")
    public String menu(){
        return "/campus/menu";
    }

    //갤러리
    @GetMapping("/gallery")
    public String gallery(){
        return "/campus/gallery";
    }
}
