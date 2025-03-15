package kr.co.greendae.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// 커뮤니티
@Controller
@RequestMapping("/community")
public class CommunityController {

    // 공지사항
    @GetMapping("/notice")
    public String notice(){
        return "/community/notice";
    }
}
