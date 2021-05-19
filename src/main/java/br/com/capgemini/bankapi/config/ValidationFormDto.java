package br.com.capgemini.bankapi.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ValidationFormDto {
	private String field;
	private String error;
}
