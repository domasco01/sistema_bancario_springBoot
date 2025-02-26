package it.micegroup.sistema_bancario.dto;

import java.time.LocalDate;
import java.util.List;

import it.micegroup.sistema_bancario.domain.ContoCliente;
import it.micegroup.sistema_bancario.domain.Movimento;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor  @ToString @EqualsAndHashCode

public class ClienteDTO {

	private String nomeCliente;
	private String cognomeCliente;
	private LocalDate dataNascita;
	private List<ContoCliente> conti;
	private List<Movimento> movimenti;
}
