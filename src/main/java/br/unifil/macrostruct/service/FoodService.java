package br.unifil.macrostruct.service;

import br.unifil.macrostruct.dto.CustomPageRequest;
import br.unifil.macrostruct.dto.FoodEntityRequest;
import br.unifil.macrostruct.dto.FoodEntityResponse;
import br.unifil.macrostruct.model.FoodEntity;
import br.unifil.macrostruct.model.User;
import br.unifil.macrostruct.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final UserService userService;
    private final SheetService sheetService;
    private final FoodRepository repository;

    public void save(FoodEntityRequest request) {
        User user = this.userService.getUserFromToken();
        FoodEntity food = FoodEntity.from(request, user);

        this.repository.save(food);
    }

    public void saveFromSheet(MultipartFile file) {
        List<FoodEntity> list = this.sheetService.readFromSheet(file);
        User user = this.userService.getUserFromToken();
        list.forEach(i -> i.setUser(user));
        this.repository.saveAll(list);
    }

    public PagedModel<FoodEntityResponse> findAllByUser(CustomPageRequest pageRequest) {
        User user = this.userService.getUserFromToken();
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        FoodEntity food = FoodEntity.fromUser(user);
        Example<FoodEntity> example = Example.of(food, matcher);

        return new PagedModel<>(this.repository.findAll(example, pageRequest)
                .map(FoodEntityResponse::from));
    }

    public List<FoodEntityResponse> findAllList() {
        User user = this.userService.getUserFromToken();
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        FoodEntity food = FoodEntity.fromUser(user);
        Example<FoodEntity> example = Example.of(food, matcher);

        return this.repository.findAll(example).stream()
                .map(FoodEntityResponse::from)
                .sorted(Comparator.comparing(FoodEntityResponse::getDescription))
                .toList();
    }

    public void deleteByIds(List<Integer> ids) {
        this.repository.deleteByIdIn(ids);
    }
}
