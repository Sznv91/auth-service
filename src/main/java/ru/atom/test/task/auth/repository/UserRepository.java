package ru.atom.test.task.auth.repository;

import ru.atom.test.task.auth.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByNameOrPhoneOrEmail(String username, String phone, String email);

    Optional<UserEntity> findByPhone(String phone);
}
