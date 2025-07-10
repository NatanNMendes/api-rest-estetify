package com.estetify.backend.models.itens;

import com.estetify.backend.models.users.UsersCustomer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class PurchaseHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private UsersCustomer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private ItensProduct item;

    private LocalDateTime purchaseDate;

    // Construtores
    public PurchaseHistory() {}

    public PurchaseHistory(UsersCustomer customer, ItensProduct item) {
        this.customer = customer;
        this.item = item;
        this.purchaseDate = LocalDateTime.now();
    }
}