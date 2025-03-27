package kr.co.greendae.repository.user;

import kr.co.greendae.entity.user.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

    //학번, 이름, 학과, 휴대폰번호, 주민번호, 이메일, 학년/학기, 재학상태
    @Query("select (s.stdNo, s.stdYear, s.stdSemester, s.stdClass, s.stdStatus, u.name, u.hp, u.email, u.ssn) from User as u " +
            "join Student as s on u.uid = s.user.uid " +
            "where s.stdNo = :stdNo")
    public List<Object[]> findRecordByStdNo(@Param("stdNo") String stdNo);
}
