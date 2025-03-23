package kr.co.greendae.entity.Lecture;

import jakarta.persistence.*;
import kr.co.greendae.entity.user.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="Register")
public class Register {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int regNo;

    @ManyToOne
    @JoinColumn(name = "regStdNo")
    private Student student;

    /*
    *   Lecture (외례키 : LecNo)
    *   -------------------------------------------------
    *   lecCate  : 과목구분
    *   professor - user - name : 교수 이름
    *   lecRoom  : 강의실
    *   lecName  : 과목명
    *   lecGrade : 학년
    *   l
    * */

    @ManyToOne
    @JoinColumn(name = "regLecNo")
    private Lecture lecture;

    private String regAttenScore; // 출석점수
    private String regMidScore;   // 중간고수 점수
    private String regFinalScore; // 기말고수 점수
    private String regEtcScore;   // 기타점수
    private int regTotalScore;    // 총점
    // private int regCredit;        // 학점
    private String regGradeScore; //A,B,C,D,E

    /*
    * 고민1 : A,B,C,D,E,F
    * 컬럼을 2개로 한다. -> 조회할 때 따로 비즈니스 로직을 구현 안해도됨.
    * -> (편함) 하지만  A,B 와 학점은 서로 연관관계. 하나만 있어도 문제 없음.
    * 예 ) 4.0 -> A, 3.0 -> B
    * 데이터베이스 모델링 실습2 -> 평점(A,B,C,D)
    * https://drive.google.com/drive/folders/1Vow0jVg5fh3ROGwa9qywhuCgWfA1MFPy
    * */


    /*
    * 고민2 : 수업이 2024년 9월이라면?
    * regYear: 수업에 따라서 2024년이 아닐까?
    * regSemeter : 3~6월 1학기, 9월 12월 2학기
    * 두 가지 컬럼이 필요할까?
    * lecture에서 데이터를 들고와서 가공하는게 좋을까?
    * */
    // private String regYear;
    // private String regSemester;








/*
    public RegisterDTO toRegisterDTO(){
        return RegisterDTO.builder()
                .regNo(regNo)
                .regStdNo(student.getStdNo())
                .regLecNo(lecture.getLecNo())
                .regTotalScore(regTotalScore)
                .regGradeScore(regGradeScore)
                .regCredit(regCredit)
                .regYear(regYear)
                .regSemester(regSemester)
                //Lecture
                .lecName(lecture.getLecName())
                .lecCate(lecture.getLecCate())
                .lecGrade(lecture.getLecGrade())  // Default 0 or null 처리 가능
                .lecProName(lecture.getLecProName())
                .lecRoom(lecture.getLecRoom())
                .lecTime(lecture.getLecTime())
                .build();
    }
*/
    /*
    @Id
    private int regNo;

    @ManyToOne
    @JoinColumn(name = "stdNo")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "lecNo")
    private Lecture lecture;
    private String regLecName;
    private String regLecProName;
    private String regLecCate;
    private int regTotalScore;
    private String regGrade;
    private int regCredit;
    private String regLecRoom;
    private String regLecTime;


    public RegisterDTO toDTO(){
        return RegisterDTO.builder()
                .regNo(regNo)
                .regStdNo(String.valueOf(student))
                .regLecNo(String.valueOf(lecture))
                .regLecName(regLecName)
                .regLecProName(regLecProName)
                .regLecCate(regLecCate)
                .regTotalScore(regTotalScore)
                .regGrade(regGrade)
                .regCredit(regCredit)
                .regLecRoom(regLecRoom)
                .regLecTime(regLecTime)
                .build();
    }

     */


}
