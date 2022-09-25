package edu.miu.bloguser.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VerifyDto {
    private String firstName;
    private String lastName;
    private String email;
}
