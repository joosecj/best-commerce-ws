package com.github.bestcommerce.controllers;

import com.github.bestcommerce.dtos.v1.CategoryDTO;
import com.github.bestcommerce.services.CategoryProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/categories")
public class CategoryController {
    private final CategoryProductService categoryProductService;

    public CategoryController(CategoryProductService categoryProductService) {
        this.categoryProductService = categoryProductService;
    }

    @GetMapping
    public ResponseEntity<Page<CategoryDTO>> findAll(Pageable pageable) {
        Page<CategoryDTO> dto = categoryProductService.findAll(pageable);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<Page<CategoryDTO>> searchByName(@RequestParam(name = "name", defaultValue = "") String name,
                                                    Pageable pageable) {
        Page<CategoryDTO> dto = categoryProductService.searchByName(name, pageable);
        return ResponseEntity.ok(dto);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<CategoryDTO> insert(@RequestBody CategoryDTO dto) {
        dto = categoryProductService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CategoryDTO> update(@PathVariable UUID id, @RequestBody CategoryDTO dto) {
        dto = categoryProductService.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        categoryProductService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
