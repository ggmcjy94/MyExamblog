package myproject.blog.controller.web.form;

import lombok.Data;
import myproject.blog.domain.member.Member;

@Data
public class SignUpForm {
    private String username;
    private String password;
    private String name;
    private String phoneNumber;
    private String address;


}
