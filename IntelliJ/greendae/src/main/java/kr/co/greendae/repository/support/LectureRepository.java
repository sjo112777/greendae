package kr.co.greendae.repository.support;

import kr.co.greendae.dto.support.LectureDTO;
import kr.co.greendae.entity.Lecture.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LectureRepository extends JpaRepository<Lecture, String> {
    Optional<Lecture> findByLecGrade(int lecGrade);

    /*
    @Query("SELECT l.lecClass, l.lecCate, l.lecGrade, l.lecNo, l.lecName, l.lecCredit, l.lecStdCount, l.lecStdTotal, u.name " +
            "FROM Lecture l " +
            "join Professor p on l.professor = p.proNo " +
            "join User u on u.uid = p.user.uid " +
            "WHERE l.lecGrade = :stdYear")
    public List<Object[]> findLecturesByYear(@Param("stdYear") int stdYear);
     */

}