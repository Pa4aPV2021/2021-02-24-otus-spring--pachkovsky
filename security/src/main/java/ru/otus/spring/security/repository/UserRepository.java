package ru.otus.spring.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sun.istack.NotNull;

import ru.otus.spring.security.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByName(@NotNull String name);

}
