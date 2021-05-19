package br.com.capgemini.bankapi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.capgemini.bankapi.models.Account;
import br.com.capgemini.bankapi.repositories.AccountRepository;

@Service
public class AuthenticationService implements UserDetailsService {

	@Autowired
	AccountRepository accountRepository;
	
	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		Optional<Account> accout = accountRepository.findById(Long.parseLong(id));
		if (accout.isPresent()) {
			return accout.get();
		}
		throw new UsernameNotFoundException("Dados inválidos");
	}
	
}
