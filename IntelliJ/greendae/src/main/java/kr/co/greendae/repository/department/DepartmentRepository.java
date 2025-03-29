package kr.co.greendae.repository.department;

import kr.co.greendae.entity.department.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Integer> {
    Optional<Department> findFirstByOrderByDeptNoDesc();
}
