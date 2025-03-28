package kr.co.greendae.repository.community.file;

import kr.co.greendae.entity.community.article.BasicArticle;
import kr.co.greendae.entity.community.file.Basic_File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasicFileRepository extends JpaRepository<Basic_File, Integer> {
}
