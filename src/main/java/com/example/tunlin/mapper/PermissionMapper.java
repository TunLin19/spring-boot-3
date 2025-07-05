package com.example.tunlin.mapper;

import com.example.tunlin.dto.request.PermissionRequest;
import com.example.tunlin.dto.response.PermissionResponse;
import com.example.tunlin.entity.Permission;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);

}
