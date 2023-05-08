package com.humanresources.webservice.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Users getUserById(long id){
        return userRepository.getUserById(id);
    }

    public Users getUserByEmail(String mail){
        return userRepository.getUserByEmail(mail);
    }

    public void saveUser(Users user){
        user.setPass(this.passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

}
