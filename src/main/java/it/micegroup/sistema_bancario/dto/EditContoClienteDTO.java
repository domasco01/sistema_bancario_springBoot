package it.micegroup.sistema_bancario.dto;

import it.micegroup.sistema_bancario.domain.Cliente;
import it.micegroup.sistema_bancario.domain.Conto;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter @Setter
public class EditContoClienteDTO {

	@NonNull
	Cliente cliente;
	//private Integer clienteId;
	
	@NonNull 
	Conto conto;
	//private Integer contoId;
	
	public EditContoClienteDTO(Cliente cliente, Conto conto) {
		this.cliente = cliente;
		this.conto = conto;
	}
}
