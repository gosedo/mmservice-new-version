package com.mmsystem.property.security;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


public class TokenUtils {
    
    private static Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(SecurityConstants.JWT_SECRET), 
    SignatureAlgorithm.HS512.getJcaName());

    public static String generateJWTUserToken(String userName) {
        
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + SecurityConstants.EXPIRATION_TIME);

        String token = Jwts.builder()
                .setSubject(userName)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                 .signWith(hmacKey)
                
                .compact();
        return token;
    }

    public static String getUsernameFromJWTUserToken(String token) {
       
        Jws<Claims> claims = Jwts.parserBuilder()
        .setSigningKey(hmacKey)
        .build()
        .parseClaimsJws(token);
        
        

        return claims.getBody().getSubject();
    }
    
    public static Date getJWTTokenExpiration(String token) {
       
        Jws<Claims> claims = Jwts.parserBuilder()
        .setSigningKey(hmacKey)
        .build()
        .parseClaimsJws(token);
         

        return claims.getBody().getExpiration();
    }
    
    
    public static boolean isJWTTokenValid(String token) {
        try {
            
            Jwts.parserBuilder().setSigningKey(hmacKey).build().parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            throw new AuthenticationCredentialsNotFoundException("JWT was expired or incorrect");
        }
    }

    public static String[] decodedBase64(String token) {

        byte[] decodedBytes = Base64.getDecoder().decode(token);
        String pairedCredentials = new String(decodedBytes);
        String[] credentials = pairedCredentials.split(":", 2);

        return credentials;

    }

}

