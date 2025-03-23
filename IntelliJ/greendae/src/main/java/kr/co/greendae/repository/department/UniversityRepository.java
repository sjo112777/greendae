package kr.co.greendae.repository.department;

import kr.co.greendae.entity.university.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversityRepository extends JpaRepository<University,String> {

}
