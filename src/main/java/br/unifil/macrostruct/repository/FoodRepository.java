package br.unifil.macrostruct.repository;

import br.unifil.macrostruct.model.FoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<FoodEntity, Long> {
}
