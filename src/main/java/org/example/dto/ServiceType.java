package org.example.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.enums.Status;

import java.time.LocalDate;
@Setter
@Getter
@ToString
public class ServiceType {
    private Integer id;
    private String name;
    private double price;
    private double personTage;
    private LocalDate createdData;
    private Status status;
}
