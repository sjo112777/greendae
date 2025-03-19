package kr.co.greendae.repository.article;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Basic_Article extends JpaRepository<Basic_Article, Integer> {
}
