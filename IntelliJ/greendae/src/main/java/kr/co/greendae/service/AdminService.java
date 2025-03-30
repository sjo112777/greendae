package kr.co.greendae.service;

import kr.co.greendae.dto.college.CollegeDTO;
import kr.co.greendae.dto.department.ChairPersonDTO;
import kr.co.greendae.dto.department.DepartmentDTO;
import kr.co.greendae.dto.support.StudentDTO;
import kr.co.greendae.dto.user.ProfessorDTO;
import kr.co.greendae.dto.user.UserDTO;
import kr.co.greendae.entity.college.College;
import kr.co.greendae.entity.department.Chairperson;
import kr.co.greendae.entity.department.Department;
import kr.co.greendae.entity.user.Professor;
import kr.co.greendae.entity.user.Student;
import kr.co.greendae.entity.user.User;
import kr.co.greendae.repository.department.ChairPersonRepository;
import kr.co.greendae.repository.department.CollegeRepository;
import kr.co.greendae.repository.department.DepartmentRepository;
import kr.co.greendae.repository.user.ProfessorRepository;
import kr.co.greendae.repository.user.StudentRepository;
import kr.co.greendae.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class AdminService {

    private final CollegeRepository collegeRepository;
    private final DepartmentRepository departmentRepository;
    private final ChairPersonRepository chairPersonRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final ProfessorRepository professorRepository;
    private final StudentRepository studentRepository;

    @Value("${spring.servlet.multipart.location}")
    private String uploadDir;

    // 이미지 등록
    public CollegeDTO uploadImage(CollegeDTO collageDTO) {


        java.io.File fileUploadDir = new java.io.File(uploadDir);

        if(!fileUploadDir.exists()){
            // 파일 업로드 디렉터리가 존재하지 않으면 생성
            fileUploadDir.mkdirs();
        }

        // 파일 업로드 디렉터리 시스템 경로 구하기
        String fileUploadPath = fileUploadDir.getAbsolutePath();
        log.info("fileUploadPath : {}", fileUploadPath);

        MultipartFile multipartFile = collageDTO.getImage();

        if(!multipartFile.isEmpty()){
            String oName = multipartFile.getOriginalFilename();
            String ext = oName.substring(oName.lastIndexOf("."));
            String sName = UUID.randomUUID().toString() + ext;

            // 파일 저장
            try {
                multipartFile.transferTo(new java.io.File(fileUploadPath, sName));
            } catch (IOException e) {
                log.error(e.getMessage());
            }

            collageDTO.setOName(oName);
            collageDTO.setSName(sName);

        }

        return collageDTO;

    }


    public void save(CollegeDTO collageDTO) {

        College college = modelMapper.map(collageDTO, College.class);
        collegeRepository.save(college);

    }

    public int getMaxDepNo() {
        Optional<Department> optDepartment = departmentRepository.findFirstByOrderByDeptNoDesc();
        if(optDepartment.isPresent()){
            return optDepartment.get().getDeptNo() + 1;
        }
        return 10;
    }

    public List<CollegeDTO> findAllCollege() {

        List<College>  collegeList = collegeRepository.findAll();
        List<CollegeDTO> collegeDTOS = new ArrayList<>();
        for(College college : collegeList){
            CollegeDTO collegeDTO = modelMapper.map(college, CollegeDTO.class);
            collegeDTOS.add(collegeDTO);
        }
        return collegeDTOS;

    }

    public List<ChairPersonDTO> findAllChairPerson() {

        List<Chairperson> list = chairPersonRepository.findAll();
        List<ChairPersonDTO> chairPersonDTOS = new ArrayList<>();
        for(Chairperson chairperson : list){
            ChairPersonDTO chairPersonDTO = modelMapper.map(chairperson, ChairPersonDTO.class);
            chairPersonDTOS.add(chairPersonDTO);
        }

        return chairPersonDTOS;
    }

    public void register(DepartmentDTO departmentDTO) {

        Department department = modelMapper.map(departmentDTO, Department.class);
        departmentRepository.save(department);
    }

    public List<DepartmentDTO> findAllDepartmentByName(String name) {

        List<Department> departments= departmentRepository.findAllByCollege(name);
        List<DepartmentDTO> departmentDTOS = new ArrayList<>();

        for(Department department : departments){
            DepartmentDTO departmentDTO = modelMapper.map(department, DepartmentDTO.class);
            departmentDTOS.add(departmentDTO);
        }

        return departmentDTOS;
    }

    public DepartmentDTO findDepartmentByName(String departmentD) {

        Department department = departmentRepository.findByDeptName(departmentD);
        return modelMapper.map(department, DepartmentDTO.class);
    }

    // 교수 등록 메서드
    public String registerUser(UserDTO userDTO, String code) {

        int number = 1;
        String num = code + "001";

        while(userRepository.existsById(num)){
            number += 1;
            num = String.valueOf((Integer.parseInt(num) + number));
        }

        userDTO.setUid(num);

        User user = modelMapper.map(userDTO, User.class);
        userRepository.save(user);

        return num;
    }


    public void registerProfessor(ProfessorDTO professorDTO, UserDTO userDTO, DepartmentDTO departmentDTO) {

        Professor professor = modelMapper.map(professorDTO, Professor.class);
        User user = modelMapper.map(userDTO, User.class);
        Department department = modelMapper.map(departmentDTO, Department.class);
        professor.setUser(user);
        professor.setDepartment(department);

        professorRepository.save(professor);

    }

    // 학과에 맞는 교수 출력
    public List<ProfessorDTO> findAllProfessorByName(String department) {

        Department findDepartment = departmentRepository.findByDeptName(department);
        System.out.println(findDepartment);
        System.out.println(findDepartment);
        System.out.println(findDepartment);
        List<Professor> professors = professorRepository.findByDepartment((findDepartment));
        List<ProfessorDTO> professorDTOS = new ArrayList<>();
        for(Professor professor : professors){
            professorDTOS.add(modelMapper.map(professor, ProfessorDTO.class));
        }

        return professorDTOS;
    }

    public void registerStudent(StudentDTO studentDTO, UserDTO userDTO, DepartmentDTO departmentDTO) {

        Optional<User> adviser = userRepository.findById(studentDTO.getAdvisor());
        if(adviser.isPresent()){
            Professor professor = professorRepository.findByUser(adviser.get());

            User user = modelMapper.map(userDTO, User.class);
            Department department = modelMapper.map(departmentDTO, Department.class);

            Student student = modelMapper.map(studentDTO, Student.class);
            student.setUser(user);
            student.setDepartment(department);
            student.setProfessor(professor);
            studentRepository.save(student);
        }
    }
}
