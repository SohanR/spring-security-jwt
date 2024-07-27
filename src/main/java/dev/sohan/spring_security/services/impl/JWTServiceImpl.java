package dev.sohan.spring_security.services.impl;

import dev.sohan.spring_security.entities.User;
import dev.sohan.spring_security.services.JWTService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTServiceImpl implements JWTService {

    private  <T> T extractClaim(String token, Function<Claims, T> claimResolvers){
        final Claims claims = extractAllClaims(token);
        return claimResolvers.apply(claims);
    }

    // getting the signing key
    private Key getSigninKey() {
        byte[] key = Decoders.BASE64.decode("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        return Keys.hmacShaKeyFor(key);
    }

    // extracting claims here
    private  Claims extractAllClaims(String token){
        return Jwts.parser().setSigningKey(getSigninKey()).build().parseClaimsJws(token).getBody();
    }


    @Override
    public String generateToken(String username) {
        return Jwts.builder().setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 604800000))
                .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                .compact();

    }


    @Override
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // check token validity
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUserName(token);
         return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    // check token expiration
    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }
}