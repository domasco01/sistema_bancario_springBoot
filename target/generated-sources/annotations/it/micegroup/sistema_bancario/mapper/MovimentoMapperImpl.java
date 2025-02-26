package it.micegroup.sistema_bancario.mapper;

import it.micegroup.sistema_bancario.domain.Cliente;
import it.micegroup.sistema_bancario.domain.Conto;
import it.micegroup.sistema_bancario.domain.Movimento;
import it.micegroup.sistema_bancario.dto.EditMovimentoDTO;
import it.micegroup.sistema_bancario.dto.ViewMovimentoDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-13T09:49:48+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Eclipse Adoptium)"
)
@Component
public class MovimentoMapperImpl implements MovimentoMapper {

    @Override
    public ViewMovimentoDTO toDto(Movimento movimento) {
        if ( movimento == null ) {
            return null;
        }

        ViewMovimentoDTO viewMovimentoDTO = new ViewMovimentoDTO();

        viewMovimentoDTO.setIdCliente( movimentoClienteIdCliente( movimento ) );
        viewMovimentoDTO.setIdConto( movimentoContoIdConto( movimento ) );
        viewMovimentoDTO.setNomeCliente( movimentoClienteNomeCliente( movimento ) );
        viewMovimentoDTO.setCognomeCliente( movimentoClienteCognomeCliente( movimento ) );
        viewMovimentoDTO.setImporto( movimento.getImporto() );
        viewMovimentoDTO.setTipoMovimento( movimento.getTipoMovimento() );
        viewMovimentoDTO.setDataMovimento( movimento.getDataMovimento() );

        return viewMovimentoDTO;
    }

    @Override
    public Movimento toEntity(EditMovimentoDTO movimentoDTO) {
        if ( movimentoDTO == null ) {
            return null;
        }

        Movimento movimento = new Movimento();

        movimento.setCliente( editMovimentoDTOToCliente( movimentoDTO ) );
        movimento.setConto( editMovimentoDTOToConto( movimentoDTO ) );
        movimento.setTipoMovimento( movimentoDTO.getTipoMovimento() );
        movimento.setImporto( movimentoDTO.getImporto() );

        return movimento;
    }

    private Integer movimentoClienteIdCliente(Movimento movimento) {
        Cliente cliente = movimento.getCliente();
        if ( cliente == null ) {
            return null;
        }
        return cliente.getIdCliente();
    }

    private Integer movimentoContoIdConto(Movimento movimento) {
        Conto conto = movimento.getConto();
        if ( conto == null ) {
            return null;
        }
        return conto.getIdConto();
    }

    private String movimentoClienteNomeCliente(Movimento movimento) {
        Cliente cliente = movimento.getCliente();
        if ( cliente == null ) {
            return null;
        }
        return cliente.getNomeCliente();
    }

    private String movimentoClienteCognomeCliente(Movimento movimento) {
        Cliente cliente = movimento.getCliente();
        if ( cliente == null ) {
            return null;
        }
        return cliente.getCognomeCliente();
    }

    protected Cliente editMovimentoDTOToCliente(EditMovimentoDTO editMovimentoDTO) {
        if ( editMovimentoDTO == null ) {
            return null;
        }

        Cliente cliente = new Cliente();

        cliente.setIdCliente( editMovimentoDTO.getIdCliente() );

        return cliente;
    }

    protected Conto editMovimentoDTOToConto(EditMovimentoDTO editMovimentoDTO) {
        if ( editMovimentoDTO == null ) {
            return null;
        }

        Conto conto = new Conto();

        conto.setIdConto( editMovimentoDTO.getIdConto() );

        return conto;
    }
}
