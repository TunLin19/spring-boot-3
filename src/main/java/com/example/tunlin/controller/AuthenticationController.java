package com.example.tunlin.controller;

import com.example.tunlin.dto.request.AuthenticationRequest;
import com.example.tunlin.dto.request.IntrospectRequest;
import com.example.tunlin.dto.response.ApiResponse;
import com.example.tunlin.dto.response.AuthenticationResponse;
import com.example.tunlin.dto.response.IntrospectResponse;
import com.example.tunlin.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AuthenticationController {

    AuthenticationService authenticationService;

    @PostMapping("/login")
    ApiResponse<AuthenticationResponse> auth(@RequestBody AuthenticationRequest request){
        AuthenticationResponse result = authenticationService.authentication(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> auth(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        var result = authenticationService.introspect(request);
        return ApiResponse.<IntrospectResponse>builder()
                .result(result)
                .build();
    }

}
