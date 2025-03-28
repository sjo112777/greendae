package kr.co.greendae.controller.community;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.co.greendae.dto.community.ArticleDTO;
import kr.co.greendae.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// 커뮤니티
@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/community")
public class CommunityController {

    private final HttpServletRequest request;
    private final ArticleService articleService;

    // 공지사항
    @GetMapping("/notice")
    public String notice(){
        HttpSession session = request.getSession();
        session.setAttribute("cate", "notice");
        return "/community/notice";
    }

    //칼럼
    @GetMapping("/news")
    public String news(){
        return "/community/news";
    }

    //취업정보
    @GetMapping("/employment")
    public String employment(){
        return "/community/employment";
    }

    //자유게시판 리스트
    @GetMapping("/freeboard")
    public String freeboard(){
        HttpSession session = request.getSession();
        session.setAttribute("cate", "freeboard");

        return "/community/freeboard";
    }

    //자유게시판 등록
    @GetMapping("/freeboard/write")
    public String write(){
        HttpSession session = request.getSession();
        session.setAttribute("cate", "freeboard");

        return "/community/freeboard_write";
    }

    @PostMapping("/freeboard/write")
    public String write(ArticleDTO articleDTO){

        HttpSession session = request.getSession();
        String cate = (String) session.getAttribute("cate");

        String regip = request.getRemoteAddr();
        articleDTO.setCate(cate);
        articleDTO.setRegip(regip);
        log.info("articleDTO: {}", articleDTO);

        articleService.basicRegister(articleDTO);

        return "redirect:/community/freeboard";
    }

    //질답    
    @GetMapping("/qna")
    public String qna(){
        return "/community/qna";
    }

    //자료실
    @GetMapping("/data")
    public String data(){
        return "/community/data";
    }
}
