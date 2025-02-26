package it.micegroup.sistema_bancario.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import it.micegroup.sistema_bancario.domain.Conto;
import it.micegroup.sistema_bancario.dto.ViewContoDTO;

@Mapper(componentModel = "spring", uses=ContoClienteMapper.class)
public interface ContoMapper {
	ContoMapper INSTANCE = Mappers.getMapper(ContoMapper.class);
	
	ViewContoDTO toDto(Conto conto);
	Conto toEntity(ViewContoDTO contoDTO);
}
