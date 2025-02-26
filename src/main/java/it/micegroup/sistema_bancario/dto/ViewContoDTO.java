package it.micegroup.sistema_bancario.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@EqualsAndHashCode
public class ViewContoDTO {
	
	private Integer idConto;
	private double saldoAttuale;
	private LocalDateTime dataApertura;
	
	private List<ViewContoClienteDTO> clienti;
	
}
