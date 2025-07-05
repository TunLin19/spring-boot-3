package com.example.tunlin.mapper;

import com.example.tunlin.dto.request.UserCreationRequest;
import com.example.tunlin.dto.request.UserUpdateRequest;
import com.example.tunlin.dto.response.UserResponse;
import com.example.tunlin.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    UserResponse toUserResponse(User user);

    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
