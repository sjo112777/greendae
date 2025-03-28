package kr.co.greendae.controller.community;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.co.greendae.dto.community.ArticleDTO;
import kr.co.greendae.dto.community.FileDTO;
import kr.co.greendae.service.ArticleService;
import kr.co.greendae.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

// 커뮤니티
@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/community")
public class CommunityController {

    private final HttpServletRequest request;
    private final ArticleService articleService;
    private final FileService fileService;

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

    // 자유게시판 리스트 list
    @GetMapping("/freeboard")
    public String freeboard(Model model) {
        HttpSession session = request.getSession();
        session.setAttribute("cate", "freeboard");

        List<ArticleDTO> articleDTOList = articleService.findAllByCate("freeboard");

        model.addAttribute("articleDTOList", articleDTOList);
        System.out.println(articleDTOList);
        model.addAttribute("isListing", true);  // isWriting=false로 설정하여 리스트 화면 표시
        return "/community/freeboard";  // freeboard.html을 렌더링
    }

    // 자유게시판 글쓰기 페이지
    @GetMapping("/freeboard/write")
    public String write(Model model) {
        HttpSession session = request.getSession();
        session.setAttribute("cate", "freeboard");

        model.addAttribute("isWriting", true);  // isWriting=true로 설정하여 글쓰기 화면 표시
        return "/community/freeboard";  // freeboard.html을 렌더링하며 write.html 포함
    }

    // 자유게시판 글 등록
    @PostMapping("/freeboard/write")
    public String write(ArticleDTO articleDTO) {
        HttpSession session = request.getSession();
        String cate = (String) session.getAttribute("cate");

        String regip = request.getRemoteAddr();
        articleDTO.setCate(cate);
        articleDTO.setRegip(regip);
        log.info("articleDTO: {}", articleDTO);

        //파일 업로드 서비스 호출
        List<FileDTO> files = fileService.uploadFile(articleDTO);

        // 글 저장 서비스 호출
        articleDTO.setFile(files.size());
        int no = articleService.basicRegister(articleDTO);

        log.info("no: {}", no);
        log.info("files: {}", files);

        // 파일 저장 서비스 호출
        for(FileDTO fileDTO : files) {
            fileDTO.setAno(no);
            fileService.save(fileDTO);
        }


        return "redirect:/community/freeboard";  // 글쓰기 후 리스트 페이지로 리디렉션
    }

    // 자유게시판 글 보기 view
    @GetMapping("/freeboard/view")
    public String view(Model model, int no){

        System.out.println(no);
        // 글 조회 서비스 호출
         ArticleDTO articleDTO = articleService.findById(no);

         model.addAttribute(articleDTO);
         model.addAttribute("isViewing", true);

        // return "redirect:/community/freeboard";
        return "/community/freeboard";
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
