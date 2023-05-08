package com.humanresources.webservice.auth;

import com.fasterxml.jackson.annotation.JsonView;
import com.humanresources.webservice.shared.CurrentUser;
import com.humanresources.webservice.shared.Views;
import com.humanresources.webservice.user.UserService;
import com.humanresources.webservice.user.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    UserService userService;


    @PostMapping("/api/1.0/auth")
    @JsonView(Views.Public.class)
    public ResponseEntity<?> handleAuth(@CurrentUser Users user) {
        return ResponseEntity.ok(user);
    }
}
