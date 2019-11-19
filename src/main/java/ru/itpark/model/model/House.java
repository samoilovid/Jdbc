package ru.itpark.model.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class House {
    private int id;
    private int price;
    private int rooms;
    private String district;
    private String underground;

}
