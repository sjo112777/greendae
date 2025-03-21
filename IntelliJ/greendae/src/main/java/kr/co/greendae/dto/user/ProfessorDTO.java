package kr.co.greendae.dto.user;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ProfessorDTO {

    private String uid;               //아이디
    private String proNo;             //교수번호
    private String graduationSchool;  //졸업대학
    private String fieldOfStudy;      //학문분야
    private String graduationDate;    //졸업일
    private String degree;            //학위
    private String department;        //담당학과
    private String appointmentDate;   // 임용일

}
