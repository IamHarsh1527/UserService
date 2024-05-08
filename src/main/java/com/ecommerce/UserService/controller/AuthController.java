package com.ecommerce.UserService.controller;

import com.ecommerce.UserService.DTO.LoginRequest;
import com.ecommerce.UserService.DTO.LogoutDto;
import com.ecommerce.UserService.DTO.UserDto;
import com.ecommerce.UserService.DTO.ValidateToken;
import com.ecommerce.UserService.Exceptions.UserNotFound;
import com.ecommerce.UserService.model.SessionStatus;
import com.ecommerce.UserService.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Auth")
public class AuthController {

    private AuthService authService;
    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> Login(@RequestBody LoginRequest loginRequest) throws UserNotFound {
        return authService.login(loginRequest.getEmail(),loginRequest.getPassword());
        //return null;
    }


    @PostMapping("/logout")
    public ResponseEntity<SessionStatus> Logout(@RequestBody LogoutDto logoutDto) throws UserNotFound {
        SessionStatus ss = authService.logoutTheUser(logoutDto.getToken())  ;
        return new ResponseEntity<>(ss,HttpStatus.OK);
    }

    @PostMapping("validate")
    public SessionStatus Validate(@RequestBody ValidateToken validateToken) {
        return authService.validation(validateToken.getToken());
    }

}
