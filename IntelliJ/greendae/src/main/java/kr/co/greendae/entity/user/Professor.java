package kr.co.greendae.entity.user;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "Professor")
public class Professor {


    @OneToOne
    @JoinColumn(name = "uid")
    private User user;               //유저

    @Id
    private String proNo;             //교수번호
    private String graduationSchool;  //졸업대학
    private String fieldOfStudy;      //학문분야
    private String graduationDate;    //졸업일
    private String degree;            //학위
    private String department;        //담당학과
    private String appointmentDate;   // 임용일

}
