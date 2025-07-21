package com.preet.store.services;

import com.preet.store.entities.Role;
import com.preet.store.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    private final UserService userService;
    @Value("${spring.jwt.secret}")
    private String secret;

    public JwtService(UserService userService) {
        this.userService = userService;
    }

    public String generateToken(User user, int time)
    {

        return Jwts.builder()
                .subject(String.valueOf(user.getId()))
                .claim("username", user.getName())
                .claim("email",user.getEmail())
                .claim("role",user.getRole())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * time))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }

    public String generateAccessToken(User user)
    {
        return generateToken(user,300);
    }

    public String generateRefreshToken(User user)
    {
        return generateToken(user,86400);
    }
    public Boolean validate(String token)
    {
        try {
            Claims claims = getClaims(token);
            return claims.getExpiration().after(new Date());
        } catch (JwtException e) {
            return false;
        }
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public Long getIdFromToken(String token) {
        return Long.valueOf(getClaims(token).getSubject());
    }

    public Role getRole(String token) {
        return Role.valueOf(getClaims(token).get("role", String.class));
    }
}
