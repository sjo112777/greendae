package kr.co.greendae.repository.support.impl;

import com.querydsl.core.Tuple;
import kr.co.greendae.dto.support.pageRegister.PageRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RegisterRepositoryCustom {

    public Page<Tuple> findRegisterByStdNo(Pageable pageable, String stdNo);

}
