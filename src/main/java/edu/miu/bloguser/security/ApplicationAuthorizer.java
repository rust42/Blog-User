package edu.miu.bloguser.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.miu.bloguser.dto.ErrorResponse;
import edu.miu.bloguser.util.JWTUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@Component
public class ApplicationAuthorizer extends OncePerRequestFilter {
    @Resource
    private JWTUtils jwtUtils;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String token = authorizationHeader.substring(7);
            String username = jwtUtils.parseToken(token);
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    new ArrayList<>()
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception exception) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED, exception.getMessage(), request.getServletPath());
            response.setStatus(HttpStatus.UNAUTHORIZED.value());

            ObjectMapper mapper = new ObjectMapper();
            PrintWriter out = response.getWriter();
            out.print(mapper.writeValueAsString(errorResponse));
            out.flush();
        }
    }
}


