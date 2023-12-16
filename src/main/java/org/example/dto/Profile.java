package org.example.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Setter
@Getter
@ToString
public class Profile {
    private Integer id;
    private String name;
    private String surname;
    private String phone;
    private LocalDate createdData;
    private boolean visible;
}
