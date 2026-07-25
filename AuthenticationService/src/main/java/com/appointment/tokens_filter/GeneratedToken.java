package com.appointment.tokens_filter;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.appointment.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
@Component
public class GeneratedToken {
	public class SecurityConstants {
	    public static final String SECRET_KEY =
	            "VGhpc0lzQVNlY3JldEtleUZvckpXVFRva2VuMTIzNDU2Nzg5";
	}

	public String generateToken(User user) {
		Map<String,Object>claims=new HashMap<>();
		 claims.put("role", user.getRole().name());
		return Jwts.builder()
				   .header().add("TYP","JWT")
				   .and()
				   .claim("role", user.getRole().name())
				    .subject(user.getUsername())
				   .issuedAt(new Date(System.currentTimeMillis()))
				   .expiration(new Date(System.currentTimeMillis()+1000*60*60*5))
				   .signWith(getKey(),SignatureAlgorithm.HS256)
				   .compact();
	}
	public Key getKey() {
		  byte[] decode=Base64.getDecoder().decode(SecurityConstants.SECRET_KEY);
		  return Keys.hmacShaKeyFor(decode);
	}
	private Claims getClaims(String token) {
		return Jwts.parser()
				   .verifyWith((SecretKey) getKey())
				   .build()
				   .parseSignedClaims(token)
				   .getPayload();
	}
	public void validateToken(String token) {
	        getClaims(token);
	}
	public String getUsername(String token) {
		Claims claims=getClaims(token);
		return claims.getSubject();
	}
	public boolean isTokenNotExpired(String token) {
		Claims claims=getClaims(token);
		 Date exDate=claims.getExpiration();
		 return exDate.after(new Date());
	}
	public boolean isToken(String token,UserDetails userDetails) {
		return userDetails.getUsername().equals(getUsername(token)) && isTokenNotExpired(token);
	}
}
