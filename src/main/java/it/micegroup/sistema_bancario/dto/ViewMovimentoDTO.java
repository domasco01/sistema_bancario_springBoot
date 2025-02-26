package it.micegroup.sistema_bancario.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import it.micegroup.sistema_bancario.domain.TipoMovimento;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @EqualsAndHashCode
public class ViewMovimentoDTO {
	
	private BigDecimal importo;
	private TipoMovimento tipoMovimento;
	private Integer idCliente;
	private String nomeCliente;
	private String cognomeCliente;
	private Integer idConto;
	private LocalDateTime dataMovimento;
}
