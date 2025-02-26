package it.micegroup.sistema_bancario.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Getter @Setter @NoArgsConstructor   @EqualsAndHashCode

public class Conto {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idConto;
	private BigDecimal saldoAttuale;
	private LocalDateTime dataApertura;
	
	@OneToMany(mappedBy = "conto", cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<ContoCliente> clienti = new ArrayList<>();
	

	public Conto(Integer idConto, LocalDateTime dataApertura, BigDecimal saldoAttuale) {
		this.idConto = idConto;
		this.dataApertura = dataApertura;
		this.saldoAttuale = saldoAttuale;
		this.clienti = new ArrayList<>();
	}
	
	
	@Override
	public String toString() {
		return "Conto [idConto=" + idConto + ", saldoAttuale=" + saldoAttuale + ", dataApertura=" + dataApertura + "]";
	}

}
