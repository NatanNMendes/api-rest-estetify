package com.estetify.backend.models.itens;


import com.estetify.backend.models.users.UsersCustomer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class PurchaseHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private UsersCustomer customer;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Itens item;

    private LocalDateTime purchaseDate;

}
