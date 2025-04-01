package kr.co.greendae.controller.community;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.greendae.dto.community.ArticleDTO;
import kr.co.greendae.dto.community.CommentDTO;
import kr.co.greendae.entity.community.article.BasicArticle;
import kr.co.greendae.service.ArticleService;
import kr.co.greendae.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class CommentController {

    private final CommentService commentService;
    private final ArticleService articleService;

    @ResponseBody
    @GetMapping("/comment/list")
    public List<CommentDTO> list(int parent){
        log.info("parent:{}", parent);

        List<CommentDTO> commentDTOList = commentService.findByParent(parent);

        return commentDTOList;
    }

    @ResponseBody
    @PostMapping("/comment/write")
    public CommentDTO write(@RequestBody CommentDTO commentDTO, HttpServletRequest request) {
        log.info("commentDTO:{}", commentDTO);

        String regip = request.getRemoteAddr();
        commentDTO.setRegip(regip);

        CommentDTO savedCommentDTO = commentService.save(commentDTO);
        int no = savedCommentDTO.getParent();

        articleService.CountUpComment(no);


        // basisArticle - no pk findByID
        // .setComment(basicArticle.getCom + 1)


        return savedCommentDTO;
    }

    @ResponseBody
    @PostMapping("/comment/delete")
    public String delete(@RequestParam("cno") int cno){
        log.info("cno:{}", cno);

        commentService.deletebasicComment(cno);

        return "success";
    }


}
