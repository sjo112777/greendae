package kr.co.greendae.repository.user;

import kr.co.greendae.entity.department.Department;
import kr.co.greendae.entity.user.Professor;
import kr.co.greendae.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor,String> {
    List<Professor> findByDepartment(Department department);

    Professor findByUser(User user);
}
