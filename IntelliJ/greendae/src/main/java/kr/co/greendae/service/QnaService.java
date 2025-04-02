package kr.co.greendae.service;

import com.querydsl.core.Tuple;
import kr.co.greendae.dto.community.ArticleDTO;
import kr.co.greendae.dto.page.PageRequestDTO;
import kr.co.greendae.dto.page.PageResponseDTO;
import kr.co.greendae.entity.community.article.BasicArticle;
import kr.co.greendae.entity.community.article.StateArticle;
import kr.co.greendae.entity.user.User;
import kr.co.greendae.repository.community.article.BasicArticleRepository;
import kr.co.greendae.repository.community.article.StateArticleRepository;
import kr.co.greendae.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class QnaService {

    private final StateArticleRepository  stateArticleRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public void register(ArticleDTO articleDTO) {

        User user = userRepository.findById(articleDTO.getWriter()).get();

        StateArticle stateArticle = modelMapper.map(articleDTO, StateArticle.class);
        stateArticle.setUser(user);
        stateArticleRepository.save(stateArticle);

    }

    public PageResponseDTO findAll(PageRequestDTO pageRequestDTO, String cate) {

        return null;
    }
}
