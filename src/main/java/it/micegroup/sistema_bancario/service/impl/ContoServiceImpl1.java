package it.micegroup.sistema_bancario.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import it.micegroup.sistema_bancario.domain.Cliente;
import it.micegroup.sistema_bancario.domain.TipoMovimento;
import it.micegroup.sistema_bancario.domain.Conto;
import it.micegroup.sistema_bancario.domain.ContoCliente;
import it.micegroup.sistema_bancario.domain.Movimento;
import it.micegroup.sistema_bancario.repository.ClienteRepository;
import it.micegroup.sistema_bancario.repository.ContoClienteRepository;
import it.micegroup.sistema_bancario.repository.ContoRepository;
import it.micegroup.sistema_bancario.repository.MovimentoRepository;
import it.micegroup.sistema_bancario.service.ContoService;

@Service("ContoServiceImpl1")
public class ContoServiceImpl1 implements ContoService {
	
	private ContoRepository contoRepository;
	private ClienteRepository clienteRepository;
	private ContoClienteRepository contoClienteRepository;
	private MovimentoRepository movimentoRepository;
	
	public ContoServiceImpl1(ContoRepository contoRepository, ClienteRepository clienteRepository, ContoClienteRepository contoClienteRepository, MovimentoRepository movimentoRepository) {
		this.contoRepository = contoRepository;
		this.clienteRepository = clienteRepository;
		this.contoClienteRepository = contoClienteRepository;
		this.movimentoRepository = movimentoRepository;
	}
	
	@Override
	public Conto creaConto(List<Integer> intestatariIds) {
		
		Conto conto = new Conto();
		conto.setDataApertura(LocalDateTime.now());
		conto.setSaldoAttuale(BigDecimal.ZERO);
		
		for(int i=0; i<intestatariIds.size(); i++) {
			
			Integer idIntestatario = intestatariIds.get(i);
			Cliente cliente = clienteRepository.findById(idIntestatario)
					.orElseThrow(()-> new RuntimeException("Cliente non trovato"));
			
			ContoCliente contoCliente = new ContoCliente();
			
			contoCliente.setConto(conto);
			contoCliente.setCliente(cliente);
			
			conto.getClienti().add(contoCliente);			
			
		}
		
		return contoRepository.save(conto);
		
	}

	@Override
	public Conto estrattoConto(Integer idConto) {
		Conto conto = contoRepository.findById(idConto)
			.orElseThrow(()-> new RuntimeException("Conto non trovato"));
		
		return conto;
	}
	
	@Override
	public Conto prelievo(Integer idConto, Integer idCliente, BigDecimal importo) {  
		
		Conto conto = contoRepository.findById(idConto)
			.orElseThrow(()-> new RuntimeException("conto inesistente"));
		
		Cliente cliente = clienteRepository.findById(idCliente)
		        .orElseThrow(() -> new RuntimeException("Cliente inesistente"));
		
		boolean isIntestatario = contoClienteRepository.verificaIntestatarioConto(idConto, idCliente);    //verifico con una query che il cliente sia int. del conto
		if(!isIntestatario) {
			throw new RuntimeException("Questo cliente non possiede questo conto");                       //se non lo è viene lanciata un'eccezione
		}
		
		if (conto.getSaldoAttuale().compareTo(importo) < 0) {                          //il bigDecimal non amemette = quindi uso compareTo
	        throw new RuntimeException("Saldo insufficiente per il prelievo");		   //se saldoAttuale è minore di importo restituisce un valore negativo
	    }
		
		conto.setSaldoAttuale(conto.getSaldoAttuale().subtract(importo));             //altrimenti eseguiamo il prelievo 
		
		contoRepository.save(conto);												  //aggiorno il conto
		
		Movimento movimento = new Movimento();										  //registro il movimento
		movimento.setConto(conto);
		movimento.setCliente(cliente);
		movimento.setDataMovimento(LocalDateTime.now());
		movimento.setImporto(importo);
		movimento.setTipoMovimento(TipoMovimento.PRELIEVO);
			
		movimentoRepository.save(movimento);
		
		return conto;
	}
	
	
	@Override
	public Conto deposito(Integer idConto, Integer idCliente, BigDecimal importo) {
		
		Conto conto = contoRepository.findById(idConto)
				.orElseThrow(()-> new RuntimeException("conto inesistente"));
			
			Cliente cliente = clienteRepository.findById(idCliente)
			        .orElseThrow(() -> new RuntimeException("Cliente inesistente"));
			
			boolean isIntestatario = contoClienteRepository.verificaIntestatarioConto(idConto, idCliente);    //verifico con una query che il cliente sia int. del conto
			if(!isIntestatario) {
				throw new RuntimeException("Questo cliente non possiede questo conto");                       //se non lo è viene lanciata un'eccezione
			}
			
			conto.setSaldoAttuale(conto.getSaldoAttuale().add(importo));
			
			contoRepository.save(conto);												  //aggiorno il conto
			
			Movimento movimento = new Movimento();										  //registro il movimento
			movimento.setConto(conto);
			movimento.setCliente(cliente);
			movimento.setDataMovimento(LocalDateTime.now());
			movimento.setImporto(importo);
			movimento.setTipoMovimento(TipoMovimento.DEPOSITO);
				
			movimentoRepository.save(movimento);
			
			return conto;
	}

	@Override
	public void cancellaConto(Integer idConto) {
		contoRepository.findById(idConto)
		.orElseThrow(()-> new RuntimeException("Conto non trovato"));
		
		contoRepository.deleteById(idConto);
		
	}

}
