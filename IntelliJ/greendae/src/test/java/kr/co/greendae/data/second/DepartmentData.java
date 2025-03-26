package kr.co.greendae.data.second;

import kr.co.greendae.entity.department.Department;
import kr.co.greendae.repository.department.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

/*
* 학과
* 학과 데이터 입니다.
* */
@SpringBootTest
public class DepartmentData {
/*
    @Autowired private DepartmentRepository departmentRepository;
    @Test
    public void depData(){

        Random rand = new Random();
        boolean exist = false;
        int no;
        do{
            no = rand.nextInt(90) + 10;
        }while(!departmentRepository.existsById(no));{
            no = rand.nextInt(90) + 10;
        }

        Department department = Department.builder()
                .deptNo(no)
                .deptChair("")
                .build();


         System.out.println(no);

    }

 */
}
