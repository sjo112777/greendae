package kr.co.greendae.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.co.greendae.dto.user.TermsDTO;
import kr.co.greendae.dto.user.UserDTO;
import kr.co.greendae.service.TermsService;
import kr.co.greendae.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
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

    @PostMapping("/accession")
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

    @PostMapping("/findid")
    public ResponseEntity<?> findId(@RequestBody Map<String, String> map, HttpSession session) {
        String userName = map.get("name");  // 사용자가 입력한 이름
        String userEmail = map.get("email");  // 사용자가 입력한 이메일

        log.info("아이디 찾기 요청 - 이름: {}, 이메일: {}", userName, userEmail);

        // 아이디 찾기 로직 (예: 이메일 인증 코드 전송)
        String authCode = userService.sendFindIdEmailCode(userEmail, userName);

        System.out.println(authCode);
        System.out.println(authCode);
        System.out.println(authCode);
        // 세션에 인증 코드 저장
        session.setAttribute("authCode", authCode);

        return ResponseEntity.ok().body(Collections.singletonMap("message", "인증코드를 이메일로 전송했습니다."));
    }



    @PostMapping("/findid/auth")
    public ResponseEntity<Boolean> findIdAuth(@RequestBody Map<String, String> map, HttpSession session) {
        String authCode = map.get("authCode");  // 사용자가 입력한 인증 코드
        log.info("아이디 찾기 인증 코드: {}", authCode);

        // 세션에서 저장된 인증 코드 가져오기
        String sessionAuthCode = (String) session.getAttribute("findIdAuthCode");
        log.info("세션 인증 코드: {}", sessionAuthCode);

        // 인증 코드 비교
        if (authCode != null && authCode.equals(sessionAuthCode)) {
            return ResponseEntity.ok().body(true);  // 인증 성공
        }

        return ResponseEntity.ok().body(false);  // 인증 실패
    }

    @GetMapping("/findpassword")
    public String findpassword(){
        return "/user/findpassword";
    }

    @PostMapping("/findpassword")
    public ResponseEntity<?> findpassword(@RequestBody Map<String, String> map, HttpSession session) {
        try {
            String email = map.get("email");

            // 이메일이 비어있는지 체크
            if (email == null || email.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이메일을 입력해 주세요.");
            }

            // 인증 코드 전송 로직
            String authCode = userService.sendPasswordResetEmailCode(email);

            // 인증 코드를 세션에 저장
            session.setAttribute("authCode", authCode);

            return ResponseEntity.ok().body(Collections.singletonMap("message", "인증코드를 이메일로 전송했습니다."));
        } catch (Exception e) {
            log.error("비밀번호 찾기 중 오류 발생", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류가 발생했습니다.");
        }
    }


    @PostMapping("/findpassword/auth")
    public ResponseEntity<Boolean> findPasswordAuth(@RequestBody Map<String, String> map, HttpSession session) {
        String authCode = map.get("authCode");  // 사용자가 입력한 인증 코드
        log.info("비밀번호 찾기 인증 코드: {}", authCode);

        // 세션에서 저장된 인증 코드 가져오기
        String sessionAuthCode = (String) session.getAttribute("findPasswordAuthCode");
        log.info("세션 인증 코드: {}", sessionAuthCode);

        // 인증 코드 비교
        if (authCode != null && authCode.equals(sessionAuthCode)) {
            return ResponseEntity.ok().body(true);  // 인증 성공
        }

        return ResponseEntity.ok().body(false);  // 인증 실패
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
    public String lookupresult(UserDTO userDTO, Model model){

        UserDTO findUser = userService.findUserByEmail(userDTO.getEmail());

        model.addAttribute("userDTO", findUser);

        return "/user/lookupresult";
    }

    @GetMapping("/termsandconditions")
    public String termsandconditions(Model model){

        TermsDTO termsDTO =  termsService.findTerms();
        model.addAttribute(termsDTO);

        return "/user/termsandconditions";
    }







}
