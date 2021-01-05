package cosmetics.demo.Service;

import cosmetics.demo.Domain.Entity.MemberEntity;
import cosmetics.demo.Domain.Repository.MemberRepository;
import cosmetics.demo.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원가입
     */
    public Long join(MemberDto memberDto){
        validateDuplicateMember(memberDto);
        memberRepository.save(memberDto.toEntity());
        return memberDto.getId();
    }

    //==중복 방지==//
    private void validateDuplicateMember(MemberDto memberDto) {
        List<MemberEntity> members = memberRepository.findAll();
        for(int i=0;i<members.size(); ++i){
            if(members.get(i).getEmail().equals(memberDto.getEmail())){
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            }
        }
    }

    public int login(MemberDto memberDto){
        MemberEntity byEmail = memberRepository.findByEmail(memberDto.getEmail());
        MemberEntity byPassword = memberRepository.findByPassword(memberDto.getPassword());
        List<MemberEntity> all = memberRepository.findAll();
        for(int i=0;i<all.size(); ++i){
            if(all.get(i).equals(byEmail)){
                if(all.get(i).equals(byPassword)){
                    return 1;
                }
            }
        }
        return 0;

    }
    private MemberDto convertEntityToDto(MemberEntity memberEntity){
        return MemberDto.builder()
                .id(memberEntity.getId())
                .email(memberEntity.getEmail())
                .password(memberEntity.getPassword())
                .build();
    }
}