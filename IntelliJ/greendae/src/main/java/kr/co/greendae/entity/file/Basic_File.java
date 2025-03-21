package kr.co.greendae.entity.file;

import jakarta.persistence.*;
import kr.co.greendae.dto.CommentDTO;
import kr.co.greendae.dto.FileDTO;
import kr.co.greendae.entity.article.Basic_Article;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString(exclude = "basic_article")
//@Builder
//@Entity
//@Table(name = "Basic_File")
public class Basic_File {
    /*

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ano")
    private Basic_Article basic_article;

    private String oName;
    private String sName;
    private int download;

    @CreationTimestamp
    private String rdate;

    public FileDTO toFileDTO() {
        return FileDTO.builder()
                .fno(fno)
                .ano(basic_article.getNo())
                .oName(oName)
                .sName(sName)
                .download(download)
                .rdate(rdate)
                .build();
    }
*/
}
