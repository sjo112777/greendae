package kr.co.greendae.entity.comment;

import jakarta.persistence.*;
import kr.co.greendae.dto.ArticleDTO;
import kr.co.greendae.dto.CommentDTO;
import kr.co.greendae.entity.article.Basic_Article;
import kr.co.greendae.entity.user.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Parent;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString(exclude = "basic_article")
//@Builder
//@Entity
//@Table(name = "Basic_Comment")
public class Basic_Comment {
/*
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent")
    private Basic_Article basic_article;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer")
    private User user;

    private String regip;

    @CreationTimestamp
    private String wdate;

    public CommentDTO toCommentDTO() {
        return CommentDTO.builder()
                .cno(cno)
                .parent(basic_article.getNo())
                .content(content)
                .writer(user.getUid())
                .regip(regip)
                .wdate(wdate)
                .build();
    }
*/
}
