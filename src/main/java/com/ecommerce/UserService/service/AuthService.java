package com.ecommerce.UserService.service;

import com.ecommerce.UserService.DTO.UserDto;
import com.ecommerce.UserService.Exceptions.UserNotFound;
import com.ecommerce.UserService.Repository.AuthRepository;
import com.ecommerce.UserService.Repository.SessionRepository;
import com.ecommerce.UserService.Repository.UserRepository;
import com.ecommerce.UserService.model.Session;
import com.ecommerce.UserService.model.SessionStatus;
import com.ecommerce.UserService.model.User;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;

import javax.crypto.SecretKey;
import java.util.HashMap;
import java.util.Optional;

@Service
public class AuthService {
    private UserRepository userRepository;
    private AuthRepository authRepository;
    private SessionRepository sessionRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AuthService(AuthRepository authRepository,
                       UserRepository userRepository,
                       SessionRepository sessionRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder){
        this.authRepository = authRepository;
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    public ResponseEntity<UserDto> login(String email, String password) throws UserNotFound {
       Optional<User> userOptional =  userRepository.findByEmail(email);
       if (userOptional.isEmpty()) {
           throw new UserNotFound("user not found");
       }

       User user = userOptional.get();
       if(!bCryptPasswordEncoder.matches(password,user.getPassword())){
           throw new UserNotFound("password not matched");
       }
       //String token = RandomStringUtils.randomAlphanumeric(10);
        SecretKey secretKey = Jwts.SIG.HS256.key().build();

       Session session = new Session();
       session.setUser(user);
       session.setToken(token);
       session.setSessionStatus(SessionStatus.Active);
       sessionRepository.save(session);


       UserDto userDto = new UserDto();
       userDto.setEmail(user.getEmail());

        MultiValueMap<String,String> headers = new MultiValueMapAdapter<>(new HashMap<>());
        headers.add(HttpHeaders.SET_COOKIE,"auth-token"+token);

        return new ResponseEntity<UserDto>(userDto,headers, HttpStatus.OK);
    }

    public SessionStatus logoutTheUser(String token) throws UserNotFound {
        Optional<Session> byToken = sessionRepository.findByToken(token);
        //System.out.println(byToken.get().getSessionStatus());
        if(byToken.isEmpty()){
            throw new UserNotFound("no session exist");
        }
        Session session = byToken.get();
        session.setSessionStatus(SessionStatus.Ended);
        sessionRepository.save(session);
        return SessionStatus.Ended;
    }

    public SessionStatus validation(String token) {
        Optional<Session>  sessionOptional= sessionRepository.findByToken(token);
        if(sessionOptional.isEmpty()){
            return SessionStatus.Ended;
        }
        Session session = sessionOptional.get();
        if(session.getSessionStatus().equals(SessionStatus.Ended)){
            return SessionStatus.Ended;
        }
        return SessionStatus.Active;
    }
}
