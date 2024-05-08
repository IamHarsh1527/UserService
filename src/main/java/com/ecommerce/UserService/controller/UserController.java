package com.ecommerce.UserService.controller;

import com.ecommerce.UserService.DTO.SignupDto;
import com.ecommerce.UserService.DTO.UserDto;
import com.ecommerce.UserService.Exceptions.UserExists;
import com.ecommerce.UserService.model.User;
import com.ecommerce.UserService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private  UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserDetails(@PathVariable long id) {
        UserDto userDto=userService.getDetails(id);

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignupDto signupDto) throws UserExists {
        UserDto userDto = userService.signuptheUser(signupDto);
        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }



}
