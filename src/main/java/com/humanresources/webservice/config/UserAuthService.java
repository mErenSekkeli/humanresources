package com.humanresources.webservice.config;


import com.humanresources.webservice.user.UserRepository;
import com.humanresources.webservice.user.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserAuthService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users inDB = userRepository.getUserByEmail(email);
        if(inDB == null)
            throw new UsernameNotFoundException("User Has Not Found");
        return inDB;
    }

}
