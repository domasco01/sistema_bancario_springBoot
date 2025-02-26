package it.micegroup.sistema_bancario.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.micegroup.sistema_bancario.domain.Cliente;
import it.micegroup.sistema_bancario.repository.ClienteRepository;
import it.micegroup.sistema_bancario.service.ClienteService;



@Service("ClienteServiceImpl")
public class ClienteServiceImpl implements ClienteService{

	private ClienteRepository clienteRepository;
	

	public ClienteServiceImpl(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}
	
	
	@Override
	@Transactional
	public Cliente creaCliente(Cliente cliente) {
		
		 
		 return clienteRepository.save(cliente);
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public Cliente trovaClientePerId(Integer idCliente) {
		Cliente cliente = clienteRepository.findById(idCliente)
		.orElseThrow(()-> new RuntimeException("Cliente non trovato"));
		return cliente;
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> trovaTuttiIClienti() {
		
		List<Cliente> clienti = clienteRepository.findAll();    
		
		return clienti;
	}
	
	
	
	
	@Override
	@Transactional
	public void cancellaCliente(Integer idCliente) {
		clienteRepository.findById(idCliente)
		.orElseThrow(()-> new RuntimeException("Cliente non trovato"));
		
		clienteRepository.deleteById(idCliente);
	}
	
	
	@Override
	@Transactional
	public Cliente modificaCliente(Cliente clienteAggiornato, Integer id) {
		
		
		Cliente cliente = clienteRepository.findById(id)
        		.orElseThrow(()->new RuntimeException("Cliente non trovato"));
		
        
        cliente.setNomeCliente(clienteAggiornato.getNomeCliente());
        cliente.setCognomeCliente(clienteAggiornato.getCognomeCliente());
        cliente.setDataNascita(clienteAggiornato.getDataNascita());
        
        
        return clienteRepository.save(cliente);
        
	}
}
