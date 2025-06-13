package com.estetify.backend.models.users;

import com.estetify.backend.models.itens.ItensProduct;
import com.estetify.backend.models.itens.ItensService;
import lombok.*;
import jakarta.persistence.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("HAIRDRESSER")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class UserHairdresser extends Users {

    @Builder.Default
    private Double rating = 0.0;

    private String instagramProfile;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "hairdresser_id")
    @Builder.Default
    private List<ItensProduct> productsAvailable = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "hairdresser_id")
    @Builder.Default
    private List<ItensService> servicesAvailable = new ArrayList<>();

    public void addProduct(ItensProduct product) {
        if (product != null && !productsAvailable.contains(product)) {
            productsAvailable.add(product);
        }
    }

    public void addService(ItensService service) {
        if (service != null && !servicesAvailable.contains(service)) {
            servicesAvailable.add(service);
        }
    }
}