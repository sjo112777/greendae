package kr.co.greendae.repository.community.impl;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.greendae.dto.page.PageRequestDTO;
import kr.co.greendae.entity.community.article.QBasicArticle;
import kr.co.greendae.entity.user.QUser;
import kr.co.greendae.repository.community.custom.BasicArticleRepositoryCustom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Repository
public class BasicArticleRepositoryImpl implements BasicArticleRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private QBasicArticle qArticle = QBasicArticle.basicArticle;
    private QUser qUser = QUser.user;


    @Override
    public Page<Tuple> selectAllForList(Pageable pageable) {

        List<Tuple> tupleList = queryFactory
                .select(qArticle, qUser.name)
                .from(qArticle)
                .join(qUser)
                .on(qArticle.user.uid.eq(qUser.uid))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(qArticle.no.desc())
                .fetch();

        long total = queryFactory.select(qArticle.count()).from(qArticle).fetchOne();


        // 페이징 처리를 위한 페이지 객체 반환
        return new PageImpl<Tuple>(tupleList, pageable, total);
    }

    @Override
    public Page<Tuple> selectAllForSearch(PageRequestDTO pageRequestDTO, Pageable pageable) {

        String searchType = pageRequestDTO.getSearchType();
        String keyword = pageRequestDTO.getKeyword();

        // 검색 조건에 따라 where 조건 표현식 생성
        BooleanExpression expression = null;

        if(searchType.equals("title")){
            expression = qArticle.title.contains(keyword);
        }else if(searchType.equals("content")){
            expression = qArticle.content.contains(keyword);
        }else if(searchType.equals("writer")){
            expression = qUser.name.contains(keyword);
        }

        List<Tuple> tupleList = queryFactory
                .select(qArticle, qUser.name)
                .from(qArticle)
                .join(qUser)
                .on(qArticle.user.uid.eq(qUser.uid))
                .where(expression)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(qArticle.no.desc())
                .fetch();

        long total = queryFactory
                .select(qArticle.count())
                .from(qArticle)
                .join(qUser)
                .on(qArticle.user.uid.eq(qUser.uid))
                .where(expression)
                .fetchOne();


        // 페이징 처리를 위한 페이지 객체 반환
        return new PageImpl<Tuple>(tupleList, pageable, total);
    }
}
