package it.micegroup.sistema_bancario.mapper;

import it.micegroup.sistema_bancario.domain.Cliente;
import it.micegroup.sistema_bancario.domain.Conto;
import it.micegroup.sistema_bancario.domain.ContoCliente;
import it.micegroup.sistema_bancario.dto.EditContoClienteDTO;
import it.micegroup.sistema_bancario.dto.ViewContoClienteDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-13T09:49:48+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Eclipse Adoptium)"
)
@Component
public class ContoClienteMapperImpl implements ContoClienteMapper {

    @Override
    public ViewContoClienteDTO toDto(ContoCliente contoCliente) {
        if ( contoCliente == null ) {
            return null;
        }

        ViewContoClienteDTO viewContoClienteDTO = new ViewContoClienteDTO();

        viewContoClienteDTO.setClienteId( contoClienteClienteIdCliente( contoCliente ) );
        viewContoClienteDTO.setContoId( contoClienteContoIdConto( contoCliente ) );
        viewContoClienteDTO.setNomeCliente( contoClienteClienteNomeCliente( contoCliente ) );
        viewContoClienteDTO.setCognomeCliente( contoClienteClienteCognomeCliente( contoCliente ) );

        return viewContoClienteDTO;
    }

    @Override
    public ContoCliente toEntity(EditContoClienteDTO contoClienteDTO) {
        if ( contoClienteDTO == null ) {
            return null;
        }

        ContoCliente contoCliente = new ContoCliente();

        contoCliente.setCliente( contoClienteDTO.getCliente() );
        contoCliente.setConto( contoClienteDTO.getConto() );

        return contoCliente;
    }

    private Integer contoClienteClienteIdCliente(ContoCliente contoCliente) {
        Cliente cliente = contoCliente.getCliente();
        if ( cliente == null ) {
            return null;
        }
        return cliente.getIdCliente();
    }

    private Integer contoClienteContoIdConto(ContoCliente contoCliente) {
        Conto conto = contoCliente.getConto();
        if ( conto == null ) {
            return null;
        }
        return conto.getIdConto();
    }

    private String contoClienteClienteNomeCliente(ContoCliente contoCliente) {
        Cliente cliente = contoCliente.getCliente();
        if ( cliente == null ) {
            return null;
        }
        return cliente.getNomeCliente();
    }

    private String contoClienteClienteCognomeCliente(ContoCliente contoCliente) {
        Cliente cliente = contoCliente.getCliente();
        if ( cliente == null ) {
            return null;
        }
        return cliente.getCognomeCliente();
    }
}
