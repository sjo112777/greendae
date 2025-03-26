package kr.co.greendae.data.first;

import kr.co.greendae.entity.university.University;
import kr.co.greendae.repository.department.UniversityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/*
* 대학 정보
* - 인문사회대학, 자연과학대학, 공과대학, 경영대학, 의과대학
* - 법과대학, 예술대학, 교육대학, 농업대학, 생명과학대학
* */
@SpringBootTest
public class UniversityData {
/*
    @Autowired private UniversityRepository  universityRepository;
    @Test
    public void  universityTest(){

        University university1 = University.builder()
                .name("인문사회대학")
                .engName("College of Humanities and Social Sciences")
                .title("인문사회대학 소개")
                .content("인문사회대학은 다양한 사회적 문제를 해결하기 위한 연구와 학문을 수행하는 학문 분야입니다. 심리학, 사회학, 정치학 등 여러 분야의 학문을 아우르며, 학생들에게 인문학적 사고를 통해 사회적 역할을 다하는 인재로 성장할 수 있도록 지원합니다.")
                .build();

        University university2 = University.builder()
                .name("자연과학대학")
                .engName("College of Natural Sciences")
                .title("자연과학대학 소개")
                .content("자연과학대학은 생명과학, 화학, 물리학 등 자연과학 분야에서 깊이 있는 연구를 진행하는 학문적 중심지입니다. 최신 실험 장비와 우수한 교수진을 갖추고, 이론과 실습을 통해 학문적 성취를 이루는 학습 환경을 제공합니다.")
                .build();

        University university3 = University.builder()
                .name("공과대학")
                .engName("College of Engineering")
                .title("공과대학 소개")
                .content("공과대학은 혁신적 기술과 공학적 문제 해결을 위한 교육과 연구를 진행하는 학문 분야입니다. 기계, 전자, 컴퓨터 공학 등을 통해 실생활에 적용 가능한 기술을 개발하고, 미래 산업을 이끌어 나갈 창의적인 엔지니어를 양성합니다.")
                .build();

        University university4 = University.builder()
                .name("경영대학")
                .engName("College of Business Administration")
                .title("경영대학 소개")
                .content("경영대학은 글로벌 비즈니스 환경에서 성공적인 경영 전략을 수립할 수 있는 리더를 양성하는 학문 분야입니다. 재무, 마케팅, 인사 등 다양한 경영학적 기법을 배울 수 있으며, 실습과 사례 분석을 통해 실제 경영 문제 해결 능력을 기를 수 있습니다.")
                .build();

        University university5 = University.builder()
                .name("의과대학")
                .engName("College of Medicine")
                .title("의과대학 소개")
                .content("의과대학은 인류의 건강과 복지 향상을 위한 연구와 교육을 제공합니다. 의학적 지식과 환자 치료 기술을 배울 수 있으며, 최신 의료 기술과 연구 결과를 통해 의사로서 필요한 능력을 함양할 수 있습니다.")
                .build();

        University university6 = University.builder()
                .name("법과대학")
                .engName("College of Law")
                .title("법과대학 소개")
                .content("법과대학은 법학적 사고와 실무를 배울 수 있는 교육 기관으로, 학생들에게 법률 문제를 체계적으로 분석하고 해결하는 능력을 기를 수 있습니다. 국내외 법적 이슈와 변화에 대응하는 법조인을 양성합니다.")
                .build();

        University university7 = University.builder()
                .name("예술대학")
                .engName("College of Arts")
                .title("예술대학 소개")
                .content("예술대학은 창의성과 예술적 감각을 키울 수 있는 학문 분야입니다. 음악, 미술, 연극 등 다양한 예술 분야에서 뛰어난 전문가가 될 수 있는 기회를 제공하며, 작품을 통해 자신을 표현할 수 있는 능력을 배웁니다.")
                .build();

        University university8 = University.builder()
                .name("교육대학")
                .engName("College of Education")
                .title("교육대학 소개")
                .content("교육대학은 미래의 교육을 이끌어갈 교사들을 양성하는 교육기관입니다. 교육학 이론과 실습을 통해 학생들에게 실제 교육 환경에서의 경험을 쌓을 수 있도록 지원하며, 혁신적인 교육 방법을 학습합니다.")
                .build();

        University university9 = University.builder()
                .name("농업대학")
                .engName("College of Agriculture")
                .title("농업대학 소개")
                .content("농업대학은 지속 가능한 농업과 농촌 개발을 위한 연구와 교육을 제공합니다. 농업 기술, 환경 관리 등을 배우고, 농업 관련 산업에서 전문가로서 활동할 수 있는 기초를 다질 수 있습니다.")
                .build();

        University university10 = University.builder()
                .name("생명과학대학")
                .engName("College of Life Sciences")
                .title("생명과학대학 소개")
                .content("생명과학대학은 생명의 기초부터 응용까지 다양한 분야를 연구하는 학문 분야입니다. 생물학, 유전자 연구, 생명공학 등 첨단 과학 기술을 활용하여 인류의 건강과 환경을 개선할 수 있는 방법을 찾고 있습니다.")
                .build();


        universityRepository.save(university1);
        universityRepository.save(university2);
        universityRepository.save(university3);
        universityRepository.save(university4);
        universityRepository.save(university5);
        universityRepository.save(university6);
        universityRepository.save(university7);
        universityRepository.save(university8);
        universityRepository.save(university9);
        universityRepository.save(university10);

    }


 */
}
