package kr.co.greendae.controller;


import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/accession")
    public String accession(){
        return "/user/accession";
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



}
