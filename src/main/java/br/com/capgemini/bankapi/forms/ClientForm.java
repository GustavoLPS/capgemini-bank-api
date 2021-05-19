package br.com.capgemini.bankapi.forms;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import br.com.capgemini.bankapi.models.Client;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientForm {
	@NotBlank(message = "O campo Nome não pode estar vazio")
	@Length(min = 5, max = 255, message = "O campo nome necessita de no mínimo 5 caracteres")
	private String fullName;
	
	@Column(nullable = false, length = 15)
	private String cpf;

	@Column(nullable = false, length = 10)
	private String rg;
	
	@NotBlank(message = "O campo Email não pode estar vazio")
	@Email
	private String email;
	
	public Client converter() {
		return new Client(fullName, cpf, rg, email);
	}
}
