package it.micegroup.sistema_bancario.dto;

import java.math.BigDecimal;

import io.micrometer.common.lang.NonNull;
import it.micegroup.sistema_bancario.domain.TipoMovimento;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @EqualsAndHashCode
public class EditMovimentoDTO {
	
	@NonNull
	private BigDecimal importo;
	
	@NonNull
	private TipoMovimento tipoMovimento;
	
	@NonNull
	private Integer idCliente;
	
	@NonNull
	private Integer idConto;

}
