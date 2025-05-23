package com.estetify.backend.repository;

import com.estetify.backend.models.itens.ItensService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItensServiceRepository extends JpaRepository<ItensService, Long> {
}
