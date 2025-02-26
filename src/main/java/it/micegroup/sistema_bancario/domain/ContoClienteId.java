package it.micegroup.sistema_bancario.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Embeddable

@Getter @Setter @ToString @EqualsAndHashCode @AllArgsConstructor @NoArgsConstructor

public class ContoClienteId implements Serializable {
	
	@Column(name="id_cliente")
	private Integer idCliente;
	
	@Column(name="id_conto")
	private Integer idConto;
}
