package cosmetics.demo.Controller;

import cosmetics.demo.Domain.Entity.Member.MemberEntity;
import cosmetics.demo.Domain.Entity.Member.SensitiveStatus;
import cosmetics.demo.Domain.Entity.Member.SkinStatus;
import cosmetics.demo.Service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @Data
    static class CreateMemberRequest {
        private String nickname;
        private String name;
        private String password;
    }
    @Data
    @AllArgsConstructor
    class CreateMemberResponse {
        private Long id;
    }
    //회원가입
    @PostMapping("/members/new")
    public CreateMemberResponse saveMember(@RequestBody @Validated CreateMemberRequest request) {
        MemberEntity member = new MemberEntity();
        member.setName(request.getName());
        member.setPassword(request.getPassword());
        member.setNickname(request.getNickname());
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }
    @Data
    static class UpdateMemberRequest {
        private String name;
        private String nickname;
        private String password;
    }
    @Data
    @AllArgsConstructor
    class UpdateMemberResponse {
        private Long id;
    }
    //피부타입 수정
    @PostMapping("/members/skin/{id}")
    public UpdateMemberResponse updateMemberSkin(@PathVariable("id") Long id,
                                                 @RequestBody @Validated UpdateMemberSkinRequest request) {
        memberService.updateSkin(id,request.getSkin());
        return new UpdateMemberResponse(id);
    }
    @Data
    static class UpdateMemberSkinRequest {
        private SkinStatus skin;
    }
    //피부민감 수정
    @PostMapping("/members/sensitive/{id}")
    public UpdateMemberResponse updateMemberSkin(@PathVariable("id") Long id,
                                                 @RequestBody @Validated UpdateMemberSensitiveRequest request) {
        memberService.updateSensitive(id,request.getSensitive());
        return new UpdateMemberResponse(id);
    }
    @Data
    static class UpdateMemberSensitiveRequest {
        private SensitiveStatus sensitive;
    }
    //닉네임 수정
    @PostMapping("/members/nickname/{id}")
    public UpdateMemberResponse updateMemberNickname(@PathVariable("id") Long id,
                                                     @RequestBody @Validated UpdateMemberNicknameRequest request) {
        memberService.updateNickname(id,request.getNickname());
        return new UpdateMemberResponse(id);
    }
    @Data
    static class UpdateMemberNicknameRequest {
        private String nickname;
    }
    //피부고민 수정
    @PostMapping("/members/skinTrouble/{id}")
    public UpdateMemberResponse updateMemberSkinTrouble(@PathVariable("id") Long id,
                                                        @RequestBody @Validated UpdateMemberSkinTroubleRequest request) {
        memberService.updateSkinTrouble(id,request.isBlackhead(),request.isOily(),request.isKeratin(),
                request.isPimple(),request.isDry(),request.isGlow(),request.isFlexibility(),
                request.isSkintone(),request.isWrinkle());
        return new UpdateMemberResponse(id);
    }
    @Data
    static class UpdateMemberSkinTroubleRequest {
        private boolean blackhead;
        private boolean oily;
        private boolean keratin;
        private boolean pimple;
        private boolean dry;
        private boolean glow;
        private boolean flexibility;
        private boolean skintone;
        private boolean wrinkle;
    }
    //개인특성 수정
    @PostMapping("/members/personal/{id}")
    public UpdateMemberResponse updateMemberPersonal(@PathVariable("id") Long id,
                                                     @RequestBody @Validated UpdateMemberPersonalRequest request) {
        memberService.updatePersonal(id,request.getSleeping_Hours(),request.getWash_Temperature(),
                request.getWash_Num(),request.getStress(),request.getCollyrium(),request.getFood());
        return new UpdateMemberResponse(id);
    }
    @Data
    static class UpdateMemberPersonalRequest {
        private String Sleeping_Hours;
        private String Wash_Temperature;
        private String Wash_Num;
        private String Stress;
        private String Collyrium;
        private String Food;
    }
    //로그인
    @PostMapping("/members/login")
    public LoginResponse login(@RequestBody @Validated LoginRequest request){
        List<MemberEntity> findMembers=memberService.findByMember(request.getName());
        if(findMembers.isEmpty()){
            return new LoginResponse("false",0L);
        }
        else{
            MemberEntity member=findMembers.stream().findAny().get();
            if(!member.getPassword().equals(request.getPassword())){ // 비번4자리부터 안됨;
                return new LoginResponse("false",-1L);
            }
            else{
                return new LoginResponse("true",member.getId());
            }
        }
    }
    @Data
    static class LoginRequest{
        private String name;
        private String password;
    }
    @Data
    @AllArgsConstructor
    class LoginResponse {
        private String state;
        private Long id;
    }
    /*
    @GetMapping("/api/v2/members")
    public MemberApiController.Result membersV2() {
        List<Member> findMembers = memberService.findMembers();
        //엔티티 -> DTO 변환
        List<MemberApiController.MemberDto> collect = findMembers.stream()
                .map(m -> new MemberApiController.MemberDto(m.getName()))
                .collect(Collectors.toList());
        return new MemberApiController.Result(collect);
    }
    @Data
    @AllArgsConstructor
    class Result<T> {
        private T data;
    }
    @Data
    @AllArgsConstructor
    class MemberDto {
        private String name;
    }
    */
}