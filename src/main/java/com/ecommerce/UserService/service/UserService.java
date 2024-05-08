package com.ecommerce.UserService.service;

import com.ecommerce.UserService.DTO.SignupDto;
import com.ecommerce.UserService.DTO.UserDto;
import com.ecommerce.UserService.Exceptions.UserExists;
import com.ecommerce.UserService.Repository.UserRepository;
import com.ecommerce.UserService.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    public UserService(UserRepository userRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public UserDto getDetails(long id) {
       Optional<User> user =  userRepository.findById(id);
       if(user.isEmpty()){
           return null;
       }
        UserDto userDto = new UserDto();
        userDto.setId(user.get().getId());
        userDto.setEmail(user.get().getEmail());
        return userDto;
    }

    public UserDto signuptheUser(SignupDto signupDto) throws UserExists {
        Optional<User> userbyEmail = userRepository.findByEmail(signupDto.getEmail());
        if(userbyEmail.isPresent()){
            throw new UserExists("user already exists");
        }
        User user = new User();
        user.setEmail(signupDto.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(signupDto.getPassword()));

        User saved = userRepository.save(user);
        return UserDto.from(saved);

    }
}
