package it.micegroup.sistema_bancario.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import it.micegroup.sistema_bancario.domain.ContoCliente;
import it.micegroup.sistema_bancario.dto.EditContoClienteDTO;
import it.micegroup.sistema_bancario.dto.ViewContoClienteDTO;


@Mapper(componentModel = "spring", uses= {ClienteMapper.class, ContoMapper.class})
public interface ContoClienteMapper {

	ContoClienteMapper INSTANCE = Mappers.getMapper(ContoClienteMapper.class);
	

    @Mapping(target = "clienteId", source = "cliente.idCliente")
    @Mapping(target = "contoId", source = "conto.idConto")
    @Mapping(target = "nomeCliente", source = "cliente.nomeCliente")
    @Mapping(target = "cognomeCliente", source = "cliente.cognomeCliente")
    
	ViewContoClienteDTO toDto (ContoCliente contoCliente);
	
	
	
	ContoCliente toEntity (EditContoClienteDTO contoClienteDTO);
	
}