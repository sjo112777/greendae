package kr.co.greendae.controller;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.greendae.dto.college.CollegeDTO;
import kr.co.greendae.dto.department.ChairPersonDTO;
import kr.co.greendae.dto.department.DepartmentDTO;
import kr.co.greendae.dto.support.StudentDTO;
import kr.co.greendae.dto.user.ProfessorDTO;
import kr.co.greendae.dto.user.UserDTO;
import kr.co.greendae.entity.user.Student;
import kr.co.greendae.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

        return "redirect:/admin/college/register";
    }

    // 교수 등록 페이지
    @GetMapping("/professor/register")
    public String professor(Model model){
        
        
        // Select 박스를 위한 대학 정보
        List<CollegeDTO>  collegeDTOS = adminService.findAllCollege();
        List<DepartmentDTO> departmentDTOS = adminService.findAllDepartmentByName(collegeDTOS.get(0).getName());

        //대학 정보
        model.addAttribute("collegeDTOS",collegeDTOS);
        model.addAttribute("departmentDTOS",departmentDTOS);
        return "/admin/register/professor";
    }

    // 교수 등록
    @PostMapping("/professor/register")
    public String  professor(ProfessorDTO professorDTO, UserDTO userDTO
                            , @RequestParam("graduationDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate graduationDate,
                             @RequestParam("appointmentDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate appointmentDate
                            , HttpServletRequest req){

        // 교수번호 : 연도 + 학과번호 + 순번 자동 조합
        String prodNo = String.valueOf(appointmentDate.getYear());
        DepartmentDTO departmentDTO = adminService.findDepartmentByName(professorDTO.getDepartmentD());
        prodNo += departmentDTO.getDeptNo();

        // 비밀번호 추출 (주민번호 뒷자리)
        String[] passStr = userDTO.getSsn().split("-");
        String pass = passStr[1];
        userDTO.setPass(pass);
        userDTO.setRole("Professor");
        userDTO.setRegip(req.getRemoteAddr());

        // User 테이블에 저장 - code는 교수번호
        String code = adminService.registerUser(userDTO, prodNo);

        professorDTO.setUid(code);
        professorDTO.setProNo(code);
        professorDTO.setDeptNo(departmentDTO.getDeptNo());

        adminService.registerProfessor(professorDTO, userDTO, departmentDTO);




        System.out.println(departmentDTO);
        return "redirect:/admin/professor/register";
    }

    // 학생 등록 페이지
    @GetMapping("/student/register")
    public String student(Model model){


        // Select 박스를 위한 대학 정보
        List<CollegeDTO>  collegeDTOS = adminService.findAllCollege();
        List<DepartmentDTO> departmentDTOS = adminService.findAllDepartmentByName(collegeDTOS.get(0).getName());

        //대학 정보
        model.addAttribute("collegeDTOS",collegeDTOS);
        model.addAttribute("departmentDTOS",departmentDTOS);
        return "/admin/register/student";
    }

    // 학생 등록
    @PostMapping("/student/register")
    public String  student(StudentDTO studentDTO, UserDTO userDTO
            , HttpServletRequest req){

        // 학생번호 : 연도 + 학과번호 + 순번 자동 조합
        String studNo = studentDTO.getAdmission_year();  // 연도 추가
        DepartmentDTO departmentDTO = adminService.findDepartmentByName(studentDTO.getStdClass());
        studNo += departmentDTO.getDeptNo();

        // 비밀번호 추출 (주민번호 뒷자리)
        String[] passStr = userDTO.getSsn().split("-");
        String pass = passStr[1];
        userDTO.setPass(pass);
        userDTO.setRole("Student");
        userDTO.setRegip(req.getRemoteAddr());

        // User 테이블에 저장 - code는 학생번호
        String code = adminService.registerUser(userDTO, studNo);

        studentDTO.setUid(code);
        studentDTO.setStdNo(code);
        adminService.registerStudent(studentDTO, userDTO, departmentDTO);

        return "redirect:/admin/professor/register";
    }

    // 대학을 선택하면 관련 학과를 출력
    @ResponseBody
    @GetMapping("/department/list")
    public List<DepartmentDTO> departmentList(@RequestParam("college") String college){
        List<DepartmentDTO> departmentDTOS = adminService.findAllDepartmentByName(college);
        return departmentDTOS;
    }

    // 학과를 선택하면 관련 교수를 출력
    @ResponseBody
    @GetMapping("/professor/list")
    public List<ProfessorDTO> professorList(@RequestParam("department") String department){

        List<ProfessorDTO> professorDTOS = adminService.findAllProfessorByName(department);

        return professorDTOS;
    }
}
