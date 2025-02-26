package it.micegroup.sistema_bancario.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import it.micegroup.sistema_bancario.domain.Movimento;
import it.micegroup.sistema_bancario.dto.EditMovimentoDTO;
import it.micegroup.sistema_bancario.dto.ViewMovimentoDTO;


@Mapper(componentModel = "spring")
public interface MovimentoMapper {

	MovimentoMapper INSTANCE = Mappers.getMapper(MovimentoMapper.class);
	
	@Mapping(target = "idCliente", source = "cliente.idCliente")
	@Mapping(target = "idConto", source = "conto.idConto")
	@Mapping(target= "nomeCliente", source = "cliente.nomeCliente")
	@Mapping(target= "cognomeCliente", source = "cliente.cognomeCliente")
	ViewMovimentoDTO toDto(Movimento movimento);
	
	@Mapping(target = "cliente.idCliente", source = "idCliente")
	@Mapping(target = "conto.idConto", source = "idConto")
	
	Movimento toEntity(EditMovimentoDTO movimentoDTO);
	
}
