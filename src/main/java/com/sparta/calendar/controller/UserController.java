package com.sparta.calendar.controller;

import com.sparta.calendar.dto.SignUpRequestDto;
import com.sparta.calendar.dto.SignUpResponseDto;
import com.sparta.calendar.dto.UpdatePasswordRequestDto;
import com.sparta.calendar.dto.UserResponseDto;
import com.sparta.calendar.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signup(@RequestBody SignUpRequestDto requestDto, HttpServletRequest request ) {

     SignUpResponseDto signUpResponseDto = userService.signup(
             requestDto.getUsername(),
             requestDto.getPassword(),
             requestDto.getAge()
     );

        HttpSession session = request.getSession();
        session.setAttribute("user", requestDto.getUsername());

     return new ResponseEntity<>(signUpResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable("id") Long id) {
       UserResponseDto userResponseDto =  userService.findById(id);

       return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePassword(
            @PathVariable("id") Long id,
            @RequestBody UpdatePasswordRequestDto requestDto
    ) {

        userService.updatePassword(id, requestDto.getOldPassword(), requestDto.getNewPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
