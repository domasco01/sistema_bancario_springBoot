package it.micegroup.sistema_bancario.dto;



import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @ToString @EqualsAndHashCode
public class ViewContoClienteDTO {
	
	private Integer clienteId;
	private String nomeCliente;
	private String cognomeCliente;
	private Integer contoId;

	
	
}
