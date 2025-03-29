package kr.co.greendae.service;

import kr.co.greendae.dto.college.CollegeDTO;
import kr.co.greendae.dto.department.ChairPersonDTO;
import kr.co.greendae.dto.department.DepartmentDTO;
import kr.co.greendae.entity.college.College;
import kr.co.greendae.entity.department.Chairperson;
import kr.co.greendae.entity.department.Department;
import kr.co.greendae.repository.department.ChairPersonRepository;
import kr.co.greendae.repository.department.CollegeRepository;
import kr.co.greendae.repository.department.DepartmentRepository;
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
}
