package it.micegroup.sistema_bancario.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString @EqualsAndHashCode
public class Movimento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Integer idMovimento;
	
    @Enumerated(EnumType.STRING)
	private TipoMovimento tipoMovimento;
    
    private BigDecimal importo;
    private LocalDateTime dataMovimento;
    
    @ManyToOne
	@JoinColumn(name="id_cliente", nullable=false)
	
	private Cliente cliente;
    
    @ManyToOne
	@JoinColumn(name="id_conto", nullable=false)
	
	private Conto conto;

	
    
    
}
