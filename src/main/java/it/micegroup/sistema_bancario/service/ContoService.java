package it.micegroup.sistema_bancario.service;

import java.time.LocalDate;
import java.util.List;

import it.micegroup.sistema_bancario.domain.Conto;
import it.micegroup.sistema_bancario.domain.Movimento;

public interface ContoService {
	
	Conto creaConto(List<Integer> intestatariIds);
	
	Conto modificaConto(Integer idConto, List<Integer> intestatariIds);
	
	Conto ottieniContoDaId(Integer idConto);
	
	List<Movimento> estrattoConto(Integer idConto, LocalDate dataInizio, LocalDate dataFine);
	
	Conto prelievo(Movimento movimento);
	
	Conto deposito(Movimento movimento);
	
	void cancellaConto(Integer idConto);
	
	List<Conto> ottieniTuttiConti();
}
