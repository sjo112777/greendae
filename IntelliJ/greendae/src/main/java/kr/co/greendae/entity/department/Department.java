package kr.co.greendae.entity.department;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "department")
public class Department {

    @Id
    private int deptNo;            // 학과번호
    private String college;         // 단과대학
    private String deptName;       // 학과명
    private String deptEname;   // 영문명
    private String establishedYear; // 설립연도
    private String deptChair;      // 학과장
    private String deptHp;         // 학과 연락처
    private String deptOffice;     // 학과 사무실

}
