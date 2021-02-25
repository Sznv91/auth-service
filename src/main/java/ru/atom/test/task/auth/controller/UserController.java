package ru.atom.test.task.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.atom.test.task.auth.model.UserTo;
import ru.atom.test.task.auth.service.AuthUserService;

@RestController
public class UserController {

    @Autowired
    private AuthUserService authUserService;

    @PostMapping("/oauth/user")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public String register(@RequestBody UserTo userTo) {
        return authUserService.register(userTo);
    }

}
