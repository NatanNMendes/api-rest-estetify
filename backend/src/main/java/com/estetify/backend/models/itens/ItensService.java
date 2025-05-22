package com.estetify.backend.models.itens;

import com.estetify.backend.utils.ItensType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class ItensService extends Itens{
    private LocalDateTime availabilityDate;
    private LocalDateTime reservedDate;
    @ManyToMany
    @JoinTable(
            name = "service_products",
            joinColumns = @JoinColumn(name = "service_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<ItensProduct> materialsUsed;

    public ItensService() {
    }

    public ItensService(Long id, String name, ItensType itensType, Double price, Double discount, LocalDateTime createdAt, String image, LocalDateTime availabilityDate, LocalDateTime reservedDate, List<ItensProduct> materialsUsed) {
        super(id, name, itensType, price, discount, createdAt, image);
        this.availabilityDate = availabilityDate;
        this.reservedDate = reservedDate;
        this.materialsUsed = materialsUsed;
    }
}
