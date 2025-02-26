package it.micegroup.sistema_bancario.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.micegroup.sistema_bancario.domain.Movimento;

@Repository
public interface MovimentoRepository extends JpaRepository<Movimento, Integer>{
	
	@Query("SELECT m FROM Movimento m WHERE m.conto.id = :idConto " + "AND FUNCTION('FORMATDATETIME', m.dataMovimento, 'yyyy-MM-dd') " + "BETWEEN :dataInizio AND :dataFine")
	List<Movimento> estrattoConto(@Param("idConto") Integer idConto, @Param("dataInizio") LocalDate dataInizio, @Param("dataFine") LocalDate dataFine );
}
