package kr.co.greendae.repository.user;

import kr.co.greendae.entity.user.Professor;
import kr.co.greendae.entity.user.Student;
import kr.co.greendae.entity.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentRepositoryTest {


    /*
    @Autowired private StudentRepository studentRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private ProfessorRepository professorRepository;

    // 사용자 등록
    @Test
    public void test1(){

        // given - 테스트 데이터 준비
        User user = User.builder()
                .uid("user1")
                .pass("1234")
                .role("student")
                .build();

        System.out.println(user);
        userRepository.save(user);

        Student student = Student.builder()
                .stdNo("177301")
                .user(user)
                .build();

        studentRepository.save(student);

    }

    // 교수 등록
    @Test
    public void test2(){

        // given - 테스트 데이터 준비
        User user = User.builder()
                .uid("user3")
                .pass("1234")
                .role("professor")
                .build();

        userRepository.save(user);

        Professor professor = Professor
                .builder()
                .proNo("prod1")
                .user(user)
                .build();

        professorRepository.save(professor);


    }

     */
}