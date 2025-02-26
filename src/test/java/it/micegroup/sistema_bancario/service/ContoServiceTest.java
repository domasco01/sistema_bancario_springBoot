package it.micegroup.sistema_bancario.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import it.micegroup.sistema_bancario.domain.Cliente;
import it.micegroup.sistema_bancario.domain.Conto;
import it.micegroup.sistema_bancario.domain.Movimento;
import it.micegroup.sistema_bancario.domain.TipoMovimento;
import it.micegroup.sistema_bancario.repository.ClienteRepository;
import it.micegroup.sistema_bancario.repository.ContoClienteRepository;
import it.micegroup.sistema_bancario.repository.ContoRepository;
import it.micegroup.sistema_bancario.repository.MovimentoRepository;
import it.micegroup.sistema_bancario.service.impl.ContoServiceImpl;
import it.micegroup.sistema_bancario.service.impl.MovimentoServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ContoServiceTest {

	@Mock
	private ContoRepository contoRepository;
	
	@Mock
	private MovimentoRepository movimentoRepository;
	
	@Mock
	private ClienteRepository clienteRepository;
	
	@Mock
	private ContoClienteRepository contoClienteRepository;
	
	@Mock
    private MovimentoServiceImpl movimentoServiceImpl;
	
	@InjectMocks
	private ContoServiceImpl contoServiceImpl;
	
	
	@Test
	void testEstrattoConto_Success() {
	    Integer idConto = 1;
	    LocalDate dataInizio = LocalDate.of(2025, 2, 7);
	    LocalDate dataFine = LocalDate.of(2024, 2, 9);

	    Conto conto = new Conto();
	    conto.setIdConto(idConto);

	    Movimento movimento1 = new Movimento(1, TipoMovimento.DEPOSITO, new BigDecimal("100.00"), 
	                                         LocalDateTime.of(2025, 2, 7, 10, 0), null, conto);
	    Movimento movimento2 = new Movimento(2, TipoMovimento.PRELIEVO, new BigDecimal("-50.00"), 
	                                         LocalDateTime.of(2024, 2, 10, 14, 30), null, conto);
	    List<Movimento> movimenti = Arrays.asList(movimento1, movimento2);

	    when(contoRepository.findById(idConto)).thenReturn(Optional.of(conto));
	    when(movimentoRepository.estrattoConto(idConto, dataInizio, dataFine)).thenReturn(movimenti);

	    List<Movimento> result = contoServiceImpl.estrattoConto(idConto, dataInizio, dataFine);

	    assertEquals(2, result.size());
	    assertEquals(new BigDecimal("100.00"), result.get(0).getImporto());
	    assertEquals(new BigDecimal("-50.00"), result.get(1).getImporto());

	    verify(contoRepository, times(1)).findById(idConto);
	    verify(movimentoRepository, times(1)).estrattoConto(idConto, dataInizio, dataFine);
	}

	
	
	@Test
	void testPrelievo_Success() {
	    Integer idConto = 1;
	    Integer idCliente = 1;

	    Conto conto = new Conto();
	    conto.setIdConto(idConto);
	    conto.setSaldoAttuale(new BigDecimal("200.00"));

	    Cliente cliente = new Cliente();
	    cliente.setIdCliente(idCliente);

	    Movimento movimento = new Movimento(1, TipoMovimento.PRELIEVO, new BigDecimal("50.00"), LocalDateTime.now(), cliente, conto);

	    when(clienteRepository.findById(idCliente)).thenReturn(Optional.of(cliente));
	    when(contoRepository.findById(idConto)).thenReturn(Optional.of(conto));
	    
	    // Simula il fatto che il cliente sia intestatario del conto
	    when(contoClienteRepository.verificaIntestatarioConto(idCliente, idConto)).thenReturn(true);

	    when(contoRepository.save(conto)).thenReturn(conto);
	    when(movimentoServiceImpl.creaMovimento(movimento)).thenReturn(movimento);

	    Conto result = contoServiceImpl.prelievo(movimento);

	    assertEquals(new BigDecimal("150.00"), result.getSaldoAttuale());
	    verify(contoRepository, times(1)).save(conto);
	    verify(movimentoServiceImpl, times(1)).creaMovimento(movimento);
	}
	
	
	@Test
	void testDeposito_Success() {
	    Integer idConto = 1;
	    Integer idCliente = 1;

	    Conto conto = new Conto();
	    conto.setIdConto(idConto);
	    conto.setSaldoAttuale(new BigDecimal("200.00"));

	    Cliente cliente = new Cliente();
	    cliente.setIdCliente(idCliente);

	    Movimento movimento = new Movimento(1, TipoMovimento.DEPOSITO, new BigDecimal("20.00"), LocalDateTime.now(), cliente, conto);

	    when(clienteRepository.findById(idCliente)).thenReturn(Optional.of(cliente));
	    when(contoRepository.findById(idConto)).thenReturn(Optional.of(conto));
	    
	    // Simula il fatto che il cliente sia intestatario del conto
	    when(contoClienteRepository.verificaIntestatarioConto(idCliente, idConto)).thenReturn(true);

	    when(contoRepository.save(conto)).thenReturn(conto);
	    when(movimentoServiceImpl.creaMovimento(movimento)).thenReturn(movimento);

	    Conto result = contoServiceImpl.deposito(movimento);

	    assertEquals(new BigDecimal("220.00"), result.getSaldoAttuale());
	    verify(contoRepository, times(1)).save(conto);
	    verify(movimentoServiceImpl, times(1)).creaMovimento(movimento);
	}

    
	
}
