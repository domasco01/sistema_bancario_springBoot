package it.micegroup.sistema_bancario.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import it.micegroup.sistema_bancario.domain.Cliente;
import it.micegroup.sistema_bancario.dto.EditClienteDTO;
import it.micegroup.sistema_bancario.dto.ViewClienteDTO;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
	ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

	ViewClienteDTO toDto(Cliente cliente);
    Cliente toEntity(EditClienteDTO clienteDTO);
}
