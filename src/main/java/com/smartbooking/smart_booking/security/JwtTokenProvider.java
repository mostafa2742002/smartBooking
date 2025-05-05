package com.smartbooking.smart_booking.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.smartbooking.smart_booking.entity.User;

import java.util.Date;
import java.util.Set;
import javax.crypto.SecretKey;

@Component
public class JwtTokenProvider {

    private final SecretKey jwtSecret;
    private final long jwtExpirationMs;

    public JwtTokenProvider(@Value("${jwt.secret}") String secret,
                            @Value("${jwt.expiration}") long expiration) {
        this.jwtSecret = Keys.hmacShaKeyFor(secret.getBytes());
        this.jwtExpirationMs = expiration;
    }

    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("roles", user.getRoles())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(jwtSecret)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(jwtSecret).build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}


/**
 * JwtTokenProvider Class
 * 
 * This class is responsible for generating, parsing, and validating JSON Web Tokens (JWTs) 
 * for authentication and authorization purposes in the application.
 * 
 * Dependencies:
 * - Uses the JJWT library for JWT operations.
 * - Requires a secret key and expiration time, which are injected from the application properties.
 * 
 * Fields:
 * - jwtSecret (SecretKey): The secret key used to sign and verify JWTs. It is initialized using the `Keys.hmacShaKeyFor` method.
 * - jwtExpirationMs (long): The expiration time for JWTs in milliseconds.
 * 
 * Constructor:
 * - JwtTokenProvider(@Value("${jwt.secret}") String secret, @Value("${jwt.expiration}") long expiration):
 *   - Initializes the `jwtSecret` using the secret key from the application properties.
 *   - Sets the `jwtExpirationMs` using the expiration time from the application properties.
 * 
 * Methods:
 * 
 * 1. generateToken(User user):
 *    - Generates a JWT for the given user.
 *    - Sets the username as the subject and includes the user's roles as a claim.
 *    - Sets the issued date and expiration date for the token.
 *    - Signs the token using the `jwtSecret`.
 *    - Returns the compact JWT string.
 * 
 * 2. getUsernameFromToken(String token):
 *    - Parses the given JWT to extract the username (subject).
 *    - Uses the `jwtSecret` to validate the token signature.
 *    - Returns the username from the token's claims.
 * 
 * 3. validateToken(String token):
 *    - Validates the given JWT.
 *    - Parses the token using the `jwtSecret` to ensure it is valid and not tampered with.
 *    - Returns `true` if the token is valid, otherwise catches exceptions and returns `false`.
 * 
 * Exceptions:
 * - Catches `JwtException` and `IllegalArgumentException` in `validateToken` to handle invalid or malformed tokens.
 * 
 * Usage:
 * - This class is typically used in authentication filters or services to handle JWT-based authentication.
 * 
 * Notes:
 * - Ensure the secret key (`jwt.secret`) is sufficiently strong and kept secure.
 * - The expiration time (`jwt.expiration`) should be configured based on the application's security requirements.
 * - the difference bitween the subject and the claim is 
 */