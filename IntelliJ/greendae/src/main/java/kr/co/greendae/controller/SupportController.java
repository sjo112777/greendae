package kr.co.greendae.controller;

import kr.co.greendae.dto.support.LectureDTO;
import kr.co.greendae.dto.support.RegisterDTO;
import kr.co.greendae.dto.support.StudentDTO;
import kr.co.greendae.service.SupportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

// 학생 지원
@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/support")
public class SupportController {

    private final SupportService supportService;

    //교육과정
    @GetMapping("/classes")
    public String classes(){
        return "/support/classes";
    }

    //성적
    @GetMapping("/grade")
    public String grade(){
        return "/support/grade";
    }
    @GetMapping("/grade/{stdNo}")
    public String gradeByStdNo(@PathVariable String stdNo, Model model){
        List<RegisterDTO> gradeList = supportService.findGradeByStdNo(stdNo);

        model.addAttribute("gradeList", gradeList);

        return "/support/grade";
    }

    //수강신청
    @GetMapping("/register")
    public String register(Model model){

        List<LectureDTO> lectureDTOList = supportService.findAll();
        model.addAttribute("lectureDTOList", lectureDTOList);

        return "/support/register";
    }

    //내역
    @GetMapping("/register_list")
    public String register_list(){
        return "/support/register_list";
    }

    @GetMapping("/register_list/{stdNo}")
    public String registerListByStdNo(@PathVariable String stdNo, Model model){
        log.info("stdNo: " + stdNo);

        List<RegisterDTO> registerList = supportService.findRegisterByStdNo(stdNo);

        log.info("registerList : {}", registerList);

        model.addAttribute("registerList", registerList);

        return "/support/register_list";
    }

    //학적
    @GetMapping("/record")
    public String record(){
        return "/support/record";
    }
    @GetMapping("/record/{stdNo}")
    public String recordByStdNo(@PathVariable String stdNo, Model model){
        log.info("stdNo: " + stdNo);

        List<StudentDTO> studentList = supportService.findRecordByStdNo(stdNo);

        if(!studentList.isEmpty()){
            model.addAttribute("student", studentList.get(0));
        }

        return "/support/record";
    }

}