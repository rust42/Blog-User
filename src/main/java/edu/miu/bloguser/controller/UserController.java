package edu.miu.bloguser.controller;

import edu.miu.bloguser.dto.*;
import edu.miu.bloguser.entity.User;
import edu.miu.bloguser.security.BlogUserDetail;
import edu.miu.bloguser.service.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Resource
    private IUserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> register(@Valid @RequestBody SignupRequest user) {
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @PostMapping("/login")
    ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest login) {
        return ResponseEntity.ok(userService.loginUser(login));
    }

    /*
    Verfies that the token header is correct
    The header `Authorization`: "Bearer <token>" must be passed in order to authorize this request
     */
    @PostMapping("/verify")
    ResponseEntity<VerifyDto> verifyToken(Authentication authentication) {
        return ResponseEntity.ok(userService.verifyToken());
    }

    @PostMapping("/validate")
    ResponseEntity<VerifyDto> verifyUsername(@RequestBody VerifyRequest verifyRequest) {
        return ResponseEntity.ok(userService.validateUsername(verifyRequest.getEmail()));
    }

}
