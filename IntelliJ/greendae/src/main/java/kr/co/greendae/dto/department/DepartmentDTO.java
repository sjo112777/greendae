package kr.co.greendae.dto.department;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class DepartmentDTO {
    private int deptNo;            // 학과번호
    private String university;         // 단과대학
    private String name;       // 학과명
    private String engName;   // 영문명
    private String establishedYear; // 설립연도
    private String deptChair;      // 학과장
    private String hp;         // 학과 연락처
    private String office;     // 학과 사무실

}
