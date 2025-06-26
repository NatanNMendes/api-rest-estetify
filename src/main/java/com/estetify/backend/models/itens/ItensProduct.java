package com.estetify.backend.models.itens;

import com.estetify.backend.utils.Dimensions;
import com.estetify.backend.utils.ItensType;


import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;

@Entity
public class ItensProduct extends Itens {
    private String mark;
    private String barcode;
    private Integer quantityStock;

    @Embedded
    private Dimensions dimensions;

    public ItensProduct(){}

    public ItensProduct(Long id, String name, ItensType itensType, Double price, Double discount, LocalDateTime createdAt, String image, String mark, String barcode, Integer quantityStock, Dimensions dimensions) {
        super(id, name, itensType, price, discount, createdAt, image);
        this.mark = mark;
        this.barcode = barcode;
        this.quantityStock = quantityStock;
        this.dimensions = dimensions;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public Integer getQuantityStock() {
        return quantityStock;
    }

    public void setQuantityStock(Integer quantityStock) {
        this.quantityStock = quantityStock;
    }

    public Dimensions getDimensions() {
        return dimensions;
    }

    public void setDimensions(Dimensions dimensions) {
        this.dimensions = dimensions;
    }
}
