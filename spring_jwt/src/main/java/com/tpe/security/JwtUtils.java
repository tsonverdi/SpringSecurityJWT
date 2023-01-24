package com.tpe.security;

import com.tpe.security.service.UserDetailsImpl;
import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    //JWT produce/-Secret key lazim
    //JWT validation
    //JWT username bilgisini cek


    private String jwtSecret = "sboot";

    private Long jwtExprirationMs = 86400000L; //Bir gun expiration time verdik

    //*************GENERATE JWTOKEN***************

    public String generateToken(Authentication authentication){
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder().
                setSubject(userDetails.getUsername()).
                setIssuedAt(new Date()).
                setExpiration(new Date(new Date().getTime() + jwtExprirationMs)).
                signWith(SignatureAlgorithm.HS512,jwtSecret).
                compact();
    }

    //********************VALIDATION OF TOKEN*********************
    public boolean validateToken(String token){

        try {
            Jwts.
                    parser().
                    setSigningKey(jwtSecret).
                    parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
        } catch (UnsupportedJwtException e) {
            e.printStackTrace();
        } catch (MalformedJwtException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return false;
    }

    //*****************JWT tokenden Username Almak*************
    public String getUserNameFromJwtToken(String token){
        return Jwts.
                parser().
                setSigningKey(jwtSecret).
                parseClaimsJws(token).
                getBody().
                getSubject();
    }
}
