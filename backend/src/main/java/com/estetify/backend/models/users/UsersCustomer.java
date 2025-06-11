package com.estetify.backend.models.users;

import com.estetify.backend.models.itens.Itens;
import com.estetify.backend.models.itens.ItensProduct;
import com.estetify.backend.utils.Gender;
import com.estetify.backend.utils.Sexuality;
import com.estetify.backend.utils.UsersType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;


@Entity
@DiscriminatorValue("CUSTOMER")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UsersCustomer extends Users {

    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private List<ItensProduct> shoppingCart = new ArrayList<>();

    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_purchased_items",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<ItensProduct> purchasedItems = new ArrayList<>();

    @Builder.Default
    @ElementCollection
    @CollectionTable(name = "user_purchase_history", joinColumns = @JoinColumn(name = "user_id"))
    private List<Itens> purchaseHistory = new ArrayList<>();

}