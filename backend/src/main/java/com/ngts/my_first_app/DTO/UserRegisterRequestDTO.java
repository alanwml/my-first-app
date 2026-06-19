package com.ngts.my_first_app.DTO;

import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterRequestDTO {

    // Name must be Not Blank.
    // No Nulls, Not Empty, At least 1 char.
    @NotBlank(message = "Name cannot be blank.")
    private String name;

    // Password must be Not Blank.
    @NotBlank(message = "Password cannot be blank.")
    private String password;

    // Age Must be More than 4 & Less than or Equals to 100.
    @Max(value = 100, message = "Ain't no way you still alive unc. 💀")
    @Min(value = 4, message = "You're too young twin. :|")
    private int age;

    // Email Must not be Blank & Valid Email.
    @NotBlank(message = "Email cannot be blank.")
    @Email(message = "Email should be valid.")
    private String email;

    // registrationDate will be auto-created.
    @CreationTimestamp
    private LocalDateTime registrationDate;
}
