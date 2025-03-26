package kr.co.greendae.repository.support;

import kr.co.greendae.entity.Lecture.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;



public interface RegisterRepository extends JpaRepository<Register, String> {
    //Register_list 페이지에 출력할 정보 JOIN
    @Query("select (r.student.stdNo, r.lecture.lecNo, l.lecCredit, l.lecName, l.lecCate, l.lecGrade, l.professor, l.lecRoom, l.lecTime) from Register as r " +
            "join Lecture as l on r.lecture.lecNo = l.lecNo " +
            "where r.student.stdNo = :stdNo")
    public List<Object[]> findRegisterByStdNo(@Param("stdNo") String stdNo);

    //Grade 페이지에 출력할 정보 JOIN
    @Query("select (r.student.stdNo, r.lecture.lecNo, r.regTotalScore, r.regGradeScore, l.lecCredit, l.lecName, l.lecCate, l.lecGrade, l.professor) from Register as r " +
            "join Lecture as l on r.lecture.lecNo = l.lecNo " +
            "where r.student.stdNo = :stdNo")
    public List<Object[]> findGradeByStdNo(@Param("stdNo") String stdNo);
}
