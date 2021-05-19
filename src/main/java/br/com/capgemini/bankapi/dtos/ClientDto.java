package br.com.capgemini.bankapi.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDto {
	private long id;
	private String fullName;
	private String cpf;
	private String rg;
	private String email;
}
