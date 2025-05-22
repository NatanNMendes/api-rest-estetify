package com.estetify.backend.utils;

import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class OperatingHours {
    private Week week;
    private DateTimeFormatter hours;

    public OperatingHours(Week week, DateTimeFormatter hours) {
        this.week = week;
        this.hours = hours;
    }
}
