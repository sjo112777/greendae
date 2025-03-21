$(document).ready(function() {
    // li 클릭 시 active 클래스 추가
    $('.demo_list li a').click(function(e) {
        // 기본 클릭 동작 방지
        e.preventDefault();

        // 모든 li에서 active 클래스 제거
        $('.demo_list li').removeClass('active');
        
        // 클릭한 li에 active 클래스 추가
        $(this).parent().addClass('active');
        
        // 클릭한 항목에 맞는 콘텐츠 로드
        var target = $(this).attr('href').substring(1); // #default 같은 형식에서 # 제거
        
        // 해당 콘텐츠를 보여줌
        loadContent(target);
    });

    // 페이지가 처음 로드될 때 기본 콘텐츠 로드
    loadContent('human');
});

function loadContent(target) {
    var content = '';

    switch (target) {
        case 'human':
            content = `
                    <img src="/greendae/images/college-introduce-1.jpg" alt="인문사회대학" width="930" height="300"/>
                    <h2>인문사회대학 학생회는?</h2>
                    <p>인문사회대학 학생회는 인문사회대학 학부생을 대표하는 단체로, 인문사회대학 학부생들이 학생사회의 구성원으로서 가지는 이해와 요구를 최대한 반영하여 실현하는 것을 목적으로 합니다. 이러한 목적을 달성하기 위해 학생회는 필요에 따라 구성원의 의견을 수렴하기도 하고, 때로는 학생사회의 새로운 의제를 제시하기도 하며, 구성원 간 화합을 위해 다양한 행사를 준비하기도 합니다.</p><br>
                    <p>인문사회대학 학생회의 핵심 조직에는 운영위원회와 집행위원회가 있습니다. 운영위원회는 인문사회대학 학생회장단, 각 학부 · 과의 학생회장, 자연대 동아리 연합회 의장으로 구성되며 정기적인 운영위원회를 통하여 인문사회대학 내외의 여러 사안에 대해 논의하는 상임 의결기구입니다. 반면 집행위원회는 집행위원장과 집행위원들로 구성되며 자연대 학생 대표자 회의와 운영위원회의 의결에 근거하여 사업을 집행하는 상설 집행기구입니다.</p><br>
                    <p>인문사회과학대학은 민족의 문화적 유산과 전통을 창조적으로 계승.발전시킬 열린 민족인, 세계화된 지식, 정보사회를 주도할 수 있는 국제적인 식견을 갖춘 전문인, 법, 정치 경제, 언론등 우리사회의 중심을 이루는 영역에서 정의로운 사명감을 가지고 일 할 양심적인 봉사자, 그리고 창의적인 디자인 활동을 할 개성적인 인재를 양성하기 위해서 인문, 사회, 디자인 영역의 다양하고 심화된 이론과 실제를 교수, 연구하는데 교육목표를 두고 있습니다.</p><br><br>
                    <h2>학생회 사업 및 행사</h2>
                    <table>
                        <tr>
                            <th>새내기 새로 배움터</th>
                            <td>신입생들이 입학하기 전, 대학 생활 적응에 도움이 되는 여러 정보를 전달하고 구성원 간의 친목을 도모하기 위한 행사입니다. 행사는 신입생 오리엔테이션, 학부/과별로 진행되는 각종 게임과 학생회에서 준비한 폐막제 등으로 구성됩니다.</td>
                        </tr>
                        <tr>
                            <th>시험 기간 간식 행사</th>
                            <td>시험에 지친 자연과학대학 학우들을 응원하는 행사입니다. 다양한 간식들을 마련하여 학생들에게 배부하며, 자연과학대학 구성원으로서의 결속력을 강화하는 계기가 됩니다.</td>
                        </tr>
                        <tr>
                            <th>전공학교</th>
                            <td>학기 중 어려운 전공 공부를 따라갈 수 있도록 미리 그 과목을 수강한 선배가 멘토가 되어 방학 중 수요가 높은 전공과목의 학습을 도와주는 프로그램입니다.</td>
                        </tr>
                        <tr>
                            <th>인문사회대학 사회봉사단</th>
                            <td>인문사회대학과 학생회가 함께하여 8월 초, 4박 5일간 여러 지역에 내려가 지역 학생들과 함께 미리 준비한 다양한 사회 관련 프로그램을 진행합니다. 각 학부/과별로 그 특성에 맞는 다양한 프로그램을 진행하고 고등학생들의 멘토가 되어주면서 사회에 이바지하는 봉사 정신을 배우게 됩니다.</td>
                        </tr>
                    </table>
            `;
            break;
        case 'nature':
            content = `
                    <img src="/greendae/images/college-introduce-2.jpg" alt="자연과학대학" width="930" height="300"/>
                    <h2>자연과학대학 학생회는?</h2>
                    <p>자연과학대학 학생회는 자연과학대학 학부생을 대표하는 단체로, 자연과학대학 학부생들이 학생사회의 구성원으로서 가지는 이해와 요구를 최대한 반영하여 실현하는 것을 목적으로 합니다. 이러한 목적을 달성하기 위해 학생회는 필요에 따라 구성원의 의견을 수렴하기도 하고, 때로는 학생사회의 새로운 의제를 제시하기도 하며, 구성원 간 화합을 위해 다양한 행사를 준비하기도 합니다.</p><br>
                    <p>자연과학대학 학생회의 핵심 조직에는 운영위원회와 집행위원회가 있습니다. 운영위원회는 자연과학대학 학생회장단, 각 학부 · 과의 학생회장, 자연대 동아리 연합회 의장으로 구성되며 정기적인 운영위원회를 통하여 자연과학대학 내외의 여러 사안에 대해 논의하는 상임 의결기구입니다. 반면 집행위원회는 집행위원장과 집행위원들로 구성되며 자연대 학생 대표자 회의와 운영위원회의 의결에 근거하여 사업을 집행하는 상설 집행기구입니다.</p><br>
                    <p>자연과학대학은 미래 첨단과학기술사회의 학계, 연구소, 산업계 등을 주도적으로 이끌어 나갈 기초과학 인재 양성을 교육목표로 합니다. </p>
                    <p>자연과학의 근간이 되는 물리, 화학, 응용수학, 미생물학, 과학컴퓨팅 및 간호학의 기초 이론을 교수·연구하고 융합 및 응용과학의 토대를 제공할 것입니다.</p><br><br>
                    <h2>학생회 사업 및 행사</h2>
                    <table>
                        <tr>
                            <th>새내기 새로 배움터</th>
                            <td>신입생들이 입학하기 전, 대학 생활 적응에 도움이 되는 여러 정보를 전달하고 구성원 간의 친목을 도모하기 위한 행사입니다. 행사는 신입생 오리엔테이션, 학부/과별로 진행되는 각종 게임과 학생회에서 준비한 폐막제 등으로 구성됩니다.</td>
                        </tr>
                        <tr>
                            <th>시험 기간 간식 행사</th>
                            <td>시험에 지친 자연과학대학 학우들을 응원하는 행사입니다. 다양한 간식들을 마련하여 학생들에게 배부하며, 자연과학대학 구성원으로서의 결속력을 강화하는 계기가 됩니다.</td>
                        </tr>
                        <tr>
                            <th>전공학교</th>
                            <td>학기 중 어려운 전공 공부를 따라갈 수 있도록 미리 그 과목을 수강한 선배가 멘토가 되어 방학 중 수요가 높은 전공과목의 학습을 도와주는 프로그램입니다.</td>
                        </tr>
                        <tr>
                            <th>자연과학대 과학봉사단</th>
                            <td>자연과학대학과 학생회가 함께하여 8월 초, 4박 5일간 여러 지역에 내려가 지역 학생들과 함께 미리 준비한 다양한 과학 관련 프로그램을 진행합니다. 각 학부/과별로 그 특성에 맞는 다양한 프로그램을 진행하고 고등학생들의 멘토가 되어주면서 사회에 이바지하는 봉사 정신을 배우게 됩니다.</td>
                        </tr>
                    </table>
            `;
            break;
        case 'computer':
            content = `
                    <img src="/greendae/images/college-introduce-3.jpg" alt="공과대학" width="930" height="300"/>
                    <h2>공과대학 학생회는?</h2>
                    <p>공과대학 학생회는 공과대학 학부생을 대표하는 단체로, 공과대학 학부생들이 학생사회의 구성원으로서 가지는 이해와 요구를 최대한 반영하여 실현하는 것을 목적으로 합니다. 이러한 목적을 달성하기 위해 학생회는 필요에 따라 구성원의 의견을 수렴하기도 하고, 때로는 학생사회의 새로운 의제를 제시하기도 하며, 구성원 간 화합을 위해 다양한 행사를 준비하기도 합니다.</p><br>
                    <p>공과대학 학생회의 핵심 조직에는 운영위원회와 집행위원회가 있습니다. 운영위원회는 공과대학 학생회장단, 각 학부 · 과의 학생회장, 공과대학 동아리 연합회 의장으로 구성되며 정기적인 운영위원회를 통하여 공과대학 내외의 여러 사안에 대해 논의하는 상임 의결기구입니다. 반면 집행위원회는 집행위원장과 집행위원들로 구성되며 자연대 학생 대표자 회의와 운영위원회의 의결에 근거하여 사업을 집행하는 상설 집행기구입니다.</p><br>
                    <p>공과대학은 지역 및 국가산업을 선도할 창의적이고 생산적인 전문기술인을 양성하기 위하여 기본 공학교육의 강화,전공교육의 심화 및 산업현장과 연계된 산학협동의 활성화를 실현할 관련학문을 교수, 연구하는데 교육목표를 두고 있습니다.</p><br><br>
                    <h2>학생회 사업 및 행사</h2>
                    <table>
                        <tr>
                            <th>새내기 새로 배움터</th>
                            <td>신입생들이 입학하기 전, 대학 생활 적응에 도움이 되는 여러 정보를 전달하고 구성원 간의 친목을 도모하기 위한 행사입니다. 행사는 신입생 오리엔테이션, 학부/과별로 진행되는 각종 게임과 학생회에서 준비한 폐막제 등으로 구성됩니다.</td>
                        </tr>
                        <tr>
                            <th>시험 기간 간식 행사</th>
                            <td>시험에 지친 자연과학대학 학우들을 응원하는 행사입니다. 다양한 간식들을 마련하여 학생들에게 배부하며, 자연과학대학 구성원으로서의 결속력을 강화하는 계기가 됩니다.</td>
                        </tr>
                        <tr>
                            <th>전공학교</th>
                            <td>학기 중 어려운 전공 공부를 따라갈 수 있도록 미리 그 과목을 수강한 선배가 멘토가 되어 방학 중 수요가 높은 전공과목의 학습을 도와주는 프로그램입니다.</td>
                        </tr>
                        <tr>
                            <th>공과대학 과학봉사단</th>
                            <td>공과대학 학생회가 함께하여 8월 초, 4박 5일간 여러 지역에 내려가 지역 학생들과 함께 미리 준비한 다양한 과학 관련 프로그램을 진행합니다. 각 학부/과별로 그 특성에 맞는 다양한 프로그램을 진행하고 고등학생들의 멘토가 되어주면서 사회에 이바지하는 봉사 정신을 배우게 됩니다.</td>
                        </tr>
                    </table>
                    `;
            break;
        case 'education':
            content = `
                    <img src="/greendae/images/college-introduce-4.jpg" alt="사범대학" width="930" height="300"/>
                    <h2>사범대학 학생회는?</h2>
                    <p>사범대학 학생회는 사범대학 학부생을 대표하는 단체로, 사범대학 학부생들이 학생사회의 구성원으로서 가지는 이해와 요구를 최대한 반영하여 실현하는 것을 목적으로 합니다. 이러한 목적을 달성하기 위해 학생회는 필요에 따라 구성원의 의견을 수렴하기도 하고, 때로는 학생사회의 새로운 의제를 제시하기도 하며, 구성원 간 화합을 위해 다양한 행사를 준비하기도 합니다.</p><br>
                    <p>사범대학 학생회의 핵심 조직에는 운영위원회와 집행위원회가 있습니다. 운영위원회는 사범대학 학생회장단, 각 학부 · 과의 학생회장, 자연대 동아리 연합회 의장으로 구성되며 정기적인 운영위원회를 통하여 사범대학 내외의 여러 사안에 대해 논의하는 상임 의결기구입니다. 반면 집행위원회는 집행위원장과 집행위원들로 구성되며 자연대 학생 대표자 회의와 운영위원회의 의결에 근거하여 사업을 집행하는 상설 집행기구입니다.</p><br>
                    <p>전공영역은 물론 현대 교육에 대한 학문적 탐구의 자질 및 인격 도야에 중점을 두고 있습니다.</p>
                    <p>원격강의 , 스튜디오, 원격교육강의실, 교육매체 제작실, 멀티미디어 강의실, 어학학습실, 수업행동 분석실 등 첨단 시설을 갖춘 교육지원센터를 운영하고 있습니다.</p>
                    <p>부속기관으로는 중등교육연수원, 과학교육연구소, 부설고등학교 등이 있습니다</p><br><br>
                    <h2>학생회 사업 및 행사</h2>
                    <table>
                        <tr>
                            <th>새내기 새로 배움터</th>
                            <td>신입생들이 입학하기 전, 대학 생활 적응에 도움이 되는 여러 정보를 전달하고 구성원 간의 친목을 도모하기 위한 행사입니다. 행사는 신입생 오리엔테이션, 학부/과별로 진행되는 각종 게임과 학생회에서 준비한 폐막제 등으로 구성됩니다.</td>
                        </tr>
                        <tr>
                            <th>시험 기간 간식 행사</th>
                            <td>시험에 지친 자연과학대학 학우들을 응원하는 행사입니다. 다양한 간식들을 마련하여 학생들에게 배부하며, 자연과학대학 구성원으로서의 결속력을 강화하는 계기가 됩니다.</td>
                        </tr>
                        <tr>
                            <th>전공학교</th>
                            <td>학기 중 어려운 전공 공부를 따라갈 수 있도록 미리 그 과목을 수강한 선배가 멘토가 되어 방학 중 수요가 높은 전공과목의 학습을 도와주는 프로그램입니다.</td>
                        </tr>
                        <tr>
                            <th>사범대학 교육봉사단</th>
                            <td>사범대학 학생회가 함께하여 8월 초, 4박 5일간 여러 지역에 내려가 지역 학생들과 함께 미리 준비한 다양한 교육 관련 프로그램을 진행합니다. 각 학부/과별로 그 특성에 맞는 다양한 프로그램을 진행하고 고등학생들의 멘토가 되어주면서 사회에 이바지하는 봉사 정신을 배우게 됩니다.</td>
                        </tr>
                    </table>
            `;
            break;
        default:
            content = '<p>Content not found.</p>';
    }

    // 콘텐츠를 #content에 삽입
    $('#content').html(content);
}
