package kr.co.greendae.repository.department;

import kr.co.greendae.entity.college.College;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollegeRepository extends JpaRepository<College,String> {

}
