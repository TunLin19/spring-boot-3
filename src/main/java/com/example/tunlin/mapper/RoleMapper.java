package com.example.tunlin.mapper;

import com.example.tunlin.dto.request.RoleRequest;
import com.example.tunlin.dto.response.RoleResponse;
import com.example.tunlin.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions",ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);

}
