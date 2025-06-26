package com.estetify.backend.utils;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class Dimensions implements Serializable {

    private double width;
    private double height;
    private double depth;

    public Dimensions() {
    }


    public Dimensions(double width, double height, double depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;
    }
}

