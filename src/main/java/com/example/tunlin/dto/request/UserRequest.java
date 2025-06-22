package com.example.tunlin.dto.request;


import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {

    String userName;
    @Size(min = 8, message = "PASSWORD_VALIDATE")
    String password;
    String firstName;
    String lastName;
    LocalDate dob;
}
