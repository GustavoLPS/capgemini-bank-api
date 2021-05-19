package br.com.capgemini.bankapi.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.capgemini.bankapi.models.Account;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	@Value("${capgemini-bank-web.jwt.expiration}")
	private String expiration;
	
	@Value("${capgemini-bank-web.jwt.secret}")
	private String secret;
	
	public String generateToken(Authentication authenticate) {
		Account account = (Account) authenticate.getPrincipal();
		Date now = new Date();
		Date expirationDate = new Date(now.getTime() + Long.parseLong(expiration));
		return Jwts.builder().setIssuer("Capgemini Bank API").setSubject(Long.toString(account.getId())).setIssuedAt(now)
				.claim("client", account)
				.setExpiration(expirationDate).signWith(SignatureAlgorithm.HS256, secret).compact();
	}
	
	public boolean isTokenValid(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public Long getAccountId(String token) {
		Claims body = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return Long.parseLong(body.getSubject());
	}
}
