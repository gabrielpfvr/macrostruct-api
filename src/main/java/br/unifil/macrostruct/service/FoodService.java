package br.unifil.macrostruct.service;

import br.unifil.macrostruct.dto.FoodEntityRequest;
import br.unifil.macrostruct.model.FoodEntity;
import br.unifil.macrostruct.model.User;
import br.unifil.macrostruct.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

}
