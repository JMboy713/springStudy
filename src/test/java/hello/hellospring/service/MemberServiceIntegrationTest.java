package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional // test 후에 rollback을 해준다.
public class MemberServiceIntegrationTest {
    // Test code
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

//    @BeforeEach
//    public void beforeEach(){
//        memberRepository = new MemoryMemberRepository();
//        memberService = new MemberService(memberRepository);
//    }
//    @AfterEach// 각각 테스트 후 실행.
//    public void afterEach() {
//        memberRepository.clearStore();
//    } // 각각의 test 후에 repository 라는 내장 메모리를 clear 해준다.

    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("hello");
        //when
        Long saveId = memberService.join(member);
        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring0");

        Member member2 = new Member();
        member2.setName("spring0");
        //when
        memberService.join(member1);
        /*try{
            memberService.join(member2); // -> validate 에서 예외가 터져야함.
            fail();
        }catch(IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));// 뒤에 있는게 작동하면 앞의 있는 예외가 터져야함.
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");


    }


}
