package com.sparta.calendar.service;

import com.sparta.calendar.dto.SignUpResponseDto;
import com.sparta.calendar.dto.UserResponseDto;
import com.sparta.calendar.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

//회원가입기능
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    public SignUpResponseDto signup(String username, String password, int age) {

       User user = new User(username, age, password);

      User savedUser = userRepository.save(user);
        return new SignUpResponseDto(savedUser.getId(), savedUser.getName(), savedUser.getAge());
    }

    public UserResponseDto findById(Long id) {

        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exists id : " + id);
        }

       User findUser =  optionalUser.get();

        return new UserResponseDto(findUser.getName(), findUser.getAge());
    }

    @Transactional
    public void updatePassword(Long id, String oldPassword, String newPassword) {

       User findUser = userRepository.findByIdOrElseThrow(id);

      if(!findUser.getPassword().equals(oldPassword)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "패스워드가 일치하지 않습니다.");
        }

      findUser.updatePassword(newPassword);

    }
}
