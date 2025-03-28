package kr.co.greendae.dto.support;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LectureDTO {

    private String lecNo;
    private String proNo;

    private String lecClass;
    private String lecName;
    private String lecCate;
    private int lecGrade;   //학년
    private int lecCredit;
    private int lecStdTotal; //총 수강인원
    private int lecStdCount;
    private String lecRoom;
    private String lecTime;
    private String lecSchedule;
    private String book;



    private String proName;



}
