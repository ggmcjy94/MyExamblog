package myproject.blog.domain.member;

import lombok.RequiredArgsConstructor;
import myproject.blog.controller.web.form.InfoForm;
import myproject.blog.controller.web.form.SignUpForm;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void saveMember(SignUpForm signUpForm) {
        String passwordEncode = bCryptPasswordEncoder.encode(signUpForm.getPassword());
        Member member = new Member(
                signUpForm.getUsername(),
                passwordEncode,
                signUpForm.getName(),
                signUpForm.getPhoneNumber(),
                signUpForm.getAddress());
        memberRepository.save(member);
    }

    public void updateMember(InfoForm infoForm) {
        String passwordEncode = bCryptPasswordEncoder.encode(infoForm.getPassword());
        Member member = new Member(
                infoForm.getUsername(),
                passwordEncode,
                infoForm.getName(),
                infoForm.getPhoneNumber(),
                infoForm.getAddress()
        );
        memberRepository.save(member);
    }

    public Member login(String loginId, String password) {
        Member member = memberRepository.findMember(loginId, password);
        return member;
    }

}
