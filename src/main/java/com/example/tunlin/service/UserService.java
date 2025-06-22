package com.example.tunlin.service;

import com.example.tunlin.dto.request.UserRequest;
import com.example.tunlin.dto.response.UserResponse;
import com.example.tunlin.entity.User;
import com.example.tunlin.entity.enums.Role;
import com.example.tunlin.exception.AppException;
import com.example.tunlin.exception.ErrorCode;
import com.example.tunlin.mapper.UserMapper;
import com.example.tunlin.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserService {

    UserRepository userRepository;

    UserMapper userMapper;

    PasswordEncoder passwordEncoder;

    public User createUser(UserRequest request){

        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Set<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
        user.setRoles(roles);
        return userRepository.save(user);

    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public UserResponse getById(String id) {
        User user = userRepository.findById(id).orElseThrow(()->
                new AppException(ErrorCode.USER_NOT_FOUND));
        return userMapper.toUserResponse(user);
    }

    public User updateUser(String id, UserRequest request) {
        User user = userRepository.findById(id).orElseThrow(()->
                new AppException(ErrorCode.USER_NOT_FOUND));
        user.setUserName(request.getUserName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());
        return userRepository.save(user);
    }

}
