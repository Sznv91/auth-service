package ru.atom.test.task.auth.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;

@ToString
@Getter
@Setter
public class UserTo {

    private String name;

    private String phone;

    @Email
    private String email;

    private String password;

    private String confirmPassword;

    boolean rules;

    boolean subscribe;
}
