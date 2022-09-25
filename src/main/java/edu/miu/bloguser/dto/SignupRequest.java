package edu.miu.bloguser.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
public class SignupRequest {

        @NotBlank
        private String firstName;
        @NotBlank
        private String lastName;

        @Email
        @NotBlank
        private String email;

        @NotBlank
        private String password;

        private Date dateOfBirth;

        private LocalDate createdAt;

        @NotBlank
        private String street;
        @NotBlank
        private String zipCode;
        @NotBlank
        private String state;
        @NotBlank
        private String country;
}
