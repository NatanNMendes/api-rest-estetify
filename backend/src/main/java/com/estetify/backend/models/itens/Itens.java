package com.estetify.backend.models.itens;

import com.estetify.backend.utils.ItensType;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "item_type")
public abstract class Itens {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Enumerated(EnumType.STRING)
    private ItensType itensType;

    private Double price;
    private Double discount;
    private LocalDateTime createdAt;
    private String image;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<PurchaseHistory> purchaseHistory = new ArrayList<>();

    public Itens(){}

    public Itens(Long id, String name, ItensType itensType, Double price, Double discount, LocalDateTime createdAt, String image) {
        this.id = id;
        this.name = name;
        this.itensType = itensType;
        this.price = price;
        this.discount = discount;
        this.createdAt = createdAt;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItensType getItensType() {
        return itensType;
    }

    public void setItensType(ItensType itensType) {
        this.itensType = itensType;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
