package it.micegroup.sistema_bancario.domain;



import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor  @EqualsAndHashCode
public class ContoCliente {


	@EmbeddedId
	private ContoClienteId id = new ContoClienteId();
	
	@ManyToOne
	@MapsId("idCliente")
	@JoinColumn(name = "id_cliente", nullable = false)
	private Cliente cliente;
	
	@ManyToOne
	@MapsId("idConto")
	@JoinColumn(name = "id_conto", nullable = false)
	private Conto conto;
	
	public ContoCliente(Conto conto, Cliente cliente) {
		this.id = new ContoClienteId(cliente.getIdCliente(), conto.getIdConto());
		this.cliente = cliente;
		this.conto = conto;
	}
	
	@Override
	public String toString() {
		return "ContoCliente [id=" + id + ", cliente=" + cliente.getIdCliente() + ", conto=" + conto.getIdConto() + "]";
	}
	
	
}
