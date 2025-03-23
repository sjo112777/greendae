package kr.co.greendae.entity.university;

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
@Table(name = "University")
public class University {

    @Id
    private String name;       // 대학 이름
    private String engName;    // 대학 영어 이름
    private String title;      // 소개 제목
    private String content;    // 소개 내용

}
