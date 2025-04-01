package kr.co.greendae.repository.support;

import com.querydsl.core.Tuple;
import kr.co.greendae.dto.support.LectureDTO;
import kr.co.greendae.entity.Lecture.Lecture;
import kr.co.greendae.repository.support.impl.LectureRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LectureRepository extends JpaRepository<Lecture, String> ,LectureRepositoryCustom{

    @Query("SELECT l FROM Lecture l WHERE l.lecGrade = :lecGrade")
    public List<Lecture> findByLecGrade(int lecGrade);

    public List<Lecture> findByLecClassAndLecGrade(String lecClass, int lecGrade);

    public List<Lecture> lecCate(String lecCate);

    public List<Lecture> findByLecClassAndLecCate(String lecClass, String cate);

    public Lecture findByLecNo(String lecNo);







    /*
    @Query("SELECT l.lecClass, l.lecCate, l.lecGrade, l.lecNo, l.lecName, l.lecCredit, l.lecStdCount, l.lecStdTotal, u.name " +
            "FROM Lecture l " +
            "join Professor p on l.professor = p.proNo " +
            "join User u on u.uid = p.user.uid " +
            "WHERE l.lecGrade = :stdYear")
    public List<Object[]> findLecturesByYear(@Param("stdYear") int stdYear);
    public List<Lecture> findLectureByLecCate(String lecCate);
    */
}