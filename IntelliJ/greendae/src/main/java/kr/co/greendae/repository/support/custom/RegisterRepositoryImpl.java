package kr.co.greendae.repository.support.custom;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.greendae.entity.Lecture.QLecture;
import kr.co.greendae.entity.Lecture.QRegister;
import kr.co.greendae.entity.user.QProfessor;
import kr.co.greendae.entity.user.QStudent;
import kr.co.greendae.entity.user.QUser;
import kr.co.greendae.repository.support.impl.RegisterRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class RegisterRepositoryImpl implements RegisterRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private QLecture qLecture = QLecture.lecture;
    private QUser qUser = QUser.user;
    private QStudent qStudent = QStudent.student;
    private QRegister qRegister = QRegister.register;
    private QProfessor qProfessor = QProfessor.professor;

    @Override
    public Page<Tuple> findRegisterByStdNo(Pageable pageable, String stdNo) {
        List<Tuple> tupleList = queryFactory
                .select(qRegister, qLecture ,qUser.name)
                .from(qRegister)
                .join(qLecture)
                .on(qLecture.lecNo.eq(qRegister.lecture.lecNo))
                .join(qProfessor)
                .on(qLecture.professor.proNo.eq(qProfessor.proNo))
                .join(qUser)
                .on(qUser.uid.eq(qProfessor.user.uid))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(qRegister.regNo.desc())
                .fetch();

        Long total = Optional.ofNullable(queryFactory
                .select(qRegister.count())
                .from(qRegister)
                .fetchOne())
                .orElse(0L);

        return new PageImpl<>(tupleList, pageable, total);
    }
}
