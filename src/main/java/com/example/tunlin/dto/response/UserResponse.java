package com.example.tunlin.dto.response;

import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String userName;
    String firstName;
    String lastName;
    LocalDate dob;
    Set<String> roles;
}
