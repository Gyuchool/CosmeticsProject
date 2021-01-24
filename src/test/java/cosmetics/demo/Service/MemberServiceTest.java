package cosmetics.demo.Service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class MemberServiceTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Test
    public void 회원가입() throws Exception{
        //given
        MemberDto memberDto = new MemberDto();
        MemberDto memberDto1 = new MemberDto();
        memberDto.setEmail("sa");
        memberDto.setPassword("123");
        memberRepository.save(memberDto.toEntity());

        memberService.join(memberDto);
        //when

        memberRepository.findOne(memberDto.getId());
        //then


    }
}