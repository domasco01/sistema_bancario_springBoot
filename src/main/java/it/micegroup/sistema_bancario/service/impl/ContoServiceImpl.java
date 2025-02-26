package it.micegroup.sistema_bancario.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import it.micegroup.sistema_bancario.domain.Cliente;
import it.micegroup.sistema_bancario.domain.Conto;
import it.micegroup.sistema_bancario.domain.ContoCliente;
import it.micegroup.sistema_bancario.domain.Movimento;
import it.micegroup.sistema_bancario.repository.ClienteRepository;
import it.micegroup.sistema_bancario.repository.ContoClienteRepository;
import it.micegroup.sistema_bancario.repository.ContoRepository;
import it.micegroup.sistema_bancario.repository.MovimentoRepository;
import it.micegroup.sistema_bancario.service.ContoService;
import it.micegroup.sistema_bancario.service.MovimentoService;
//import it.micegroup.sistema_bancario.service.MovimentoService;
import jakarta.transaction.Transactional;

@Service("ContoServiceImpl")
public class ContoServiceImpl implements ContoService {
	
	private ContoRepository contoRepository;
	private ClienteRepository clienteRepository;
	private ContoClienteRepository contoClienteRepository;
	private MovimentoRepository movimentoRepository;
	private MovimentoService movimentoService;
	
	public ContoServiceImpl(ContoRepository contoRepository, ClienteRepository clienteRepository, ContoClienteRepository contoClienteRepository, MovimentoRepository movimentoRepository, MovimentoService movimentoService) {
		this.contoRepository = contoRepository;
		this.clienteRepository = clienteRepository;
		this.contoClienteRepository = contoClienteRepository;
		this.movimentoRepository = movimentoRepository;
		this.movimentoService = movimentoService;
	}
	
	@Override
	@Transactional
	public Conto creaConto(List<Integer> intestatariIds) {
		
		Conto conto = new Conto();
		conto.setDataApertura(LocalDateTime.now());
		conto.setSaldoAttuale(BigDecimal.ZERO);
		
		//creo un set per rimuovere eventuali duplicati
		Set<Integer> idUnici = new HashSet<>(intestatariIds);
		
		for(Integer idIntestatario : idUnici) {
			
			Cliente cliente = clienteRepository.findById(idIntestatario)
					.orElseThrow(()-> new RuntimeException("Cliente con id: " + idIntestatario + " non trovato"));
			
			ContoCliente contoCliente = new ContoCliente();
			
			contoCliente.setConto(conto);
			contoCliente.setCliente(cliente);
			
			conto.getClienti().add(contoCliente);			
			
		}

		
		return contoRepository.save(conto);
	}
	
	@Override
	@Transactional
	public List<Conto> ottieniTuttiConti() {
		
		List<Conto> conti = contoRepository.findAll();
		
		return conti;
	}
	
	
	
	
	@Override
	@Transactional
	public Conto modificaConto(Integer idConto, List<Integer> intestatari) {
		
		Conto conto = contoRepository.findById(idConto)
			.orElseThrow(()-> new RuntimeException("Conto inesistente"));
		
		//Rimuovo i vecchi intestatari
		contoClienteRepository.deleteByConto(conto);
		
		//creo un set per rimuovere eventuali duplicati
	    Set<Integer> idUnici = new HashSet<>(intestatari);
		
		for(Integer idIntestatario : idUnici) {
			
			Cliente cliente = clienteRepository.findById(idIntestatario)
					.orElseThrow(()-> new RuntimeException("Cliente con id: " + idIntestatario + " non trovato"));
			
			ContoCliente contoCliente = new ContoCliente();
			
			contoCliente.setConto(conto);
			contoCliente.setCliente(cliente);
			
			conto.getClienti().add(contoCliente);			
		}
		
		return contoRepository.save(conto);
	}
	
	
	@Override
	public Conto ottieniContoDaId(Integer idConto) {
	     Conto conto = contoRepository.findById(idConto)
	        .orElseThrow(() -> new RuntimeException("Conto non trovato"));
	     
	     return conto;
	}
	
	
	

	@Override
	public List<Movimento> estrattoConto(Integer idConto, LocalDate dataInizio, LocalDate dataFine) {
		Conto conto = contoRepository.findById(idConto)
			.orElseThrow(()-> new RuntimeException("Conto non trovato"));
		
		List<Movimento> movimenti = movimentoRepository.estrattoConto(idConto, dataInizio, dataFine);

		
		return movimenti;
	}
	
	
	
	
	
	
	@Override
	@Transactional
	public Conto prelievo(Movimento movimento) {  
		
		Conto conto = verificaIntestatarioERecuperaConto(movimento.getConto().getIdConto(), movimento.getCliente().getIdCliente());

		
		if (conto.getSaldoAttuale().compareTo(movimento.getImporto()) < 0) {                          //il bigDecimal non amemette = quindi uso compareTo
	        throw new RuntimeException("Saldo insufficiente per il prelievo");		   //se saldoAttuale è minore di importo restituisce un valore negativo
	    }
		
		conto.setSaldoAttuale(conto.getSaldoAttuale().subtract(movimento.getImporto()));             //altrimenti eseguiamo il prelievo 
		
		contoRepository.save(conto);												  //aggiorno il conto
		
		movimentoService.creaMovimento(movimento);
		//registraMovimento(conto, idCliente, importo, TipoMovimento.PRELIEVO);
		return conto;
	}
	
	
	
	
	
	@Override
	@Transactional
	public Conto deposito(Movimento movimento) {
		
		Conto conto = verificaIntestatarioERecuperaConto(movimento.getConto().getIdConto(), movimento.getCliente().getIdCliente());

			
			conto.setSaldoAttuale(conto.getSaldoAttuale().add(movimento.getImporto()));
			
			contoRepository.save(conto);												  //aggiorno il conto
			
			movimentoService.creaMovimento(movimento);
			//registraMovimento(conto, idCliente, importo, TipoMovimento.DEPOSITO);
			return conto;
	}
	
	
	
	@Override
	@Transactional
	public void cancellaConto(Integer idConto) {
		contoRepository.findById(idConto)
		.orElseThrow(()-> new RuntimeException("Conto non trovato"));
		
		contoRepository.deleteById(idConto);
		
	}
	
	
	
	
	
	//Metodo usato sia in prelievo() che in deposito() ridurre codice ridondante
	//Metodo che verificca l'esistenza del conto, del cliente e se il cliente è intestatario
	private Conto verificaIntestatarioERecuperaConto(Integer idConto, Integer idCliente) {
		
		Conto conto = contoRepository.findById(idConto)
                .orElseThrow(() -> new RuntimeException("Conto inesistente"));
        
        clienteRepository.findById(idCliente)
                .orElseThrow(() -> new RuntimeException("Cliente inesistente"));
        
        boolean isIntestatario = contoClienteRepository.verificaIntestatarioConto(idConto, idCliente);
        if (!isIntestatario) {
            throw new RuntimeException("Questo cliente non possiede questo conto");
        }
        
        return conto;		
	}
	
	
	

}
