package it.micegroup.sistema_bancario.controller;

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
import org.springframework.web.bind.annotation.RestController;

import it.micegroup.sistema_bancario.domain.Movimento;
import it.micegroup.sistema_bancario.dto.EditMovimentoDTO;
import it.micegroup.sistema_bancario.dto.ViewMovimentoDTO;
import it.micegroup.sistema_bancario.mapper.MovimentoMapper;
import it.micegroup.sistema_bancario.service.MovimentoService;

@RestController
public class MovimentoController {
	
	private MovimentoService movimentoService;
	private MovimentoMapper movimentoMapper;
	
	public MovimentoController(@Qualifier("MovimentoServiceImpl") MovimentoService movimentoService, MovimentoMapper movimentoMapper) {
		
		this.movimentoService = movimentoService;
		this.movimentoMapper = movimentoMapper;
	}
	
	@PostMapping("/movimento/creazione")
	public ResponseEntity<ViewMovimentoDTO> creaMovimento( @RequestBody EditMovimentoDTO movDTO) {
		
		
		
		Movimento movimento = movimentoMapper.toEntity(movDTO);
				movimentoService.creaMovimento( movimento);
		
		ViewMovimentoDTO movimentoDTO = movimentoMapper.toDto(movimento);
		
		return ResponseEntity.ok(movimentoDTO);
	}
	
	
	@GetMapping("/movimento/{id}")
	public ResponseEntity<ViewMovimentoDTO> ottieniMovimentoPerId(Integer id) {
		
		Movimento movimento = movimentoService.ottieniMovimentoPerId(id);
		
		ViewMovimentoDTO movimentoDTO = movimentoMapper.toDto(movimento);

		if (movimento != null) {
            return ResponseEntity.ok(movimentoDTO);
        } else {
            return ResponseEntity.notFound().build();  
        }
	}
	
	@GetMapping("/movimento")
	public ResponseEntity<List<ViewMovimentoDTO>> mostraTuttiMovimenti() {
		
		List<Movimento> movimenti = movimentoService.mostraTuttiMovimenti();
	
		if (movimenti != null) {
			List<ViewMovimentoDTO> movimentiDTO = new ArrayList<>();
			
			for(Movimento movimento : movimenti) {
				movimentiDTO.add(movimentoMapper.toDto(movimento));
			}
			return ResponseEntity.ok(movimentiDTO);
		}else {
            return ResponseEntity.notFound().build();  
        }
	}
	
	@DeleteMapping("/movimento/{id}")
	public ResponseEntity<String> cancellaMovimento(@PathVariable Integer id) {
		
		movimentoService.cancellaMovimento(id);
		return ResponseEntity.ok("Cancellazione effettuata con successo");
	}
	
	@PutMapping("/movimento-modifica/{id}")
	public ResponseEntity<ViewMovimentoDTO> modificaMovimento(@PathVariable Integer id, @RequestBody EditMovimentoDTO movimentoDTO) {
 
		
		Movimento movimento = movimentoMapper.toEntity(movimentoDTO);
		movimentoService.modificaMovimento(id, movimento);
		
		return ResponseEntity.ok(movimentoMapper.toDto(movimento));
	}
	
	
	
	
	
	
	
	
	
	
}
