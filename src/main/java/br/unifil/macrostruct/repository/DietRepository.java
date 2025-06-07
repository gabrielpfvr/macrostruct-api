package br.unifil.macrostruct.repository;

import br.unifil.macrostruct.model.Diet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DietRepository extends JpaRepository<Diet, Integer> {
}
