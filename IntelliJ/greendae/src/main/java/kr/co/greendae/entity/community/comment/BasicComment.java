package kr.co.greendae.entity.community.comment;

import jakarta.persistence.*;
import kr.co.greendae.entity.community.article.BasicArticle;
import kr.co.greendae.entity.user.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "basicarticle")
@Builder
@Entity
@Table(name = "BasicComment")
public class BasicComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cno;

    private int parent;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer")
    private User user;

    private String regip;

    @CreationTimestamp
    private String wdate;


}
