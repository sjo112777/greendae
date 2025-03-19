package kr.co.greendae.dto;

import kr.co.greendae.entity.article.Basic_Article;
import kr.co.greendae.entity.article.Employ_Article;
import kr.co.greendae.entity.article.State_Article;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ArticleDTO {

    private int no;
    private String cate;    // Employ_Article에는 없음
    private String title;
    private String content;
    private String comment;
    private int file ;
    private int hit;
    private String writer;
    private String regip;
    private String wdate;

    // Employ_Article 내용 추가
    private String ddate;

    // State_Article 내용 추가
    private String state;

    // Entity 변환 메서드 정의
    public Basic_Article toBasicArticleEntity() {
        return Basic_Article.builder()

                .build();
    }

    public Employ_Article toEmployArticleEntity() {

    }

    public State_Article toStateArticleEntity() {

    }

}
