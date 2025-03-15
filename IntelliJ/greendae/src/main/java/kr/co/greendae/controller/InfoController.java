package kr.co.greendae.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// 대학·대학원
@Controller
@RequestMapping("/info")
public class InfoController {

    // 인문사회대학
    @GetMapping("/social")
    public String social(){
        return "/info/social";
    }

    // 자연과학대학
    @GetMapping("/natural")
    public String natural(){
        return "/info/natural";
    }

    // 공과대학
    @GetMapping("/engineering")
    public String engineering(){
        return "/info/engineering";
    }

    // 사범대학
    @GetMapping("/education")
    public String education(){
        return "/info/education";
    }

    // 대학원
    @GetMapping("/graduate")
    public String graduate(){
        return "/info/graduate";
    }
}
