package org.example.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Setter
@Getter
@ToString
public class Car {
    /* nomeri, modely, rangi va ro'yhatdan o'tgan sanasi*/
    private Integer id;
    private String number;
    private String model;
    private String color;
    private LocalDate createdData;
    private boolean visible;
}
