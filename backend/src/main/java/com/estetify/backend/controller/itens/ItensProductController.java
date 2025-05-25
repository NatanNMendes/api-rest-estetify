package com.estetify.backend.controller.itens;

import com.estetify.backend.models.itens.ItensProduct;
import com.estetify.backend.repository.itens.ItensProductRepository;
import com.estetify.backend.utils.ItensType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
        item.setItensType(ItensType.PRODUCT);
        item.setCreatedAt(LocalDateTime.now());
        ItensProduct savedItem = repository.save(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedItem);
    }

    // PUT (Update completo)
    @PutMapping("/{id}")
    public ResponseEntity<ItensProduct> update(@PathVariable Long id, @RequestBody ItensProduct itemAtualizado) {
        return repository.findById(id)
                .map(itemExistente -> {
                    // Atualiza todos os campos
                    itemExistente.setName(itemAtualizado.getName());
                    itemExistente.setPrice(itemAtualizado.getPrice());
                    itemExistente.setDiscount(itemAtualizado.getDiscount());
                    itemExistente.setImage(itemAtualizado.getImage());
                    itemExistente.setMark(itemAtualizado.getMark());
                    itemExistente.setBarcode(itemAtualizado.getBarcode());
                    itemExistente.setQuantityStock(itemAtualizado.getQuantityStock());
                    itemExistente.setDimensions(itemAtualizado.getDimensions());

                    ItensProduct itemSalvo = repository.save(itemExistente);
                    return ResponseEntity.ok(itemSalvo);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
