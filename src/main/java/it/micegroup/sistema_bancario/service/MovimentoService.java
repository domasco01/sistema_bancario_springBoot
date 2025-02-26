package it.micegroup.sistema_bancario.service; 

import java.util.List;

import it.micegroup.sistema_bancario.domain.Movimento;

public interface MovimentoService {

	Movimento creaMovimento(Movimento movimento);
	
	Movimento ottieniMovimentoPerId(Integer idMovimento);
	
	List<Movimento> mostraTuttiMovimenti();
	
	Movimento modificaMovimento(Integer idMovimento, Movimento movimentoAggiornato);
	
	void cancellaMovimento(Integer idMovimento);
}
