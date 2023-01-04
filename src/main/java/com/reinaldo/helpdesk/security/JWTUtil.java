package com.reinaldo.helpdesk.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class JWTUtil {

    @Value("${jwt.expiration}")
    private Long expration;

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(String email){

        return Jwts.builder().setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis()+expration))
                .signWith(SignatureAlgorithm.ES512, secret.getBytes())
                .compact();

    }
}
