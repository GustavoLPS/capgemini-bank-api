package br.com.capgemini.bankapi.seed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.capgemini.bankapi.models.Agency;
import br.com.capgemini.bankapi.repositories.AgencyRepository;

@Component
public class DatabaseSeeder implements CommandLineRunner {
	@Autowired
	AgencyRepository agencyRepository;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		generateFakers();
	}
	
	private void generateFakers() {
		createAgency();
	}
	
	private void createAgency() {
		if (!agencyRepository.existsByName("djalma_batista")) {			
			Agency a = new Agency();
			a.setName("djalma_batista");
			a.setDescription("Djalma Batista");
			agencyRepository.save(a);
		}
	}
}
