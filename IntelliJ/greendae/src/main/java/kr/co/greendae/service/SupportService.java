package kr.co.greendae.service;


import kr.co.greendae.dto.support.LectureDTO;
import kr.co.greendae.dto.support.RegisterDTO;
import kr.co.greendae.entity.Lecture.Lecture;
import kr.co.greendae.repository.support.LectureRepository;
import kr.co.greendae.repository.support.RegisterRepository;

import kr.co.greendae.repository.user.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class SupportService {

    private final LectureRepository lectureRepository;
    private final RegisterRepository registerRepository;
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    public void register(){}

    public List<LectureDTO> findAll(){
        List<Lecture> lecturesEntities = lectureRepository.findAll();
        // ModelMapper를 사용하여 Lecture 엔티티를 LectureDTO로 변환 후 리스트로 반환
        return lecturesEntities
                .stream()
                .map(lecture -> modelMapper.map(lecture, LectureDTO.class))
                .collect(Collectors.toList());
    }

    public List<RegisterDTO> findRegisterByStdNo(@Param("stdNo") String stdNo){
        List<Object[]> optRegisterStd = registerRepository.findRegisterByStdNo(stdNo);
        log.info("optRegisterStd : {}", optRegisterStd);

        // ModelMapper의 커스텀 매핑을 사용하여 RegisterDTO로 변환
        List<RegisterDTO> registerDTOList = optRegisterStd.stream().map(obj ->{
            RegisterDTO registerDTO = modelMapper.map(obj, RegisterDTO.class);

            registerDTO.setRegStdNo((String) obj[0]);  // 학생 번호
            registerDTO.setRegLecNo((String) obj[1]);  // 강의 번호
            registerDTO.setLecCredit((String) obj[2]); // 학점
            registerDTO.setLecName((String) obj[3]);  // 강의명
            registerDTO.setLecCate((String) obj[4]);  // 강의 카테고리
            registerDTO.setLecGrade((Integer) obj[5]);  // 강의 학년
            registerDTO.setLecProName((String) obj[6]);  // 교수명
            registerDTO.setLecRoom((String) obj[7]);  // 강의실
            registerDTO.setLecTime((String) obj[8]);  // 강의 시간

            return registerDTO;
        }).collect(Collectors.toList());

        log.info("registerDTOList : {}", registerDTOList);

        return registerDTOList;
    }

    public List<RegisterDTO> findGradeByStdNo(@Param("stdNo") String stdNo){
        List<Object[]> optGradeStd = registerRepository.findGradeByStdNo(stdNo);
        log.info("optGradeStd : {}", optGradeStd);

        // 각 obj의 타입을 로그로 찍어보기
        optGradeStd.stream().forEach(obj -> {
            for (int i = 0; i < obj.length; i++) {
                log.info("obj[{}] type: {}", i, obj[i].getClass().getName());
            }
        });

        List<RegisterDTO> gradeDTOList = optGradeStd.stream().map(obj -> {
            RegisterDTO registerDTO = modelMapper.map(obj, RegisterDTO.class);
            registerDTO.setRegStdNo((String) obj[0]);
            registerDTO.setRegLecNo((String) obj[1]);
            registerDTO.setRegTotalScore((Integer) obj[2]);
            registerDTO.setRegGradeScore((String) obj[3]);
            registerDTO.setLecCredit((String) obj[4]);
            registerDTO.setLecName((String) obj[5]);
            registerDTO.setLecCate((String) obj[6]);
            registerDTO.setLecGrade((Integer) obj[7]);
            registerDTO.setLecProName((String) obj[8]);

            return registerDTO;
        }).collect(Collectors.toList());

        log.info("gradeDTOList : {}", gradeDTOList);

        return gradeDTOList;
    }

    public void findByName(){}

    public void findByClassName(){}

    public void findByProfessor(){}

    public void modify(){}


}