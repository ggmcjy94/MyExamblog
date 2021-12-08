package myproject.blog.controller.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myproject.blog.controller.web.form.LoginForm;
import myproject.blog.controller.web.form.SignUpForm;
import myproject.blog.domain.member.Member;
import myproject.blog.domain.member.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/login")
    public String LoginForm(@ModelAttribute LoginForm loginForm) {
        return "members/login";
    }

    @PostMapping("/login")
    public String Login(@ModelAttribute LoginForm loginForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "members/login";
        }
        Member member = memberService.login(loginForm.getUsername(), loginForm.getPassword());
        if (member != null) {
            return "redirect:/";
        }
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
}
