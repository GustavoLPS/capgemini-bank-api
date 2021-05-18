package br.com.capgemini.bankapi.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "clients")
@NoArgsConstructor
@Getter
@Setter
public class Client {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	private String fullName;
	
	@Column(nullable = false)
	private String cpf;

	@Column(nullable = false)
	private String rg;
	
	private String email;
	
	@Column(nullable = false, length = 255)
	private int password;
	
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	public Client(String fullName, String cpf, String rg, String email, int password) {
		this.fullName = fullName;
		this.cpf = cpf;
		this.rg = rg;
		this.email = email;
		this.password = password;
	}
}
