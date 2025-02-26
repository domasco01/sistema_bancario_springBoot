package it.micegroup.sistema_bancario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.micegroup.sistema_bancario.domain.Conto;
import it.micegroup.sistema_bancario.domain.ContoCliente;
import it.micegroup.sistema_bancario.domain.ContoClienteId;
import jakarta.transaction.Transactional;

@Repository
public interface ContoClienteRepository extends JpaRepository<ContoCliente, ContoClienteId> {
	 @Query("SELECT COUNT(cc) > 0 FROM ContoCliente cc WHERE cc.conto.id = :idConto AND cc.cliente.id = :idCliente")
	    boolean verificaIntestatarioConto(@Param("idConto") Integer idConto, @Param("idCliente") Integer idCliente);
	 
	@Transactional
    @Modifying
    @Query("DELETE FROM ContoCliente cc WHERE cc.conto = :conto")
    void deleteByConto(@Param("conto") Conto conto);

}
