package it.micegroup.sistema_bancario.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @EqualsAndHashCode @AllArgsConstructor
public class EditEstrattoContoDTO {
	
	private Integer idConto;
	private LocalDate dataInizio;
    private LocalDate dataFine;
}
