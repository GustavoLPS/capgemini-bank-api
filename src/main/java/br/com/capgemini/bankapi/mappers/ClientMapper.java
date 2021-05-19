package br.com.capgemini.bankapi.mappers;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.capgemini.bankapi.dtos.ClientDto;
import br.com.capgemini.bankapi.models.Client;

@Mapper
public interface ClientMapper {
	ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);
	
	@InheritInverseConfiguration
	ClientDto entityToResponse(Client client);
	
	List<ClientDto> entityToResponse(List<Client> client);
	
	Client entityToResponse(ClientDto clientDto);
}
