package br.com.capgemini.bankapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.capgemini.bankapi.models.Agency;

public interface AgencyRepository extends JpaRepository<Agency, Long>{
	boolean existsByName(String name);
}
