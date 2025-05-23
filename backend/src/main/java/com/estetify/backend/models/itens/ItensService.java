package com.estetify.backend.models.itens;

import com.estetify.backend.utils.ItensType;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class ItensService extends Itens{
    private LocalDateTime availabilityDate;
    private LocalDateTime reservedDate;


    @ManyToMany(fetch = FetchType.EAGER) // Adicionado FetchType.EAGER
    @JoinTable(
            name = "service_products",
            joinColumns = @JoinColumn(name = "service_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    @JsonInclude(JsonInclude.Include.NON_NULL)
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
