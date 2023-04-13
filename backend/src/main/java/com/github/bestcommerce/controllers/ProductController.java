package com.github.bestcommerce.controllers;

import com.github.bestcommerce.dtos.v1.ProductCategoriesStoreDTO;
import com.github.bestcommerce.dtos.v1.ProductMinDTO;
import com.github.bestcommerce.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductCategoriesStoreDTO> findById(@PathVariable UUID id) {
        ProductCategoriesStoreDTO dto = productService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping
    public ResponseEntity<Page<ProductMinDTO>> findAll(Pageable pageable) {
        Page<ProductMinDTO> dto = productService.findAll(pageable);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<Page<ProductMinDTO>> searchByName(@RequestParam(name = "name", defaultValue = "") String name,
                                                            Pageable pageable) {
        Page<ProductMinDTO> dto = productService.findAllSearchName(name, pageable);
        return ResponseEntity.ok(dto);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<ProductCategoriesStoreDTO> insert(@Valid @RequestBody ProductCategoriesStoreDTO dto) {
        dto = productService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
}
