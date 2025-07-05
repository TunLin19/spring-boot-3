package com.example.tunlin.controller;

import com.example.tunlin.dto.response.ApiResponse;
import com.example.tunlin.dto.request.RoleRequest;
import com.example.tunlin.dto.response.RoleResponse;
import com.example.tunlin.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RoleController {

    RoleService roleService;

    @GetMapping
    ApiResponse<List<RoleResponse>> getAll(){
        List<RoleResponse> roleResponses = roleService.getAll();
        return ApiResponse.<List<RoleResponse>>builder()
                .result(roleResponses)
                .build();
    }

    @PostMapping
    ApiResponse<RoleResponse> create(@RequestBody RoleRequest request){

        return ApiResponse.<RoleResponse>builder()
                .result(roleService.create(request))
                .build();
    }

    @DeleteMapping("/{role}")
    ApiResponse<Void> delete(@PathVariable String role){
        roleService.delete(role);
        return ApiResponse.<Void>builder().build();
    }

}
