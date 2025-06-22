package com.example.tunlin.controller;

import com.example.tunlin.dto.request.UserRequest;
import com.example.tunlin.dto.response.ApiResponse;
import com.example.tunlin.dto.response.UserResponse;
import com.example.tunlin.entity.User;
import com.example.tunlin.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserController {

    UserService userService;

    @PostMapping("/users")
    ApiResponse<User> createUser(@RequestBody @Valid UserRequest request){

        ApiResponse<User> userApiResponse = new ApiResponse<>();
        userApiResponse.setCode(200);
        userApiResponse.setResult(userService.createUser(request));
//        userApiResponse.setMessage("add user successfully");
       return userApiResponse;
    }
    @GetMapping("/users")
    List<User> getAllUser(){
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("User name",authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));

        return userService.getAllUser();
    }
    @GetMapping("/users/{id}")
    UserResponse getUserById(@PathVariable("id") String id) {
        return userService.getById(id);
    }

    @PutMapping("/users/{id}")
    User getUserById(@PathVariable("id") String id,
                     @RequestBody @Valid UserRequest request) {
        return userService.updateUser(id,request);
    }

}
