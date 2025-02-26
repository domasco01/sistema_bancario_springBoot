package it.micegroup.sistema_bancario.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @EqualsAndHashCode @AllArgsConstructor @NoArgsConstructor
public class ContoRequestDTO {
	
	private List<Integer> intestatari;
}
