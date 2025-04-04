package com.sparta.calendar.service;

import com.sparta.calendar.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByName(String name);
    default User findByUserByUsernameOrElseThrow(String username) {
        return findByName(username).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Does not exist username = " + username));

    }
    default User findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() ->
                new ResponseStatusException
                        (HttpStatus.NOT_FOUND,
                        "User not found id = " + id));
    }
}
