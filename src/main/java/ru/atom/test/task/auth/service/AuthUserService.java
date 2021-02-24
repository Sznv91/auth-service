package ru.atom.test.task.auth.service;

import ru.atom.test.task.auth.model.UserTo;
import ru.atom.test.task.auth.model.entity.UserEntity;
import ru.atom.test.task.auth.repository.UserRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.atom.test.task.auth.util.UserFieldChecker;
import ru.atom.test.task.auth.util.UserFieldException;

import java.util.Optional;

@Service
public class AuthUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    UserFieldChecker checker;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserEntity register(UserTo userTo) {
        checker.checkFields(userTo);
        UserEntity userEntity = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).convertValue(userTo, UserEntity.class);
        userEntity.setPassword(passwordEncoder.encode(userTo.getPassword()));
        Optional<UserEntity> optUser = userRepository.findByNameOrPhoneOrEmail(userTo.getPhone(), userTo.getPhone(), userTo.getEmail());
        if (!optUser.isPresent()) {
            userRepository.save(userEntity);
            userEntity.setPassword(userTo.getPassword());
            return userEntity;
        }
        throw new RuntimeException("User already exist");
//        throw UserFieldException.get("User already exist");
    }
}
