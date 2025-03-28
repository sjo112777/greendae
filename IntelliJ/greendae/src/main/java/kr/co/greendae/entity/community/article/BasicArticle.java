package kr.co.greendae.entity.community.article;

import jakarta.persistence.*;
import kr.co.greendae.entity.community.comment.BasicComment;
import kr.co.greendae.entity.community.file.Basic_File;
import kr.co.greendae.entity.user.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "BasicArticle")
public class BasicArticle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no;

    @Column(nullable = false)
    private String cate;

    private String title;
    private String content;
    private int comment;
    private int file;
    private int hit;

    @OneToMany(mappedBy = "parent")
    private List<BasicComment> basic_comment;

    @OneToMany(mappedBy = "ano") //mappedBy 속성은 매핑되는 엔티티의 FK 컬럼
    private List<Basic_File> basic_files;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer")
    private User user;

    private String regip;

    @CreationTimestamp
    private String wdate;

    @PrePersist
    public void prePersist() {
        // 엔티티 기본 속성 값 초기화
        if (this.cate == null) {
            this.cate = "free";
        }
    }
}

