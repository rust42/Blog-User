package edu.miu.bloguser.security;

import edu.miu.bloguser.entity.User;
import edu.miu.bloguser.exception.UserNotFoundException;
import edu.miu.bloguser.repository.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class BlogUserDetailService implements UserDetailsService {

    @Resource
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepo.findByEmail(username);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("User not found with email: " + username);
        }

        User user = optionalUser.get();
        return new BlogUserDetail(user);
    }
}
