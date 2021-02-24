package ru.atom.test.task.auth.service;

import ru.atom.test.task.auth.model.entity.UserEntity;
import ru.atom.test.task.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) {
        Optional<UserEntity> optUser = userRepository.findByPhone(userName);
        if (optUser.isPresent()) {
            UserEntity user = optUser.get();
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("User"));
            return new User(user.getPhone(), user.getPassword(), authorities);
        }
        throw new RuntimeException("user not exist");
    }
}
