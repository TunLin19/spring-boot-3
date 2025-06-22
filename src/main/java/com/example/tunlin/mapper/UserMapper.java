package com.example.tunlin.mapper;

import com.example.tunlin.dto.request.UserRequest;
import com.example.tunlin.dto.response.UserResponse;
import com.example.tunlin.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper{
    User toUser(UserRequest request);

//    @Mapping(source = "firstName",target = "lastName")
//    @Mapping(source = "lastName",target = "firstName")
// value firstName is lastName
//    @Mapping(target = "lastName", ignore = true)
// value lastName is null(no map)
    UserResponse toUserResponse(User user);
    void updateUser(@MappingTarget User user, UserRequest request);

}
