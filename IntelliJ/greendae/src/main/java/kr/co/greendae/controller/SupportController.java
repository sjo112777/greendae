package kr.co.greendae.controller;

import kr.co.greendae.dto.support.LectureDTO;
import kr.co.greendae.dto.support.RegisterDTO;

import kr.co.greendae.dto.support.StudentDTO;
import kr.co.greendae.service.SupportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// 학생 지원
@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/support")
public class SupportController {

    private final SupportService supportService;

    //교과과정
    @GetMapping("/classes")
    public String classes(@AuthenticationPrincipal UserDetails userDetails, Model model) {

        // 학번
        String stdNo = userDetails.getUsername();
        StudentDTO studentDTO = supportService.findStudentByStdNo(stdNo);

        // 학년, 학과

        // 전공 데이터 출력
        // 전공 1학년 데이터 출력
        List<LectureDTO> majorListLevel1 = supportService.findLectureByLecCate(studentDTO, "1");

        // 전공 2학년 데이터 출력
        List<LectureDTO> majorListLevel2 = supportService.findLectureByLecCate(studentDTO, "2");

        // 전공 3학년 데이터 출력
        List<LectureDTO> majorListLevel3 = supportService.findLectureByLecCate(studentDTO, "3");

        // 전공 4학년 데이터 출력
        List<LectureDTO> majorListLevel4 = supportService.findLectureByLecCate(studentDTO, "4");

        // 교양 데이터 출력
        List<LectureDTO> generalList = supportService.findLectureByLecClass(studentDTO, "교양");

        model.addAttribute("majorListLevel1", majorListLevel1);
        model.addAttribute("majorListLevel2", majorListLevel2);
        model.addAttribute("majorListLevel3", majorListLevel3);
        model.addAttribute("majorListLevel4", majorListLevel4);
        model.addAttribute("generalList", generalList);

        // 데이터 필터링 예시 (lecGrade별로 그룹화)
        Map<Integer, List<LectureDTO>> groupedGeneralList = generalList.stream()
                .collect(Collectors.groupingBy(LectureDTO::getLecGrade));

        model.addAttribute("groupedGeneralList", groupedGeneralList);

        return "/support/classes";
    }


    //성적
    @GetMapping("/grade")
    public String gradeByStdNo(@AuthenticationPrincipal UserDetails userDetails,  Model model){
        String stdNo = userDetails.getUsername();
        List<RegisterDTO> gradeList = supportService.findGradeByStdNo(stdNo);

        model.addAttribute("gradeList", gradeList);

        return "/support/grade";
    }

    //수강신청
    @GetMapping("/register")
    public String register(@AuthenticationPrincipal UserDetails userDetails, Model model){
        String stdNo = userDetails.getUsername();
        log.info("stdNo: " + stdNo);

        // 학생의 학년 조회
        int stdYear = supportService.findStudentYearByStdNo(stdNo);
        log.info("stdYear: " + stdYear);

        List<LectureDTO> lectureDTOList = supportService.findRegisterByStdNoByGrade(stdYear);
        model.addAttribute("lectureDTOList", lectureDTOList);

        return "/support/register";
    }

    //내역
    @GetMapping("/register_list")
    public String registerListByStdNo(@AuthenticationPrincipal UserDetails userDetails, Model model){
        String stdNo = userDetails.getUsername();
        log.info("stdNo: " + stdNo);

        List<RegisterDTO> registerList = supportService.findRegisterByStdNo(stdNo);

        model.addAttribute("registerList", registerList);

        return "/support/register_list";
    }


    //학적
    @GetMapping("/record")
    public String recordByStdNo(@AuthenticationPrincipal UserDetails userDetails, Model model){
        String stdNo = userDetails.getUsername();
        log.info("stdNo: " + stdNo);

        List<StudentDTO> studentList = supportService.findRecordByStdNo(stdNo);

        if (!studentList.isEmpty()) {
            StudentDTO studentDTO = studentList.get(0);  // 첫 번째 학생의 정보 가져오기
            String studentStdNo = studentDTO.getStdNo();  // 학번 가져오기
            log.info("Student StdNo: " + studentStdNo);  // 학번 출력 (확인용)

            // 학점 계산
            SupportService.CreditSummary creditSummary = supportService.calculateCredits(stdNo);

            double averageCredits = 0;
            int totalCredits = creditSummary.getMajor() + creditSummary.getElective() + creditSummary.getOther();
            if(totalCredits > 0){
                averageCredits = (double) totalCredits / 3;
            }

            // 모델에 학생 정보와 학점 요약 추가
            model.addAttribute("student", studentDTO);
            model.addAttribute("creditSummary", creditSummary);
            model.addAttribute("averageCredits", averageCredits);
        } else {
            log.warn("No student found for stdNo: " + stdNo);  // 학생 정보가 없을 경우 경고 로그
        }

        // 결과적으로 학적 정보를 보여주는 페이지로 포워드
        return "/support/record";
    }

}