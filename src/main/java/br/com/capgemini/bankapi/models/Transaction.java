package br.com.capgemini.bankapi.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "transactions")
@NoArgsConstructor
@Getter
@Setter
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private Double valor;
	
	@ManyToOne
	@JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
	private Account account;
	
	@ManyToOne
	@JoinColumn(name = "transaction_type_id", referencedColumnName = "id", nullable = false)
	private TransactionType transactionType;
	
	@CreationTimestamp
	private LocalDateTime created_at;
	
	@UpdateTimestamp
	private LocalDateTime updated_at;
	
	public Transaction(Double valor, Account account) {
		this.valor = valor;
		this.account = account;
	}
}
