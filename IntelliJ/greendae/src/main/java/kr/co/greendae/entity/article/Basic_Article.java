package kr.co.greendae.entity.article;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kr.co.greendae.dto.ArticleDTO;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "Basic_Article")
public class Basic_Article {

    @Id
    private int no;
    private String cate;
    private String title;
    private String content;
    private String comment;
    private int file ;
    private int hit;
    private String writer;
    private String regip;
    private String wdate;

    public ArticleDTO toArticleDTO() {
        return ArticleDTO.builder()
                .no(no)
                .cate(cate)
                .title(title)
                .content(content)
                .comment(comment)
                .file(file)
                .hit(hit)
                .writer(writer)
                .regip(regip)
                .wdate(wdate)
                .build();
    }
}
