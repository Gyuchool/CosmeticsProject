package cosmetics.demo.Controller;


import cosmetics.demo.Domain.Repository.MemberRepository;
import cosmetics.demo.Service.MemberService;
import cosmetics.demo.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.lang.reflect.Member;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String MemberForm(){
        return "members/join";
    }

    @PostMapping("/members/new")
    public String MemberJoin(MemberDto memberDto){
        memberService.join(memberDto);
        return "redirect:/";
    }

    @GetMapping("/members/login")
    public String MemberLoginForm(){
        return "members/login";
    }

    @PostMapping("/members/login")
    public String MemberLogin(MemberDto memberDto){
        int login = memberService.login(memberDto);
        if(login == 1) {
            return "redirect:/list";
        }
        else{
            return "redirect:/";
        }
    }
}
