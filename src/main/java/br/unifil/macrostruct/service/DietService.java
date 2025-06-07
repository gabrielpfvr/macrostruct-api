package br.unifil.macrostruct.service;

import br.unifil.macrostruct.dto.CustomPageRequest;
import br.unifil.macrostruct.dto.DietListResponse;
import br.unifil.macrostruct.dto.DietRequest;
import br.unifil.macrostruct.dto.DietResponse;
import br.unifil.macrostruct.model.Diet;
import br.unifil.macrostruct.model.User;
import br.unifil.macrostruct.repository.DietRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DietService {

    private final UserService userService;
    private final DietRepository repository;

    public DietResponse save(DietRequest request) {
        User user = this.userService.getUserFromToken();
        Diet diet = Diet.from(request, user);
        diet.getMeals().forEach(meal -> meal.setDiet(diet));

        return DietResponse.from(this.repository.save(diet));
    }

    public PagedModel<DietListResponse> getAll(CustomPageRequest pageRequest) {
        User user = this.userService.getUserFromToken();
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Diet diet = Diet.fromUser(user);
        Example<Diet> example = Example.of(diet, matcher);

        return new PagedModel<>(this.repository.findAll(example, pageRequest)
                .map(DietListResponse::from));
    }

    public DietResponse findById(Integer id) {
        return this.repository.findById(id)
                .map(DietResponse::from)
                .orElseThrow(() -> new EntityNotFoundException("Dieta não encontrada."));
    }
}
