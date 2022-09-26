package edu.miu.bloguser.service;

import edu.miu.bloguser.dto.*;
import edu.miu.bloguser.entity.User;
import edu.miu.bloguser.exception.UserExistsException;
import edu.miu.bloguser.exception.UserNotFoundException;
import edu.miu.bloguser.repository.UserRepo;
import edu.miu.bloguser.security.ApplicationAuthorizer;
import edu.miu.bloguser.security.BlogUserDetail;
import edu.miu.bloguser.util.JWTUtils;
import eye2web.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.net.http.HttpRequest;
import java.util.Optional;

@Service
@Transactional(rollbackOn = {Exception.class})
public class UserService implements IUserService {

    @Autowired
    private ModelMapper modelMapper;

    @Resource
    private JWTUtils jwtUtils;
    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto saveUser(SignupRequest userSaveDto) {
        if (userRepo.findByEmail(userSaveDto.getEmail()).isPresent()) {
            System.out.println("Throwing user presernt exception");
            throw new UserExistsException("User with email address " + userSaveDto.getEmail() + " already exists.");
        }
        userSaveDto.setPassword(passwordEncoder.encode(userSaveDto.getPassword()));
        User user = modelMapper.map(userSaveDto, User.class);
        return modelMapper.map(userRepo.save(user), UserDto.class);
    }

    public LoginResponse loginUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                        loginRequest.getPassword())
        );

        BlogUserDetail userDetail = (BlogUserDetail)authentication.getPrincipal();
        User user = userDetail.getUser();

        String token = jwtUtils.generateToken(authentication);
        return LoginResponse.builder()
                .token(token)
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .dateOfBirth(user.getDateOfBirth())
                .street(user.getStreet())
                .state(user.getState())
                .zipCode(user.getState())
                .country(user.getCountry())
                .id(user.getId())
                .build();
    }

    public VerifyDto verifyToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = (String)authentication.getPrincipal();
        Optional<User> user = userRepo.findByEmail(email);
        if (user.isEmpty()) {
            throw new UserNotFoundException("No such user found in the database.");
        }
        return modelMapper.map(user.get(), VerifyDto.class);
    }

    public VerifyDto validateUsername(String username) {
        Optional<User> user = userRepo.findByEmail(username);
        if (user.isEmpty()) {
            throw new UserNotFoundException("No such user found in the database.");
        }
        return modelMapper.map(user.get(), VerifyDto.class);
    }
}
