package com.estetify.backend.controller.itens;

import com.estetify.backend.models.itens.ItensProduct;
import com.estetify.backend.models.itens.ItensService;
import com.estetify.backend.repository.itens.ItensProductRepository;
import com.estetify.backend.repository.itens.ItensServiceRepository;
import com.estetify.backend.utils.ItensType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/service")
public class ItensServiceController  {

    @Autowired
    private ItensProductRepository productRepository;

    private final ItensServiceRepository repository;

    public ItensServiceController(ItensServiceRepository repository) {
        this.repository = repository;
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<ItensService>> getAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ItensService> getById(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST
    @PostMapping
    public ResponseEntity<ItensService> create(@RequestBody ItensServiceRequest request) {
        ItensService service = new ItensService();
        service.setName(request.getName());
        service.setPrice(request.getPrice());
        service.setDiscount(request.getDiscount());
        service.setImage(request.getImage());
        service.setItensType(ItensType.SERVICE);
        service.setCreatedAt(LocalDateTime.now());
        service.setAvailabilityDate(request.getAvailabilityDate());
        service.setReservedDate(request.getReservedDate());

        // Corrigido o processamento dos materiais
        List<ItensProduct> materials = request.getMaterialsUsed().stream()
                .map(material -> productRepository.findById(material.getId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        service.setMaterialsUsed(materials);

        ItensService savedItem = repository.save(service);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedItem);
    }

    @Getter @Setter
    public static class ItensServiceRequest {
        private String name;
        private Double price;
        private Double discount;
        private String image;
        private LocalDateTime availabilityDate;
        private LocalDateTime reservedDate;
        private List<MaterialDTO> materialsUsed;

        @Getter
        @Setter
        public static class MaterialDTO {
            private Long id;
        }
    }
}

