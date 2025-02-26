package it.micegroup.sistema_bancario.dto;

import java.time.LocalDateTime;
import java.util.List;

import it.micegroup.sistema_bancario.domain.ContoCliente;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor  @ToString @EqualsAndHashCode

public class ContoDTO {
	
	private Integer idConto;
	private double saldoAttuale;
	private LocalDateTime dataApertura;
	List<ContoCliente> clienti;
}
