package edu.miu.bloguser.service;

import edu.miu.bloguser.dto.*;

import java.net.http.HttpRequest;

public interface IUserService {
    UserDto saveUser(SignupRequest userSaveDto);
    LoginResponse loginUser(LoginRequest loginRequest);
     VerifyDto verifyToken();
}
