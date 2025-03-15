package kr.co.greendae.controller;

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

}
