package br.unifil.macrostruct.controller;

import br.unifil.macrostruct.dto.CustomPageRequest;
import br.unifil.macrostruct.dto.DietListResponse;
import br.unifil.macrostruct.dto.DietRequest;
import br.unifil.macrostruct.dto.DietResponse;
import br.unifil.macrostruct.service.DietService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/diet")
@RequiredArgsConstructor
public class DietController {

    private final DietService dietService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DietResponse create(@Valid @RequestBody DietRequest dietRequest) {
        return this.dietService.save(dietRequest);
    }

    @GetMapping
    public PagedModel<DietListResponse> getAll(CustomPageRequest pageRequest) {
        return this.dietService.getAll(pageRequest);
    }

    @GetMapping("{id}")
    public DietResponse getById(@PathVariable Integer id) {
        return this.dietService.getById(id);
    }

    @PutMapping("{id}")
    public DietResponse create(@PathVariable Integer id, @Valid @RequestBody DietRequest dietRequest) {
        return this.dietService.update(id, dietRequest);
    }

    @DeleteMapping("/{id}/delete")
    public void deleteByIds(@PathVariable Integer id) {
        this.dietService.delete(id);
    }
}
