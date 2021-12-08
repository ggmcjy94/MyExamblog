package myproject.blog.domain.member;

import lombok.RequiredArgsConstructor;
import myproject.blog.controller.web.form.SignUpForm;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


    public void saveMember(SignUpForm signUpForm) {
        Member member = new Member(signUpForm);
        memberRepository.save(member);
    }

    public Member login(String loginId, String password) {
        Member member = memberRepository.findMember(loginId, password);
        return member;
    }

}
