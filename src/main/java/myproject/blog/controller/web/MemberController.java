package myproject.blog.controller.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myproject.blog.config.auth.PrincipalDetail;
import myproject.blog.config.auth.PrincipalDetailService;
import myproject.blog.controller.web.form.InfoForm;
import myproject.blog.controller.web.form.LoginForm;
import myproject.blog.controller.web.form.SignUpForm;
import myproject.blog.domain.member.Member;
import myproject.blog.domain.member.MemberRepository;
import myproject.blog.domain.member.MemberService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.websocket.Session;

@Slf4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @GetMapping("/login")
    public String LoginForm(@ModelAttribute LoginForm loginForm) {
        return "members/login";
    }

    @GetMapping("/signup")
    public String addForm(@ModelAttribute SignUpForm signUpForm) {
        return "members/signup";
    }

    @PostMapping("/signup")
    public String CreateMember(@ModelAttribute SignUpForm signUpForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "members/signup";
        }
        log.info("test = {} ", signUpForm.getPassword());
        memberService.saveMember(signUpForm);
        return "redirect:/";
    }

    @GetMapping("/info")
    public String MemberInfo(Authentication authentication, Model model) {
        PrincipalDetail principal = (PrincipalDetail) authentication.getPrincipal();
        Member member = memberRepository.findByUsername(principal.getUsername());
        InfoForm infoForm = new InfoForm(
                member.getId(),
                member.getUsername(),
                member.getPassword(),
                member.getName(),
                member.getPhoneNumber(),
                member.getAddress()
                );
        model.addAttribute("infoForm", infoForm);
        System.out.println("infoForm.getUsername1() = " + infoForm.getUsername());
        return "members/info";
    }

    @PostMapping("/info")
    public String MemberInfoUpdate(@ModelAttribute InfoForm infoForm) {
        System.out.println("infoForm.getUsername2() = " + infoForm.getUsername());
        System.out.println("infoForm.getName() = " + infoForm.getName());
        System.out.println("infoForm.getAddress() = " + infoForm.getAddress());
        memberService.updateMember(infoForm);
        return "redirect:/";
    }
}
