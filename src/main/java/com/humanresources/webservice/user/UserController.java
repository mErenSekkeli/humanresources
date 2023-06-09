package com.humanresources.webservice.user;

import com.fasterxml.jackson.annotation.JsonView;
import com.humanresources.webservice.error.ApiError;
import com.humanresources.webservice.shared.GenericResponse;
import com.humanresources.webservice.shared.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/api/1.0/createUser")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createUser(@RequestBody Users user) {
        userService.saveUser(user);
        return ResponseEntity.ok(new GenericResponse("User Created Successfully!"));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleValidationException(MethodArgumentNotValidException exception) {
        ApiError error = new ApiError(400, "Validation Error", "/api/1.0/createWorker");
        Map<String, String> validationErrors = new HashMap<>();

        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        error.setValidationErrors(validationErrors);
        return error;
    }


    @PostMapping("/api/1.0/getUserByEmail")
    @JsonView(Views.Public.class)
    public Users getUserByEmail(@RequestParam String email) {
        return userService.getUserByEmail(email);
    }

}
