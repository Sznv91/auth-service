package ru.atom.test.task.auth.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.atom.test.task.auth.model.UserTo;
import ru.atom.test.task.auth.model.entity.UserEntity;
import ru.atom.test.task.auth.repository.UserRepository;
import ru.atom.test.task.auth.util.UserFieldChecker;

import java.util.Optional;

@Service
public class AuthUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    UserFieldChecker checker;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String register(UserTo userTo) {
        String checkFieldsResult = checker.checkFields(userTo);
        if (!checkFieldsResult.isEmpty()) {
            return checkFieldsResult;
        }
        UserEntity userEntity = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).convertValue(userTo, UserEntity.class);
        userEntity.setPassword(passwordEncoder.encode(userTo.getPassword()));
        Optional<UserEntity> optUser = userRepository.findByNameOrPhoneOrEmail(userTo.getPhone(), userTo.getPhone(), userTo.getEmail());
        if (!optUser.isPresent()) {
            userRepository.save(userEntity);
            return "success";
        } else {
            return "unsuccess: User already exist";
        }
    }
}
