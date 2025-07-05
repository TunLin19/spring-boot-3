package com.example.tunlin.controller;

import com.example.tunlin.dto.response.ApiResponse;
import com.example.tunlin.dto.request.PermissionRequest;
import com.example.tunlin.dto.response.PermissionResponse;
import com.example.tunlin.service.PermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/permissions")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)

public class PermissionController {

    PermissionService permissionService;

    @GetMapping
    ApiResponse<List<PermissionResponse>> getAll(){
        List<PermissionResponse> permissionResponses = permissionService.getAll();
        return ApiResponse.<List<PermissionResponse>>builder()
                .result(permissionResponses)
                .build();
    }

    @PostMapping
    ApiResponse<PermissionResponse> create(@RequestBody PermissionRequest request){

        return ApiResponse.<PermissionResponse>builder()
                .result(permissionService.create(request))
                .build();
    }

    @DeleteMapping("/{permission}")
    ApiResponse<Void> delete(@PathVariable String permission){
        permissionService.delete(permission);
        return ApiResponse.<Void>builder().build();
    }

}
