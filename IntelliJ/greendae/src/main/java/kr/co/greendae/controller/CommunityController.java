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

    @GetMapping("/news")
    public String news(){
        return "/community/news";
    }

    @GetMapping("/employment")
    public String employment(){
        return "/community/employment";
    }

    @GetMapping("/freeboard")
    public String freeboard(){
        return "/community/freeboard";
    }

    @GetMapping("/qna")
    public String qna(){
        return "/community/qna";
    }

    @GetMapping("/data")
    public String data(){
        return "/community/data";
    }
}
