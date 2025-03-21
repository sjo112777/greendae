package kr.co.greendae.entity.article;

import jakarta.persistence.*;
import kr.co.greendae.dto.ArticleDTO;
import kr.co.greendae.entity.comment.Basic_Comment;
import kr.co.greendae.entity.file.Basic_File;
import kr.co.greendae.entity.user.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.List;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
//@Builder
//@Entity
//@Table(name = "Basic_Article")
public class Basic_Article {
/*
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no;
    private String cate;
    private String title;
    private String content;

    @OneToMany(mappedBy = "basic_article")
    private List<Basic_Comment> basic_comment;

    @OneToMany(mappedBy = "basic_article")
    private List<Basic_File> basic_file ;

    private int hit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer")
    private User user;

    private String regip;

    @CreationTimestamp
    private String wdate;

    public ArticleDTO toArticleDTO() {
        return ArticleDTO.builder()
                .no(no)
                .cate(cate)
                .title(title)
                .content(content)
                .comment(basic_comment)
                .file(basic_file)
                .hit(hit)
                .writer(user.getUid())
                .regip(regip)
                .wdate(wdate)
                .build();
    }
*/
}

