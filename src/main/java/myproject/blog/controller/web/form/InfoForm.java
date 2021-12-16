package myproject.blog.controller.web.form;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InfoForm {

    private Long id;
    private String username;
    private String password;
    private String name;
    private String phoneNumber;
    private String address;
}
