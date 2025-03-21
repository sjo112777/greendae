package kr.co.greendae.entity.user;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "Student")
public class Student {

    @Id
    private String stdNo;           //학번

    @OneToOne
    @JoinColumn(name = "uid")
    private User user;

    private String admission_year;  //입학년도
    private String graduation_year; //졸업년도
    private String admission_type;  //입학구분(수시, 정시)
    private int stdYear;            //학년
    private String stdSemester;     //학기
    private String stdClass;        //학과
    private String major;           //전공
    private String advisor;         //지도교수
    private String stdStatus;       //재학상태
}
