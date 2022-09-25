package edu.miu.bloguser.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class UserDto {
    private long id;
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
