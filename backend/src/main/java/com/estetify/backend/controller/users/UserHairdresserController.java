package com.estetify.backend.controller.users;

import com.estetify.backend.models.itens.ItensProduct;
import com.estetify.backend.models.itens.ItensService;
import com.estetify.backend.models.users.UserHairdresser;
import com.estetify.backend.repository.itens.ItensProductRepository;
import com.estetify.backend.repository.itens.ItensServiceRepository;
import com.estetify.backend.repository.users.UserHairdresserRepository;
import com.estetify.backend.utils.ItensType;
import com.estetify.backend.utils.ResourceNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/hairdressers")
@RequiredArgsConstructor
public class UserHairdresserController {

    private final UserHairdresserRepository hairdresserRepository;
    private final ItensProductRepository productRepository;
    private final ItensServiceRepository serviceRepository;

    @PostMapping("/{hairdresserId}/products")
    public ResponseEntity<?> addProduct(
            @PathVariable Long hairdresserId,
            @RequestBody ProductRequest request) {

        UserHairdresser hairdresser = hairdresserRepository.findById(hairdresserId)
                .orElseThrow(() -> new ResourceNotFoundException("Cabeleireiro não encontrado"));

        ItensProduct product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));

        if (!hairdresser.getProductsAvailable().contains(product)) {
            hairdresser.getProductsAvailable().add(product);
            hairdresserRepository.save(hairdresser);
        }

        return ResponseEntity.ok().build();
    }

    @PostMapping("/{hairdresserId}/services")
    public ResponseEntity<?> addService(
            @PathVariable Long hairdresserId,
            @RequestBody ServiceRequest request) {

        UserHairdresser hairdresser = hairdresserRepository.findById(hairdresserId)
                .orElseThrow(() -> new ResourceNotFoundException("Cabeleireiro não encontrado"));

        ItensService service = new ItensService();
        service.setName(request.getName());
        service.setPrice(request.getPrice());
        service.setDiscount(request.getDiscount());
        service.setImage(request.getImage());
        service.setItensType(ItensType.SERVICE);
        service.setCreatedAt(LocalDateTime.now());
        service.setAvailabilityDate(request.getAvailabilityDate());
        service.setReservedDate(request.getReservedDate());

        List<ItensProduct> materials = request.getMaterialsUsed().stream()
                .map(m -> productRepository.findById(m.getId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        service.setMaterialsUsed(materials);

        // Salva o serviço primeiro para gerar ID
        ItensService savedService = serviceRepository.save(service);
        hairdresser.getServicesAvailable().add(savedService);
        hairdresserRepository.save(hairdresser);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{hairdresserId}/products")
    public ResponseEntity<List<ItensProduct>> getProducts(@PathVariable Long hairdresserId) {
        UserHairdresser hairdresser = hairdresserRepository.findById(hairdresserId)
                .orElseThrow(() -> new ResourceNotFoundException("Cabeleireiro não encontrado"));

        return ResponseEntity.ok(hairdresser.getProductsAvailable());
    }

    @GetMapping("/{hairdresserId}/services")
    public ResponseEntity<List<ItensService>> getServices(@PathVariable Long hairdresserId) {
        UserHairdresser hairdresser = hairdresserRepository.findById(hairdresserId)
                .orElseThrow(() -> new ResourceNotFoundException("Cabeleireiro não encontrado"));

        return ResponseEntity.ok(hairdresser.getServicesAvailable());
    }

    @Getter
    @Setter
    public static class ProductRequest {
        private Long productId;
    }

    @Getter
    @Setter
    public static class ServiceRequest {
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