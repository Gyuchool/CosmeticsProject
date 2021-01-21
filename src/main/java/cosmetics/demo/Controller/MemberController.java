package cosmetics.demo.Controller;


import cosmetics.demo.Domain.Repository.MemberRepository;
import cosmetics.demo.Service.MemberService;
import cosmetics.demo.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.authentication.jaas.SecurityContextLoginModule;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Member;
import java.security.Principal;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/")
    public String home(Principal principal) {
        if(principal != null) {
            return "redirect:/list";
        }
        return "home.html";
    }
    /**
     * 회원가입
     */
    @GetMapping("/members/new")
    public String MemberForm(){
        return "members/signup";
    }

    @PostMapping("/members/new")
    public String MemberJoin(MemberDto memberDto){
        memberDto.setAuth("ROLE_USER");
        memberService.join(memberDto);
        return "redirect:/";
    }

    /**
     * 로그인
     */
    @GetMapping("/members/login")
    public String MemberLoginForm(){
        return "members/login";
    }

    @PostMapping("/members/login")
    public String MemberLogin(HttpServletResponse response, HttpServletRequest request){
        return "redirect:/list";
    }

    /**
     * 로그아웃
     */
    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response){
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/";
    }
}
