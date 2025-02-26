package it.micegroup.sistema_bancario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.micegroup.sistema_bancario.domain.Conto;

@Repository
public interface ContoRepository extends JpaRepository<Conto, Integer> {

}
