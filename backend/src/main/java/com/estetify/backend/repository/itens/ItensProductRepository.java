package com.estetify.backend.repository.itens;

import com.estetify.backend.models.itens.ItensProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItensProductRepository extends JpaRepository<ItensProduct, Long> {
}
