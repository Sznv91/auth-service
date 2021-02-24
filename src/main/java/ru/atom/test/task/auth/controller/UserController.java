package ru.atom.test.task.auth.controller;

import ru.atom.test.task.auth.model.UserTo;
import ru.atom.test.task.auth.model.entity.UserEntity;
import ru.atom.test.task.auth.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private AuthUserService authUserService;

    @PostMapping("/oauth/user")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public UserEntity register(@RequestBody UserTo userTo) {
        return authUserService.register(userTo);
    }

}
