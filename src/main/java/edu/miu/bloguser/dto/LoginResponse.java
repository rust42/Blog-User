package edu.miu.bloguser.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class LoginResponse {
    private long id;
    private String token;
    private String firstName;
    private String lastName;
    private String email;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateOfBirth;

    private String street;
    private String zipCode;
    private String state;
    private String country;
}
