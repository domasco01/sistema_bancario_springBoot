package it.micegroup.sistema_bancario.mapper;

import it.micegroup.sistema_bancario.domain.Cliente;
import it.micegroup.sistema_bancario.dto.EditClienteDTO;
import it.micegroup.sistema_bancario.dto.ViewClienteDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-13T09:49:47+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Eclipse Adoptium)"
)
@Component
public class ClienteMapperImpl implements ClienteMapper {

    @Override
    public ViewClienteDTO toDto(Cliente cliente) {
        if ( cliente == null ) {
            return null;
        }

        ViewClienteDTO viewClienteDTO = new ViewClienteDTO();

        viewClienteDTO.setIdCliente( cliente.getIdCliente() );
        viewClienteDTO.setNomeCliente( cliente.getNomeCliente() );
        viewClienteDTO.setCognomeCliente( cliente.getCognomeCliente() );
        viewClienteDTO.setDataNascita( cliente.getDataNascita() );

        return viewClienteDTO;
    }

    @Override
    public Cliente toEntity(EditClienteDTO clienteDTO) {
        if ( clienteDTO == null ) {
            return null;
        }

        Cliente cliente = new Cliente();

        cliente.setNomeCliente( clienteDTO.getNomeCliente() );
        cliente.setCognomeCliente( clienteDTO.getCognomeCliente() );
        cliente.setDataNascita( clienteDTO.getDataNascita() );

        return cliente;
    }
}
