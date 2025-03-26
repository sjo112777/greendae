package kr.co.greendae.dto.support;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDTO {
    private String stdNo;       //학번
    private String admission_type;
    private String admission_year;
    private String graduation_year;
    private String stdSemester;
    private int stdYear;
    private int deptNo;
    private String prodNo;
    private String uid;

}
