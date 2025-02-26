package it.micegroup.sistema_bancario.service;

import java.util.List;

import it.micegroup.sistema_bancario.domain.Cliente;

public interface ClienteService {
	
	Cliente creaCliente(Cliente cliente);
	
	Cliente trovaClientePerId(Integer idCliente);
	
	List<Cliente> trovaTuttiIClienti();
	
	void cancellaCliente(Integer idCliente);
	
	Cliente modificaCliente(Cliente clienteAggiornato, Integer id);
}
