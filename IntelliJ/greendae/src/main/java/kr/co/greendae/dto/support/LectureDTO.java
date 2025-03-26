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
    private String lecClass;
    private String book;
    private String lecCate;
    private int lecCredit;
    private int lecGrade;   //학년
    private String lecName;
    private String lecRoom;
    private String lecSchedule;
    private int lecStdCount;
    private String lecTime;
    private String proName;



}
