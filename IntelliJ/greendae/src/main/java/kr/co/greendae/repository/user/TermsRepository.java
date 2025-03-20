package kr.co.greendae.repository.user;

import kr.co.greendae.entity.Terms.Terms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TermsRepository extends JpaRepository<Terms, Integer> {
}
