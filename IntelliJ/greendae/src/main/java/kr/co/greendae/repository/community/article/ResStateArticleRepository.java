package kr.co.greendae.repository.community.article;

import kr.co.greendae.entity.community.article.ResStateArticle;
import kr.co.greendae.entity.community.article.StateArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResStateArticleRepository  extends JpaRepository<ResStateArticle,String> {
}
