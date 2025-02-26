package it.micegroup.sistema_bancario.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.micegroup.sistema_bancario.domain.Cliente;
import it.micegroup.sistema_bancario.domain.Conto;
import it.micegroup.sistema_bancario.domain.Movimento;
import it.micegroup.sistema_bancario.domain.TipoMovimento;
import it.micegroup.sistema_bancario.repository.ClienteRepository;
import it.micegroup.sistema_bancario.repository.ContoRepository;
import it.micegroup.sistema_bancario.repository.MovimentoRepository;
import it.micegroup.sistema_bancario.service.MovimentoService;

@Service("MovimentoServiceImpl")
public class MovimentoServiceImpl implements MovimentoService {
	
	private MovimentoRepository movimentoRepository;
	private ClienteRepository clienteRepository;
	private ContoRepository contoRepository;
	
	public MovimentoServiceImpl(MovimentoRepository movimentoRepository, ClienteRepository clienteRepository, ContoRepository contoRepository) {
		this.movimentoRepository = movimentoRepository;
		this.clienteRepository = clienteRepository;
		this.contoRepository = contoRepository;
	}
	
	@Override
	@Transactional
	public Movimento creaMovimento(Movimento movimento) {
		
		Cliente cliente = clienteRepository.findById(movimento.getCliente().getIdCliente())
                .orElseThrow(() -> new RuntimeException("Cliente inesistente"));
        
		Conto conto = contoRepository.findById(movimento.getConto().getIdConto())
                .orElseThrow(() -> new RuntimeException("Conto inesistente"));
        
		
		Movimento nuovoMovimento = new Movimento();
        nuovoMovimento.setConto(conto);
        nuovoMovimento.setCliente(cliente);
        nuovoMovimento.setDataMovimento(LocalDateTime.now());
        nuovoMovimento.setImporto(movimento.getImporto());
        nuovoMovimento.setTipoMovimento(movimento.getTipoMovimento());
		
		
		return movimentoRepository.save(nuovoMovimento);
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public Movimento ottieniMovimentoPerId(Integer idMovimento) {
		
		Movimento movimento = movimentoRepository.findById(idMovimento)
			.orElseThrow(()-> new RuntimeException("Movimento non trovato"));
		
		return movimento;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Movimento> mostraTuttiMovimenti() {
		
		List<Movimento> movimenti = movimentoRepository.findAll();    
		
		return movimenti;
	}
	
	
	@Override
	@Transactional
	public void cancellaMovimento(Integer idMovimento) {
		movimentoRepository.findById(idMovimento)
		.orElseThrow(()-> new RuntimeException("Movimento non trovato"));
		
		movimentoRepository.deleteById(idMovimento);
	}
	
	@Override
	@Transactional
	public Movimento modificaMovimento(Integer idMovimento,  Movimento movimentoAggiornato) {
		
		Movimento movimento = movimentoRepository.findById(idMovimento)
				.orElseThrow(()-> new RuntimeException("Movimento inesistente"));
		
		Cliente cliente = clienteRepository.findById(movimentoAggiornato.getCliente().getIdCliente())
				.orElseThrow(()-> new RuntimeException("Cliente inesistente"));
		
		Conto conto = contoRepository.findById(movimentoAggiornato.getConto().getIdConto())
				.orElseThrow(()-> new RuntimeException("Conto inesistente"));
		
		movimento.setCliente(cliente);
		movimento.setConto(conto);
		movimento.setImporto(movimentoAggiornato.getImporto());
		movimento.setTipoMovimento(movimentoAggiornato.getTipoMovimento());
		
		return movimentoRepository.save(movimento);
	
	}
	
	
	
	
	
}

