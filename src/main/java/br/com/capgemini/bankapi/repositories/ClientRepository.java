package br.com.capgemini.bankapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.capgemini.bankapi.models.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
	
}
