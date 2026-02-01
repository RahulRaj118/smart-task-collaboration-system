package in.scalive.service;


import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import in.scalive.model.UsersDet;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {

	
//	private String secretKey="hK8mYq2B3LzYzF8a9pQv6UeW9M1mZ0Qy8xFZtR0dYxA=";
	
	//Another Way
	private String secretKey="";
	
	public JWTService() {
		try {
			KeyGenerator keyGen= KeyGenerator.getInstance("HmacSHA256");
		
		SecretKey	sk= keyGen.generateKey();
		secretKey=	Base64.getEncoder().encodeToString(sk.getEncoded());
		}
		catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public String generateToken(String userName,UsersDet usersDet) {
		Map<String, Object> claim=new HashMap<>();
		
		claim.put("role", usersDet.getRole());
		claim.put("name", usersDet.getName());
		claim.put("id", usersDet.getId());
		
		return Jwts.builder()
				.claims()
				.add(claim)
				.subject(userName)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis()+1000*60*60))
				.and()
				.signWith(getKey())
				.compact();
	}
	private SecretKey getKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		return  Keys.hmacShaKeyFor(keyBytes);
	}

	public Integer extractUserID(String token) {
		Claims claims = Jwts.parser()
				.verifyWith(getKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
		return  ((Number) claims.get("id")).intValue();
	}
	
	public String extractUserName(String token) {
		
		Claims claims= Jwts.parser()
				.verifyWith(getKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
		return claims.getSubject();
	}
	
	public Date extractExpiration(String token) {
	    Claims claims = Jwts.parser()
	            .verifyWith(getKey())
	            .build()
	            .parseSignedClaims(token)
	            .getPayload();

	    return claims.getExpiration();
	}

	private boolean isTokenExpired(String token) {
	    return extractExpiration(token).before(new Date());
	}
	
	public boolean validateToken(String token, UserDetails userDetails) {

	    String usernameFromToken = extractUserName(token);

	    return usernameFromToken.equals(userDetails.getUsername())
	            && !isTokenExpired(token);
	}


	
}
