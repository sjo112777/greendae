package kr.co.greendae.service;


import com.querydsl.core.Tuple;

import kr.co.greendae.dto.support.LectureDTO;
import kr.co.greendae.dto.support.RegisterDTO;

import kr.co.greendae.dto.support.StudentDTO;
import kr.co.greendae.dto.support.pageRegister.PageRequestDTO;
import kr.co.greendae.dto.support.pageRegister.PageResponseDTO;
import kr.co.greendae.dto.support.pageRegisterList.RegisteredPageRequestDTO;
import kr.co.greendae.dto.support.pageRegisterList.RegisteredPageResponseDTO;
import kr.co.greendae.entity.Lecture.Lecture;
import kr.co.greendae.entity.Lecture.Register;
import kr.co.greendae.entity.user.Student;
import kr.co.greendae.repository.support.LectureRepository;
import kr.co.greendae.repository.support.RegisterRepository;

import kr.co.greendae.repository.user.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    public List<LectureDTO> findAll() {
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

    public PageResponseDTO searchAll(PageRequestDTO pageRequestDTO, int stdYear) {
        //페이징 처리를 위한 pageable 객체 생성
        Pageable pageable = pageRequestDTO.getPageable("no");

        Page<Tuple> pageLecture = lectureRepository.searchLecturesByStdNoAndStdYear(pageRequestDTO, pageable, stdYear);

        //변환
        List<LectureDTO> lectureDTOList = pageLecture.getContent().stream().map(tuple -> {
            Lecture lecture = tuple.get(0, Lecture.class);
            String proName = tuple.get(1, String.class);

            LectureDTO lectureDTO = modelMapper.map(lecture, LectureDTO.class);
            lectureDTO.setProName(proName);

            return lectureDTO;
        }).toList();

        int total = (int) pageLecture.getTotalElements();

        return PageResponseDTO
                .builder()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(lectureDTOList)
                .total(total)
                .build();
    }

    public PageResponseDTO findRegisterByStdNoByGrade(PageRequestDTO pageRequestDTO, int stdYear) {

        // 페이징 처리
        Pageable pageable = pageRequestDTO.getPageable("lecNo");

        // 페이징을 포함한 강의 목록 조회
        Page<Tuple> pageLecture = lectureRepository.selectAllByStdNoAndStdYear(pageable, stdYear);
        log.info("pageLecture: {}", pageLecture);

        // 페이지 정보에서 강의 DTO 리스트 변환
        List<LectureDTO> lectureDTOList = pageLecture.getContent().stream()
                .map(tuple -> {
                    Lecture lecture = tuple.get(0, Lecture.class);
                    LectureDTO lectureDTO = modelMapper.map(lecture, LectureDTO.class);
                    lectureDTO.setLecGrade(lecture.getLecGrade());
                    return lectureDTO;
                })
                .toList();

        // 전체 강의 수
        int total = (int) pageLecture.getTotalElements();

        // 페이지 DTO 반환
        return PageResponseDTO.builder()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(lectureDTOList)
                .total(total)
                .build();
    }


    public RegisteredPageResponseDTO findRegisterByStdNo(RegisteredPageRequestDTO registeredPageRequestDTO, String stdNo) {
        //페이징
        Pageable pageable = registeredPageRequestDTO.getPageable("no");

        //페이징을 포함한 목록 조회
        Page<Tuple> pageRegistered = registerRepository.findRegisterByStdNo(pageable, stdNo);
        log.info("pageRegistered: {}", pageRegistered);

        List<RegisterDTO> registerDTOList = pageRegistered.getContent().stream()
                .map(tuple -> {
                    Register register = tuple.get(0, Register.class);
                    RegisterDTO registerDTO = modelMapper.map(register, RegisterDTO.class);

                    registerDTO.setRegStdNo(register.getStudent().getStdNo());// 학생 번호
                    registerDTO.setRegLecNo(register.getLecture().getLecNo());  // 강의 번호
                    registerDTO.setLecCredit(register.getLecture().getLecCredit()); // 학점
                    registerDTO.setLecName(register.getLecture().getLecName());  // 강의명
                    registerDTO.setLecCate(register.getLecture().getLecCate());  // 강의 카테고리
                    registerDTO.setLecGrade(register.getLecture().getLecGrade());  // 강의 학년
                    registerDTO.setLecProName(register.getLecture().getProfessor().getUser().getName());  // 교수명
                    registerDTO.setLecRoom(register.getLecture().getLecRoom());  // 강의실
                    registerDTO.setLecWeekday(register.getLecture().getLecWeekday());

                    return registerDTO;
                })
                .toList();

        //전체 강의 수
        int total = (int) pageRegistered.getTotalElements();

        //페이지 DTO 반환
         return RegisteredPageResponseDTO.builder()
                 .pageRequestDTO(registeredPageRequestDTO)
                 .dtoList(registerDTOList)
                 .total(total)
                 .build();

        //List<Object[]> optRegisterStd = registerRepository.findRegisterByStdNo(stdNo);
        //log.info("optRegisterStd : {}", optRegisterStd);

        // ModelMapper의 커스텀 매핑을 사용하여 RegisterDTO로 변환
        /*List<RegisterDTO> registerDTOList = optRegisterStd.stream().map(obj -> {
            RegisterDTO registerDTO = modelMapper.map(obj, RegisterDTO.class);

            registerDTO.setRegStdNo((String) obj[0]);  // 학생 번호
            registerDTO.setRegLecNo((String) obj[1]);  // 강의 번호
            registerDTO.setLecCredit((Integer) obj[2]); // 학점
            registerDTO.setLecName((String) obj[3]);  // 강의명
            registerDTO.setLecCate((String) obj[4]);  // 강의 카테고리
            registerDTO.setLecGrade((Integer) obj[5]);  // 강의 학년
            registerDTO.setLecProName((String) obj[6]);  // 교수명
            registerDTO.setLecRoom((String) obj[7]);  // 강의실
            registerDTO.setLecWeekday((String) obj[8]);

            return registerDTO;
        }).collect(Collectors.toList());

         */
    }

    public List<RegisterDTO> findGradeByStdNo(String stdNo) {
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
            studentDTO.setRegisterCredits((Integer) obj[9]);

            return studentDTO;

        }).collect(Collectors.toList());

        log.info("service##recordList : {}", recordList);

        return recordList;
    }

    public CreditSummary calculateCredits(String stdNo) {
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
            if ("전공".equals(lecCate) || "전공필수".equals(lecCate)) {
                major++; // 전공 또는 전공필수는 전공으로 처리
            } else if ("교양".equals(lecCate)) {
                liberalArts++;
            } else if ("선택".equals(lecCate)) {
                elective++;
            } else if ("사회봉사".equals(lecCate)) {
                volunteer++;
            } else {
                other++;
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

    public StudentDTO findStudentByStdNo(String stdNo) {

        Optional<Student> optstd = studentRepository.findById(stdNo);

        if (optstd.isPresent()) {
            return modelMapper.map(optstd.get(), StudentDTO.class);
        }

        return null;

    }

    public List<LectureDTO> findLectureByLecCate(StudentDTO studentDTO, String year) {

        int lecGrade = Integer.parseInt(year);
        String lecClass = studentDTO.getStdClass();

        // 전공필수 전공선택
        List<Lecture> lectureList = lectureRepository.findByLecClassAndLecGrade(lecClass, lecGrade);

        List<LectureDTO> lectureDTOList = new ArrayList<>();

        for (Lecture lecture : lectureList) {
            LectureDTO lectureDTO = modelMapper.map(lecture, LectureDTO.class);

            if (lectureDTO.getLecCate().contains("전공")) {
                lectureDTOList.add(lectureDTO);
            }
        }

        return lectureDTOList;
    }

    public List<LectureDTO> findLectureByLecClass(StudentDTO studentDTO, String cate) {
        String lecClass = studentDTO.getStdClass();
        List<Lecture> lectureGeneralList = lectureRepository.findByLecClassAndLecCate(lecClass, cate);

        List<LectureDTO> lectureDTOList = new ArrayList<>();
        for (Lecture lecture : lectureGeneralList) {
            LectureDTO lectureDTO = modelMapper.map(lecture, LectureDTO.class);
            lectureDTOList.add(lectureDTO);
        }

        return lectureDTOList;
    }

    public Map<Integer, List<LectureDTO>> getGroupedLectureList(StudentDTO studentDTO, String year) {
        int lecGrade = Integer.parseInt(year);
        String lecClass = studentDTO.getStdClass();

        // 📌 해당 학년 & 학과의 강의 리스트 조회
        List<Lecture> lectureList = lectureRepository.findByLecClassAndLecGrade(lecClass, lecGrade);

        // 📌 Lecture -> LectureDTO 변환 후 lecGrade 기준으로 그룹화
        return lectureList.stream()
                .map(lecture -> modelMapper.map(lecture, LectureDTO.class))
                .collect(Collectors.groupingBy(LectureDTO::getLecGrade));
    }

/*
    public boolean registerLecture(RegisterDTO registerDTO) {
        try{
            Lecture lecture = lectureRepository.findByLecNo(registerDTO.getRegLecNo());
            Student student = studentRepository.findByStdNo(registerDTO.getRegLecNo());

            //Register 엔티티 생성
            Register register = Register.builder()
                    .student(student)
                    .lecture(lecture)
                    .regYear(registerDTO.getRegYear())
                    .regSemester(registerDTO.getRegSemester())
                    .build();

            registerRepository.save(register);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
*/
    public Lecture findLectureByLecNo(String lecNo) {
        Optional<Lecture> optLecture = lectureRepository.findById(lecNo);
        return optLecture.get();

    }

    public boolean registerLecture(StudentDTO studentDTO, Lecture lecture) {

        Student student = modelMapper.map(studentDTO, Student.class);

        Register register = new Register();
        register.setStudent(student);
        register.setLecture(lecture);

        String lecYear = lecture.getLecScheduleStart();
        String[] strs = lecYear.split("-");

        String year = strs[0];
        String month = strs[1];

        String regSemester = null;

        if(month.equals("03") ||month.equals("04") || month.equals("05") || month.equals("06") ){
            regSemester = "1학기";
        }else{
            regSemester = "2학기";
        }

        register.setRegSemester(regSemester);
        register.setRegYear(year);

        // 수강 등록하기
        registerRepository.save(register);

        // 수강 인원 수 더하기
        // lecStdCount를 들고와서 +1하고 save하기
        // lecture.set
        int count = lecture.getLecStdCount();
        lecture.setLecStdCount(count + 1);
        lectureRepository.save(lecture);


        return true;
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
    public void findByClass() {
    }

    //과목명
    public void findByClassName() {
    }

    //교수명
    public void findByProfessor() {
    }

    //구분
    public void findByCate() {
    }


    public void modify() {
    }
}