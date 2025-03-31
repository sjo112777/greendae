package kr.co.greendae.repository.support;

import kr.co.greendae.dto.support.LectureDTO;
import kr.co.greendae.entity.Lecture.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LectureRepository extends JpaRepository<Lecture, String> {

    @Query("SELECT l FROM Lecture l WHERE l.lecGrade = :lecGrade")
    List<Lecture> findByLecGrade(int lecGrade);

    List<Lecture> findByLecClassAndLecGrade(String lecClass, int lecGrade);

    List<Lecture> lecCate(String lecCate);

    List<Lecture> findByLecClassAndLecCate(String lecClass, String cate);







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