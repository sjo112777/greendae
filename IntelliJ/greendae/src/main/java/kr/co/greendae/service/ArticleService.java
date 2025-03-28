package kr.co.greendae.service;



import kr.co.greendae.dto.community.ArticleDTO;
import kr.co.greendae.entity.community.article.BasicArticle;
import kr.co.greendae.entity.user.User;
import kr.co.greendae.repository.community.article.BasicArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;



@Slf4j
@RequiredArgsConstructor
@Service
public class ArticleService {

    private final BasicArticleRepository basicArticleRepository;
    private final ModelMapper modelMapper;
    public int basicRegister(ArticleDTO articleDTO) {

        // 엔티티 변환
        User user = User.builder()
                .uid(articleDTO.getWriter())
                .build();

        BasicArticle basicArticle = modelMapper.map(articleDTO, BasicArticle.class);
        basicArticle.setUser(user);

        log.info("basicArticle : {}", basicArticle);

        // JPA 저장
        BasicArticle savedArticle = basicArticleRepository.save(basicArticle);

        basicArticleRepository.save(basicArticle);

        // 저장한 글번호 반환
        return savedArticle.getNo();
    }

}
