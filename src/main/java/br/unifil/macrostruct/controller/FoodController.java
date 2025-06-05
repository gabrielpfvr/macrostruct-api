package br.unifil.macrostruct.controller;

import br.unifil.macrostruct.dto.CustomPageRequest;
import br.unifil.macrostruct.dto.FoodEntityRequest;
import br.unifil.macrostruct.dto.FoodEntityResponse;
import br.unifil.macrostruct.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/food")
@RequiredArgsConstructor
public class FoodController {

    private final FoodService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody FoodEntityRequest request) {
        this.service.save(request);
    }

    @PostMapping("import")
    public void createFromSheet(@RequestPart MultipartFile file) {
        this.service.saveFromSheet(file);
    }

    @GetMapping
    public PagedModel<FoodEntityResponse> getAll(CustomPageRequest pageRequest) {
        return this.service.findAllByUser(pageRequest);
    }

}
