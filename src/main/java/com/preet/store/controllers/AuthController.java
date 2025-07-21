package com.preet.store.controllers;

import com.preet.store.dto.JwtResponse;
import com.preet.store.dto.UserDto;
import com.preet.store.dto.UserLoginDto;
import com.preet.store.entities.User;
import com.preet.store.services.JwtService;
import com.preet.store.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody UserLoginDto request, HttpServletResponse response){

        System.out.println("login endpoint hit");

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
        User user = userService.findUserByEmail(request.getEmail());

        String token = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        Cookie cookie = new Cookie("refreshToken", refreshToken);
        cookie.setHttpOnly(true);
        cookie.setPath("auth/refresh");
        cookie.setMaxAge(604800);
        cookie.setSecure(true);
        response.addCookie(cookie);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtResponse> refresh(@CookieValue(name = "refreshToken") String refreshToken)
    {
        if(!jwtService.validate(refreshToken))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        Long id = jwtService.getIdFromToken(refreshToken);
        User user = userService.getUserById(id);

        String token = jwtService.generateAccessToken(user);
        return ResponseEntity.ok(new JwtResponse(token));

    }



    @PostMapping("/validate")
    public Boolean validate(@RequestHeader("Authorization") String token)
    {
        return jwtService.validate(token.replace("Bearer ",""));
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> me()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long id = (Long) authentication.getPrincipal();
        User user = userService.getUserById(id);
        if(user == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new UserDto(user.getId(),user.getName(),user.getEmail()));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handle(BadCredentialsException exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exception.getMessage());
    }
}
