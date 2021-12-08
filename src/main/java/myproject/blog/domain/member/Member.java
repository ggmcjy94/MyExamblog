package myproject.blog.domain.member;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import myproject.blog.controller.web.form.SignUpForm;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
    private String name;
    private String phoneNumber;
    private String address;


    public Member(SignUpForm signUpForm) {
        username = signUpForm.getUsername();
        password = signUpForm.getPassword();
        name = signUpForm.getName();
        phoneNumber = signUpForm.getPhoneNumber();
        address = signUpForm.getAddress();
    }

}