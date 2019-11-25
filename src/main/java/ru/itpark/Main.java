package ru.itpark;

import ru.itpark.model.House;
import ru.itpark.service.HouseServices;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        HouseServices services = new HouseServices();
        List<House> houses;
        houses = services.getAll();
        System.out.println(houses);

        System.out.println("----------");

        services.insert(1_000_000, 1, "Московский", "Яшлек");
        houses = services.getAll();
        System.out.println(houses);
    }
}

