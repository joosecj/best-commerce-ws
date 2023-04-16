package com.github.bestcommerce.controllers;

import com.github.bestcommerce.dtos.v1.StoreDTO;
import com.github.bestcommerce.services.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.lang.model.type.UnionType;
import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/stores")
@Tag(name = "Stores", description = "Endpoints for Managing Stores")
public class StoreController {

    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "FindById Store", description = "FindById Store",
            tags = {"Stores"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = StoreDTO.class))
                                    )
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<StoreDTO> findById(@PathVariable UUID id) {
        StoreDTO dto = storeService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping
    @Operation(summary = "Find All Stores", description = "Find All Stores",
            tags = {"Stores"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = StoreDTO.class))
                                    )
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<Page<StoreDTO>> findAll(Pageable pageable) {
        Page<StoreDTO> dto = storeService.findAll(pageable);
        return ResponseEntity.ok(dto);
    }
    @PreAuthorize("hasRole('CLIENT')")
    @PostMapping(value = "/create")
    @Operation(summary = "Create Store", description = "Create Store",
            tags = {"Stores"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = StoreDTO.class))
                                    )
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<StoreDTO> insert(@Valid @RequestBody StoreDTO dto) {
        dto = storeService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/{id}")
    @Operation(summary = "Update Store", description = "Update Store",
            tags = {"Stores"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = StoreDTO.class))
                                    )
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
                    @ApiResponse(description = "Conflict", responseCode = "409", content = @Content),
            }
    )
    public ResponseEntity<StoreDTO> update(@PathVariable UUID id, @Valid @RequestBody StoreDTO dto) {
        dto = storeService.update(id, dto);
        return ResponseEntity.ok(dto);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete Store", description = "Delete Store",
            tags = {"Stores"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = UnionType.class))
                                    )
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        storeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
