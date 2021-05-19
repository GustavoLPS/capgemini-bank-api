package br.com.capgemini.bankapi.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.capgemini.bankapi.models.Account;
import br.com.capgemini.bankapi.repositories.AccountRepository;
import br.com.capgemini.bankapi.services.TokenService;

public class AuthenticationTokenFilter extends OncePerRequestFilter {
	TokenService tokenService;
	AccountRepository accountRepository;
	
	public AuthenticationTokenFilter(TokenService tokenService, AccountRepository accountRepository) {
		this.tokenService = tokenService;
		this.accountRepository = accountRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = recoverToken(request);
		boolean valid = tokenService.isTokenValid(token);
		if (valid) {
			authenticationUser(token);
		}
		filterChain.doFilter(request, response);
	}
	
	private void  authenticationUser(String token) {
		Long id = tokenService.getAccountId(token);
		Account account = accountRepository.findById(id).get();
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(account, null,
			account.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
	
	private String recoverToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		
		return token.substring(7, token.length());
	}
}
