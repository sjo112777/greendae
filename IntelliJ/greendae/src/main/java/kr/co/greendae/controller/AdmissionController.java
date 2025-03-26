package kr.co.greendae.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// 입학안내
@Controller
@RequestMapping("/admission")
public class AdmissionController {

    //수시
    @GetMapping("/early")
    public String early(){
        return "/admission/early";
    }

    //정시
    @GetMapping("/regular")
    public String regular(){
        return "/admission/regular";
    }

    //편입학
    @GetMapping("/transfer")
    public String transfer(){
        return "/admission/transfer";
    }

    //공지
    @GetMapping("/notice")
    public String notice(){
        return "/admission/notice";
    }

    //상담
    @GetMapping("/consult")
    public String consult(){
        return "/admission/consult";
    }

}
