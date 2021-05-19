package br.com.capgemini.bankapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.capgemini.bankapi.models.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

}
