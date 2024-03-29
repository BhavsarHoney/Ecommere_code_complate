package com.einfo.Project.Ecommerce.service;


import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.einfo.Project.Ecommerce.Model.User;
import com.einfo.Project.Ecommerce.repo.UserRepo;

import java.util.function.Function;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
//import org.springframework.stereotype.Service;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {
	
  @Autowired 
   UserRepo repo ;
	 public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
	 
	 public String extractUsername(String token) {
	        return extractClaim(token, Claims::getSubject);
	    }

	 public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
	        final Claims claims = extractAllClaims(token);
	        return claimsResolver.apply(claims);
	    }
	    
	  public Date extractExpiration(String token) {
	       return extractClaim(token, Claims::getExpiration);
	    }
	  private Claims extractAllClaims(String token) {
	        return Jwts
	                .parserBuilder()
	                .setSigningKey(getSignKey())
	                .build()
	                .parseClaimsJws(token)
	                .getBody();
	    }

	    private Boolean isTokenExpired(String token) {
	        return extractExpiration(token).before(new Date());
	    }

	    public Boolean validateToken(String token, UserDetails userDetails) {
	        final String username = extractUsername(token);
	        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	    }



	public String generateToken(String email){
        Map<String,Object> claims=new HashMap<>();
        return createToken(claims,email);
    }

	private String createToken(Map<String, Object> claims, String email) {
	        
		return Jwts.builder()
				 .setClaims(claims)
	                .setSubject(email)
	                .claim("authorities", populateAuthorities(email))
	                .setIssuedAt(new Date(System.currentTimeMillis()))
	                .setExpiration(new Date(System.currentTimeMillis()+1000*60*30))
	                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
	}


    private Key getSignKey() {
        byte[] keyBytes= Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    } 
    private String populateAuthorities(String  email) 
    { Optional<User> userInfo = repo.findByEmail(email);
    return userInfo.get().getRole(); }

	
}
