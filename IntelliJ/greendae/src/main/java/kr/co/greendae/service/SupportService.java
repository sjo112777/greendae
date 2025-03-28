package kr.co.greendae.service;


import kr.co.greendae.dto.support.LectureDTO;
import kr.co.greendae.dto.support.RegisterDTO;

import kr.co.greendae.dto.support.StudentDTO;
import kr.co.greendae.entity.Lecture.Lecture;
import kr.co.greendae.repository.support.LectureRepository;
import kr.co.greendae.repository.support.RegisterRepository;

import kr.co.greendae.repository.user.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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


    public int findStudentYearByStdNo(String stdNo) {
        log.info("service##stdNo: {}", stdNo);

        return studentRepository.findYearByStdNo(stdNo);
    }

    public List<LectureDTO> findRegisterByStdNoByGrade(int stdYear) {

        Optional<Lecture> optlectures =lectureRepository.findByLecGrade(stdYear);

        List<Lecture> lectureList = new ArrayList<>();

        if (optlectures.isPresent()) {
            Lecture lecture = optlectures.get();  // 값 꺼내기
            lectureList.add(lecture);  // 리스트에 추가
        }

        System.out.println("123");
        System.out.println("123");
        System.out.println("123");
        System.out.println("123");
        System.out.println(lectureList);


        /*
        List<Object[]> optLectureStd = lectureRepository.findLecturesByYear(stdYear);
        log.info("service##stdYear: {}", stdYear);



        //ModelMapper
        List<LectureDTO> lectureDTOList = optLectureStd.stream().map(obj ->{
            LectureDTO lectureDTO = modelMapper.map(obj, LectureDTO.class);

            //lectureDTO.setLecClass((String) obj[3]);
            //lectureDTO.setLecCate((String) obj[2]);
            //lectureDTO.setLecGrade((Integer) obj[5]);
            //lectureDTO.setLecName((String) obj[6]);
            //lectureDTO.setLecCredit((Integer) obj[4]);
            //lectureDTO.setProName((String) obj[12]); // 교수명 설정
            //lectureDTO.setLecStdCount((Integer) obj[9]);
            //lectureDTO.setLecStdTotal((Integer) obj[10]);


            return lectureDTO;
        }).collect(Collectors.toList());
        log.info("service##lectureDTOList: {}", lectureDTOList);
        */


        return null;

    }



    public List<RegisterDTO> findRegisterByStdNo(String stdNo){
        List<Object[]> optRegisterStd = registerRepository.findRegisterByStdNo(stdNo);
        log.info("optRegisterStd : {}", optRegisterStd);

        // ModelMapper의 커스텀 매핑을 사용하여 RegisterDTO로 변환
        List<RegisterDTO> registerDTOList = optRegisterStd.stream().map(obj ->{
            RegisterDTO registerDTO = modelMapper.map(obj, RegisterDTO.class);

            registerDTO.setRegStdNo((String) obj[0]);  // 학생 번호
            registerDTO.setRegLecNo((String) obj[1]);  // 강의 번호
            registerDTO.setLecCredit((Integer) obj[2]); // 학점
            registerDTO.setLecName((String) obj[3]);  // 강의명
            registerDTO.setLecCate((String) obj[4]);  // 강의 카테고리
            registerDTO.setLecGrade((Integer) obj[5]);  // 강의 학년
            registerDTO.setLecProName((String) obj[6]);  // 교수명
            registerDTO.setLecRoom((String) obj[7]);  // 강의실
            registerDTO.setLecTime((String) obj[8]);  // 강의 시간

            return registerDTO;
        }).collect(Collectors.toList());

        log.info("service##registerDTOList : {}", registerDTOList);

        return registerDTOList;
    }

    public List<RegisterDTO> findGradeByStdNo(String stdNo){
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
            registerDTO.setLecCredit((Integer) obj[4]);
            registerDTO.setLecName((String) obj[5]);
            registerDTO.setLecCate((String) obj[6]);
            registerDTO.setLecGrade((Integer) obj[7]);
            registerDTO.setLecProName((String) obj[8]);

            return registerDTO;
        }).collect(Collectors.toList());

        log.info("gradeDTOList : {}", gradeDTOList);

        return gradeDTOList;
    }

    public List<StudentDTO> findRecordByStdNo(String stdNo) {
        List<Object[]> optRecordStd = studentRepository.findRecordByStdNo(stdNo);
        log.info("service##optRecordStd : {}", optRecordStd);

        // 각 obj의 타입을 로그로 찍어보기
        optRecordStd.stream().forEach(obj -> {
            for (int i = 0; i < obj.length; i++) {
                log.info("obj[{}] type: {}", i, obj[i].getClass().getName());
            }
        });

        List<StudentDTO> recordList = optRecordStd.stream().map(obj -> {
            StudentDTO studentDTO = modelMapper.map(obj, StudentDTO.class);
            studentDTO.setStdNo((String) obj[0]);
            studentDTO.setStdYear((Integer) obj[1]);
            studentDTO.setStdSemester((String) obj[2]);
            studentDTO.setStdClass((String) obj[3]);
            studentDTO.setStdStatus((String) obj[4]);
            studentDTO.setName((String) obj[5]);
            studentDTO.setHp((String) obj[6]);
            studentDTO.setEmail((String) obj[7]);
            studentDTO.setSsn((String) obj[8]);

            return studentDTO;

        }).collect(Collectors.toList());

        log.info("service##recordList : {}", recordList);

        return recordList;
    }

    public CreditSummary calculateCredits(String stdNo){
        List<Object[]> registerList = registerRepository.findRegisterByStdNo(stdNo);
        log.info("service##registerList : {}", registerList);

        // 학점을 합산할 변수들
        int major = 0;
        int liberalArts = 0;
        int elective = 0;
        int volunteer = 0;
        int other = 0;

        log.info("here1");

        // 'registerList'에서 각 Object[]를 순회하면서 학점 계산
        for (Object[] register : registerList) {
            registerList.stream().forEach(obj -> {
                for (int i = 0; i < obj.length; i++) {
                    log.info("obj[{}] type: {}", i, obj[i].getClass().getName());
                }
            });
            log.info("service##register : {}", register);
            // regLecNo를 통해 해당 lecture 엔티티 정보 가져오기
            String regstdNo = (String) register[0]; // regLecNo를 추출
            String regLecNo = (String) register[1];

            log.info("service##regLecNo : {}", regLecNo);

            // regLecNo를 이용해 Lecture 엔티티를 조회
            Optional<Lecture> optlec = lectureRepository.findById(regLecNo); // regLecNo를 통해 Lecture 엔티티 조회
            Lecture lecture = optlec.get();

            log.info("service##lecture : {}", lecture);
            log.info("service##lecturecate : {}", lecture.getLecCate());


            if (lecture == null) {
                log.info("here2");
                continue; // 만약 해당하는 Lecture가 없다면, 해당 항목을 무시하고 넘어갑니다.
            }

            log.info("here3");

            // Object 배열에서 필요한 데이터를 추출
            String lecCate = (String) register[4]; // '전공', '교양' 등 카테고리 정보

            log.info("service##lecCate : {}", lecCate);

            // 카테고리에 따라 학점 계산
            switch (lecCate) {
                case "전공":
                    major++;
                    break;
                case "교양":
                    liberalArts++;
                    break;
                case "선택":
                    elective++;
                    break;
                case "사회봉사":
                    volunteer++;
                    break;
                default:
                    other++;
                    break;
            }
        }
        //각 카테고리별 학점 계산 (갯수 * 3)
        int majorCredit = major * 3;
        int liberalArtsCredit = liberalArts * 3;
        int electiveCredit = elective * 3;
        int volunteerCredit = volunteer * 3;
        int otherCredit = other * 3;


        // 총 취득 학점 계산
        int total = majorCredit + liberalArtsCredit + electiveCredit + volunteerCredit + otherCredit;

        // CreditSummary 객체로 결과 반환
        return new CreditSummary(majorCredit, liberalArtsCredit, electiveCredit, volunteerCredit, otherCredit, total);

    }

    public static class CreditSummary {
        private int major;
        private int liberalArts;
        private int elective;
        private int volunteer;
        private int other;
        private int total;

        // 생성자
        public CreditSummary(int major, int liberalArts, int elective, int volunteer, int other, int total) {
            this.major = major;
            this.liberalArts = liberalArts;
            this.elective = elective;
            this.volunteer = volunteer;
            this.other = other;
            this.total = total;
        }

        public int getMajor() {
            return major;
        }

        public int getLiberalArts() {
            return liberalArts;
        }

        public int getElective() {
            return elective;
        }

        public int getVolunteer() {
            return volunteer;
        }

        public int getOther() {
            return other;
        }

        public int getTotal() {
            return total;
        }
    }


    //학과별
    public void findByClass(){}

    //과목명
    public void findByClassName(){}

    //교수명
    public void findByProfessor(){}

    //구분
    public void findByCate(){}



    public void modify(){}




}