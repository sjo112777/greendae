package kr.co.greendae.controller;

import kr.co.greendae.dto.college.CollegeDTO;
import kr.co.greendae.dto.department.ChairPersonDTO;
import kr.co.greendae.dto.department.DepartmentDTO;
import kr.co.greendae.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

// 관리자
@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    // 메인 페이지
    @GetMapping("/main")
    public String main(){
        return "/admin/main";
    }

    // 대학 및 학과 등록
    @GetMapping("/college/register")
    public String college(Model model){

        List<CollegeDTO> collegeDTOS = adminService.findAllCollege();
        List<ChairPersonDTO> chairPersonDTOS = adminService.findAllChairPerson();

        model.addAttribute("collegeDTOS",collegeDTOS);
        model.addAttribute("chairPersonDTOS",chairPersonDTOS);

        return "/admin/register/college";
    }

    @PostMapping("/college/register")
    public String college(CollegeDTO collageDTO){

        log.info("college register : collageDTO={}", collageDTO);
        // 이미지 등록 서비스 호출
        adminService.uploadImage(collageDTO);
        adminService.save(collageDTO);

        return "redirect:/admin/main";
    }

    @PostMapping("/department/register")
    public String department(DepartmentDTO departmentDTO,
                             @RequestParam("date")
                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){

        departmentDTO.setEstablishedYear(String.valueOf(date.getYear()));

        // 최신 학과번호 추출
        int deptNo = adminService.getMaxDepNo();
        departmentDTO.setDeptNo(deptNo);

        adminService.register(departmentDTO);

        System.out.println(departmentDTO);

        return "redirect:/admin/college/register";
    }
}
