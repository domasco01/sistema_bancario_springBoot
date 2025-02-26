package it.micegroup.sistema_bancario.dto;

import java.time.LocalDate;
import java.util.Collection;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter @Setter
@EqualsAndHashCode
public class EditClienteDTO {
	
	@NonNull
	private String nomeCliente;
	
	@NonNull
	private String cognomeCliente;
	
	@NonNull
	private LocalDate dataNascita;
	
	private Collection<EditContoClienteDTO> contoClienti;
}
