package com.estetify.backend.controller.itens;

import com.estetify.backend.models.itens.ItensProduct;
import com.estetify.backend.repository.ItensProductRepository;
import com.estetify.backend.utils.ItensType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/itens")
public class ItensProductController {

    private final ItensProductRepository repository;

    public ItensProductController(ItensProductRepository repository) {
        this.repository = repository;
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<ItensProduct>> getAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ItensProduct> getById(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST
    @PostMapping
    public ResponseEntity<ItensProduct> create(@RequestBody ItensProduct item) {
        // Definir automaticamente o tipo se necessário
        item.setItensType(ItensType.PRODUCT);
        ItensProduct savedItem = repository.save(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedItem);
    }
}
