package kr.co.greendae.repository.user;

import kr.co.greendae.entity.user.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor,String> {
}
