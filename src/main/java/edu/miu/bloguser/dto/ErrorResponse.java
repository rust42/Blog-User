package edu.miu.bloguser.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;


@Data
public class ErrorResponse {
    private int code;
    String status;
    String message;
    String description;

    public ErrorResponse(HttpStatus httpStatus, String message, String description) {
        this.code = httpStatus.value();
        this.status = httpStatus.toString();
        this.message = message;
        this.description = description;
    }
}
