
package cosmetics.demo.Service;

import cosmetics.demo.Domain.Entity.Member.MemberEntity;
import cosmetics.demo.Domain.Entity.Member.SensitiveStatus;
import cosmetics.demo.Domain.Entity.Member.SkinStatus;
import cosmetics.demo.Domain.Repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Member;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional //변경
    public Long join(MemberEntity member) {
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }
    private void validateDuplicateMember(MemberEntity member) {
        List<MemberEntity> findMembers =
                memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
    /**
     * 전체 회원 조회
     */
    public List<MemberEntity> findMembers() {
        return memberRepository.findAll();
    }
    public MemberEntity findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
    public List<MemberEntity> findByMember(String memberName){
        return memberRepository.findByName(memberName);
    }
    /**
     * 회원 수정
     */
    @Transactional
    public void updateSkin(Long id, SkinStatus skin) {
        MemberEntity member = memberRepository.findOne(id);
        member.setSkin_status(skin);
    }
    @Transactional
    public void updateSensitive(Long id, SensitiveStatus sensitive) {
        MemberEntity member = memberRepository.findOne(id);
        member.setSensitive_status(sensitive);
    }
    @Transactional
    public void updateNickname(Long id, String nickname) {
        MemberEntity member = memberRepository.findOne(id);
        member.setNickname(nickname);
    }
    @Transactional
    public void updateSkinTrouble(Long id, boolean blackhead,boolean oily,boolean keratin,
                                  boolean pimple,boolean dry,boolean glow,boolean flexibility,
                                  boolean skintone,boolean wrinkle) {
        MemberEntity member = memberRepository.findOne(id);
        member.setBlackhead(blackhead);
        member.setOily(oily);
        member.setKeratin(keratin);
        member.setPimple(pimple);
        member.setDry(dry);
        member.setGlow(glow);
        member.setFlexibility(flexibility);
        member.setSkintone(skintone);
        member.setWrinkle(wrinkle);
    }
    @Transactional
    public void updatePersonal(Long id, String Sleeping_Hours,String Wash_Temperature,
                               String Wash_Num,String Stress,String Collyrium,String Food) {
        MemberEntity member = memberRepository.findOne(id);
        member.setSleeping_Hours(Sleeping_Hours);
        member.setWash_Temperature(Wash_Temperature);
        member.setWash_Num(Wash_Num);
        member.setStress(Stress);
        member.setCollyrium(Collyrium);
        member.setFood(Food);
    }
    // 로그인정보로 member.getid 해서 skin,민감성 넣어줌, 테스트필요
    public void setSkin(Long memberId,Long skinCnt){
        if (skinCnt<=1) {
            memberRepository.findOne(memberId).setSkin_status(SkinStatus.akkun);
        }
        else if(skinCnt<=4){
            memberRepository.findOne(memberId).setSkin_status(SkinStatus.kun);
        }
        else if(skinCnt<=7){
            memberRepository.findOne(memberId).setSkin_status(SkinStatus.joong);
        }
        else if(skinCnt<=10){
            memberRepository.findOne(memberId).setSkin_status(SkinStatus.ji);
        }
        else{
            memberRepository.findOne(memberId).setSkin_status(SkinStatus.akji);
        }
    }
    // 13번 접촉민 14번 화학민 13,14 극민감  둘다안체크는 non-sensitive
    public void setSensitive(Long memberId,Long sensitiveCnt){

    }
}