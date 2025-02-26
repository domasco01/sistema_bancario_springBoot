package it.micegroup.sistema_bancario.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @EqualsAndHashCode
public class ViewEstrattoContoDTO {

	private List<ViewMovimentoDTO> movimenti;
	private BigDecimal saldoAttuale;
	
	public ViewEstrattoContoDTO(List<ViewMovimentoDTO> movimenti, BigDecimal saldoAttuale) {
		this.movimenti = movimenti;
		this.saldoAttuale = saldoAttuale;
	}
}
