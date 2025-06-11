package br.unifil.macrostruct.repository;

import br.unifil.macrostruct.model.FoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FoodRepository extends JpaRepository<FoodEntity, Integer> {

    @Transactional
    void deleteByIdIn(List<Integer> ids);
}
