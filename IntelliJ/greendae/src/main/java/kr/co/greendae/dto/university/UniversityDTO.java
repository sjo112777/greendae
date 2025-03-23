package kr.co.greendae.dto.university;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UniversityDTO {

    private String name;       // 대학 이름
    private String engName;    // 대학 영어 이름
    private String title;      // 소개 제목
    private String content;    // 소개 내용

}
