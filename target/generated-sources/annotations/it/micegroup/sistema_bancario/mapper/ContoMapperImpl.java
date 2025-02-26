package it.micegroup.sistema_bancario.mapper;

import it.micegroup.sistema_bancario.domain.Conto;
import it.micegroup.sistema_bancario.domain.ContoCliente;
import it.micegroup.sistema_bancario.dto.ViewContoClienteDTO;
import it.micegroup.sistema_bancario.dto.ViewContoDTO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-13T09:49:48+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Eclipse Adoptium)"
)
@Component
public class ContoMapperImpl implements ContoMapper {

    @Autowired
    private ContoClienteMapper contoClienteMapper;

    @Override
    public ViewContoDTO toDto(Conto conto) {
        if ( conto == null ) {
            return null;
        }

        ViewContoDTO viewContoDTO = new ViewContoDTO();

        viewContoDTO.setIdConto( conto.getIdConto() );
        if ( conto.getSaldoAttuale() != null ) {
            viewContoDTO.setSaldoAttuale( conto.getSaldoAttuale().doubleValue() );
        }
        viewContoDTO.setDataApertura( conto.getDataApertura() );
        viewContoDTO.setClienti( contoClienteCollectionToViewContoClienteDTOList( conto.getClienti() ) );

        return viewContoDTO;
    }

    @Override
    public Conto toEntity(ViewContoDTO contoDTO) {
        if ( contoDTO == null ) {
            return null;
        }

        Conto conto = new Conto();

        conto.setIdConto( contoDTO.getIdConto() );
        conto.setSaldoAttuale( BigDecimal.valueOf( contoDTO.getSaldoAttuale() ) );
        conto.setDataApertura( contoDTO.getDataApertura() );
        conto.setClienti( viewContoClienteDTOListToContoClienteCollection( contoDTO.getClienti() ) );

        return conto;
    }

    protected List<ViewContoClienteDTO> contoClienteCollectionToViewContoClienteDTOList(Collection<ContoCliente> collection) {
        if ( collection == null ) {
            return null;
        }

        List<ViewContoClienteDTO> list = new ArrayList<ViewContoClienteDTO>( collection.size() );
        for ( ContoCliente contoCliente : collection ) {
            list.add( contoClienteMapper.toDto( contoCliente ) );
        }

        return list;
    }

    protected ContoCliente viewContoClienteDTOToContoCliente(ViewContoClienteDTO viewContoClienteDTO) {
        if ( viewContoClienteDTO == null ) {
            return null;
        }

        ContoCliente contoCliente = new ContoCliente();

        return contoCliente;
    }

    protected Collection<ContoCliente> viewContoClienteDTOListToContoClienteCollection(List<ViewContoClienteDTO> list) {
        if ( list == null ) {
            return null;
        }

        Collection<ContoCliente> collection = new ArrayList<ContoCliente>( list.size() );
        for ( ViewContoClienteDTO viewContoClienteDTO : list ) {
            collection.add( viewContoClienteDTOToContoCliente( viewContoClienteDTO ) );
        }

        return collection;
    }
}
