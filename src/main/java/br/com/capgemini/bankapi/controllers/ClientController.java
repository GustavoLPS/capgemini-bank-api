package br.com.capgemini.bankapi.controllers;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.capgemini.bankapi.dtos.ClientDto;
import br.com.capgemini.bankapi.forms.ClientForm;
import br.com.capgemini.bankapi.mappers.ClientMapper;
import br.com.capgemini.bankapi.models.Client;
import br.com.capgemini.bankapi.services.ClientService;

@RestController
@RequestMapping(value = "/api/v1/clients")
@CrossOrigin(origins = "*")
public class ClientController {
	@Autowired
	ClientService clientService;
	
	@PostMapping
	public ResponseEntity<ClientDto> createClient(@RequestBody @Valid ClientForm clientForm, UriComponentsBuilder uriBuilder) {
		Client client = clientForm.converter();
		clientService.createClient(client);
		URI uri = uriBuilder.path("/api/v1/clients/{id}").buildAndExpand(client.getId()).toUri();
		return ResponseEntity.created(uri).body(ClientMapper.INSTANCE.entityToResponse(client));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ClientDto> findById(@PathVariable Long id) {
		return ResponseEntity.ok(ClientMapper.INSTANCE.entityToResponse(clientService.findClient(id)));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ClientDto> updateClient(@PathVariable Long id, @RequestBody ClientForm clientForm) {
		Client client = clientForm.converter();
		return ResponseEntity.ok(ClientMapper.INSTANCE.entityToResponse(clientService.updateClient(id, client)));
	}
	
	@GetMapping
	public List<ClientDto> findAll() {
		return ClientMapper.INSTANCE.entityToResponse(clientService.findAll());
	}
}
