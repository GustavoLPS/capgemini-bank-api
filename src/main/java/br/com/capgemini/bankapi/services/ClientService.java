package br.com.capgemini.bankapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.capgemini.bankapi.models.Client;
import br.com.capgemini.bankapi.repositories.ClientRepository;

@Service
public class ClientService {
	@Autowired
	ClientRepository clientRepository;
	
	public Client createClient(Client client) {
		return clientRepository.save(client);
	}
	
	public Client findClient(Long id) {
		return clientRepository.getOne(id);
	}
	
	public Client updateClient(Long id, Client client) {
		Client c = clientRepository.getOne(id);
		c.setFullName(client.getFullName());
		c.setCpf(client.getCpf());
		c.setRg(client.getRg());
		c.setEmail(client.getEmail());
		
		return c;
	}
	
	public List<Client> findAll() {
		return clientRepository.findAll();
	}
}
