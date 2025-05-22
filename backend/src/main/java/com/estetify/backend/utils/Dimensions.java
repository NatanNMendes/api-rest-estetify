package com.estetify.backend.utils;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Dimensions implements Serializable {

    private double width;
    private double height;
    private double depth;

    // Construtor padrão (obrigatório para JPA)
    public Dimensions() {
    }

    // Construtor com parâmetros (opcional)
    public Dimensions(double width, double height, double depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;
    }

    // Getters e Setters
    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getDepth() {
        return depth;
    }

    public void setDepth(double depth) {
        this.depth = depth;
    }
}

