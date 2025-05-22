package com.estetify.backend.repository;

import com.estetify.backend.models.itens.ItensProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItensProductRepository extends JpaRepository<ItensProduct, Long> {
}
