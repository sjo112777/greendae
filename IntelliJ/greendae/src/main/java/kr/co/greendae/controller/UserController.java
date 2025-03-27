package kr.co.greendae.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.co.greendae.dto.user.TermsDTO;
import kr.co.greendae.dto.user.UserDTO;
import kr.co.greendae.service.TermsService;
import kr.co.greendae.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.tool.schema.internal.SchemaTruncatorImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

    private final TermsService termsService;
    private final UserService userService;

    @GetMapping("/accession")
    public String accession(){
        return "/user/accession";
    }

    @GetMapping("/{type}/{value}")
    public ResponseEntity user(@PathVariable("type") String type, @PathVariable("value") String value){
        log.info("type : " + type + ", value : " + value);

        // 서비스 호출
        long count = userService.checkUser(type, value);

        // JSON 생성
        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put("count", count);

        // JSON 반환
        return ResponseEntity.ok().body(resultMap);
    }

    @PostMapping("/email/auth")
    public ResponseEntity<Boolean> emailAuth(@RequestBody Map<String,String> map, HttpSession session){
        String authCode = map.get("authCode");
        log.info("authCode : {}", authCode);

        String sessAuthCode = (String) session.getAttribute("authCode");
        log.info("sessAuthCode : {}", sessAuthCode);

        if(authCode.equals(sessAuthCode)){
            return ResponseEntity.ok().body(true);
        }
        return ResponseEntity.ok().body(false);
    }

    @PostMapping("/user/accession")
    public String accession(HttpServletRequest req, UserDTO userDTO){

        String regip = req.getRemoteAddr();
        userDTO.setRegip(regip);

        // 서비스 호출
        userService.register(userDTO);

        // 리다이렉트
        return "redirect:/user/login";
    }


    @GetMapping("/login")
    public String login(){
        return "/user/login";
    }

    @GetMapping("/findid")
    public String findid(){
        return "/user/findid";
    }

    @GetMapping("/findpassword")
    public String findpassword(){
        return "/user/findpassword";
    }

    @GetMapping("/information")
    public String information(){
        return "/user/information";
    }

    @GetMapping("/Changepassword")
    public String Changepassword(){
        return "/user/Changepassword";
    }

    @GetMapping("/lookupresult")
    public String lookupresult(){
        return "/user/lookupresult";
    }

    @GetMapping("/termsandconditions")
    public String termsandconditions(Model model){

        TermsDTO termsDTO =  termsService.findTerms();
        model.addAttribute(termsDTO);

        return "/user/termsandconditions";
    }







}
