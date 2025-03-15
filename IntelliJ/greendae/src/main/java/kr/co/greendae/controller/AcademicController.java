package kr.co.greendae.controller;

import kr.co.greendae.dto.EventDTO;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// 학사안내
@Controller
@RequestMapping("/academic")
public class AcademicController {

    // 학사일정
    @GetMapping("/schedule")
    public String schedule(){return "/academic/schedule";}

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
}
