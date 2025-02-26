package it.micegroup.sistema_bancario.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.micegroup.sistema_bancario.domain.Cliente;
import it.micegroup.sistema_bancario.domain.Conto;
import it.micegroup.sistema_bancario.domain.Movimento;
import it.micegroup.sistema_bancario.dto.ContoRequestDTO;
import it.micegroup.sistema_bancario.dto.EditEstrattoContoDTO;
import it.micegroup.sistema_bancario.dto.EditMovimentoDTO;
import it.micegroup.sistema_bancario.dto.ViewClienteDTO;
import it.micegroup.sistema_bancario.dto.ViewContoDTO;
import it.micegroup.sistema_bancario.dto.ViewEstrattoContoDTO;
import it.micegroup.sistema_bancario.dto.ViewMovimentoDTO;
import it.micegroup.sistema_bancario.mapper.ContoMapper;
import it.micegroup.sistema_bancario.mapper.MovimentoMapper;
import it.micegroup.sistema_bancario.service.ContoService;

@RestController
public class ContoController {
	
	private ContoService contoService;
	private ContoMapper contoMapper;
	private MovimentoMapper movimentoMapper;
	
	public ContoController(@Qualifier("ContoServiceImpl") ContoService contoService, ContoMapper contoMapper, MovimentoMapper movimentoMapper) {
		
		this.contoService = contoService;
		this.contoMapper = contoMapper;
		this.movimentoMapper = movimentoMapper;
	}
	
	@PostMapping("/conto/creazione")
	public ResponseEntity<ViewContoDTO> creaConto(@RequestBody ContoRequestDTO contoRequest) {
		
		Conto conto = contoService.creaConto(contoRequest.getIntestatari());

		ViewContoDTO contoDTO = contoMapper.toDto(conto);
		
		return ResponseEntity.ok(contoDTO);
	}
	
	
	
	@PostMapping("/{idConto}/deposito")
	public ResponseEntity<ViewContoDTO> deposito(@RequestBody EditMovimentoDTO movimentoDTO) {
		
		Movimento movimento = movimentoMapper.toEntity(movimentoDTO);
		
		Conto contoAggiornato = contoService.deposito(movimento);
		
		ViewContoDTO contoDTO = contoMapper.toDto(contoAggiornato);
		
        return ResponseEntity.ok(contoDTO);
	}
	
	
	@PostMapping("{idConto}/prelievo")
	public ResponseEntity<ViewContoDTO> prelievo(@RequestBody EditMovimentoDTO movimentoDTO) {
		
		Movimento movimento = movimentoMapper.toEntity(movimentoDTO);
		
		Conto contoAggiornato = contoService.prelievo(movimento);
		
		ViewContoDTO contoDTO = contoMapper.toDto(contoAggiornato);
		
        return ResponseEntity.ok(contoDTO);
	}
	
	
	@GetMapping("/conto/estrattoConto/{id}")
	public ResponseEntity<ViewEstrattoContoDTO> estrattoConto(@PathVariable Integer id, @RequestParam LocalDate dataInizio, @RequestParam LocalDate dataFine) {
		
		List<Movimento> movimenti = contoService.estrattoConto(id, dataInizio, dataFine);
		
		
		Conto conto = contoService.ottieniContoDaId(id);
		
		List<ViewMovimentoDTO> movimentiDTO = new ArrayList<>();
		
		for(Movimento movimento : movimenti) {
			movimentiDTO.add(movimentoMapper.toDto(movimento));
		}
		
		ViewEstrattoContoDTO estrattoConto = new ViewEstrattoContoDTO(movimentiDTO, conto.getSaldoAttuale());
		
		return ResponseEntity.ok(estrattoConto );
		
	}
	
	@GetMapping("/conti")
	public ResponseEntity <List<ViewContoDTO>> ottieniTuttiConti() {
		
		List<Conto> conti = contoService.ottieniTuttiConti();
		
		if (conti != null) {
			List<ViewContoDTO> contiDTO = new ArrayList<>();
			for(Conto conto : conti) {
				contiDTO.add(contoMapper.toDto(conto));
			}
			return ResponseEntity.ok(contiDTO);
		}else {
            return ResponseEntity.notFound().build();  
        }
	
	}
	
	
	@GetMapping("/conto/{idConto}")
	public ResponseEntity <ViewContoDTO> ottieniContoDaId(@PathVariable Integer idConto) {
		
		Conto conto = contoService.ottieniContoDaId(idConto);
		
		ViewContoDTO contoDTO = contoMapper.toDto(conto);
		
		return ResponseEntity.ok(contoDTO);
	}
	
	@DeleteMapping("/conto/{id}")
	public ResponseEntity<String> cancellaConto(@PathVariable Integer id) {
			
			contoService.cancellaConto(id);
			return ResponseEntity.ok("Cancellazione effettuata con successo");
	}
	
	
	@PutMapping("/modifica-conto/{id}")
	public ResponseEntity<ViewContoDTO> modificaConto(@PathVariable Integer id, @RequestBody ContoRequestDTO contoRequest) {
		
		Conto contoAggiornato = contoService.modificaConto(id, contoRequest.getIntestatari());
		
		ViewContoDTO contoDTO = contoMapper.toDto(contoAggiornato);
		return ResponseEntity.ok(contoDTO);
	}
	
}


