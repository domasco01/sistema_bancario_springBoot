package it.micegroup.sistema_bancario.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter @Setter
@EqualsAndHashCode
public class EditContoDTO {

	@NonNull
	private BigDecimal saldoAttuale;
	
	@NonNull
	private LocalDateTime dataApertura;
	
	private Collection<EditContoClienteDTO> contoClienti; 
}
