package it.micegroup.sistema_bancario.dto;

import java.time.LocalDate;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter 
@EqualsAndHashCode

public class ViewClienteDTO {
	
	private Integer idCliente;
	private String nomeCliente;
	private String cognomeCliente;
	private LocalDate dataNascita;
	
	
}
