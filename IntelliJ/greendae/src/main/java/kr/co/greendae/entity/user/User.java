package kr.co.greendae.entity.user;

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
@Table(name = "User")
public class User {

    @Id
    private String uid;     // 아이디
    private String pass;    // 비밀번호
    private String name;    // 이름
    private String email;   // 이메일
    private String hp;      // 주소
    private String role;    // 역할(관리자, 학생, 교사)
    private String zip;     // IP주소
    private String addr1;   // 주소
    private String addr2;   // 자세한 주소
    private String regDate; // 가입일자
    private String leaveDate; //탈퇴일자

}
