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

import it.micegroup.sistema_bancario.domain.Cliente;
import it.micegroup.sistema_bancario.dto.EditClienteDTO;
import it.micegroup.sistema_bancario.dto.ViewClienteDTO;
import it.micegroup.sistema_bancario.mapper.ClienteMapper;
import it.micegroup.sistema_bancario.service.ClienteService;

@RestController
public class ClienteController {
	
	private ClienteService clienteService;
	private ClienteMapper clienteMapper;
	
	public ClienteController(@Qualifier("ClienteServiceImpl") ClienteService clienteService, ClienteMapper clienteMapper) {
		this.clienteService = clienteService;
		this.clienteMapper = clienteMapper;
	}
	
	@PostMapping("/cliente")
	public ResponseEntity<ViewClienteDTO> creaCliente(@RequestBody EditClienteDTO clienteDTO) {

		Cliente cliente = clienteMapper.toEntity(clienteDTO);

		Cliente nuovoCliente = clienteService.creaCliente(cliente);
		ViewClienteDTO nuovoClienteDTO = clienteMapper.toDto(nuovoCliente);
		
		return ResponseEntity.ok(nuovoClienteDTO);
	} 
	
	@GetMapping("/cliente/{id}")
	public ResponseEntity<ViewClienteDTO> trovaClientePerId(@PathVariable Integer id) {
		
		Cliente cliente = clienteService.trovaClientePerId(id);
		ViewClienteDTO clienteDTO = clienteMapper.toDto(cliente);
		
		if (cliente != null) {
            return ResponseEntity.ok(clienteDTO);
        } else {
            return ResponseEntity.notFound().build();  
        }
	}
	
	
	@GetMapping("/cliente")
	public ResponseEntity<List<ViewClienteDTO>> trovaTuttiIClienti() {
		
		List<Cliente> clienti = clienteService.trovaTuttiIClienti();
		
		if (clienti != null) {
			List<ViewClienteDTO> clientiDTO = new ArrayList<>();
			for(Cliente cliente : clienti) {
				clientiDTO.add(clienteMapper.toDto(cliente));
			}
			return ResponseEntity.ok(clientiDTO);
		}else {
            return ResponseEntity.notFound().build();  
        }
	}
	
	@DeleteMapping("/cliente/{id}")
	public ResponseEntity<String> cancellaCliente(@PathVariable Integer id) {
		
		clienteService.cancellaCliente(id);
		return ResponseEntity.ok("Cancellazione effettuata con successo");
	}
	
	@PutMapping("/cliente-modifica/{id}")
	public ResponseEntity<ViewClienteDTO> modificaCliente(@RequestBody EditClienteDTO clienteDTO, @PathVariable Integer id) {
		
		Cliente cliente = clienteMapper.toEntity(clienteDTO);
		clienteService.modificaCliente(cliente, id);
		
		return ResponseEntity.ok(clienteMapper.toDto(cliente));
	}
}
