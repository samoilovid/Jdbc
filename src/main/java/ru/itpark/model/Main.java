package ru.itpark.model;

import ru.itpark.model.model.House;
import ru.itpark.model.service.HouseServices;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        HouseServices services = new HouseServices();
        List<House> houses;
        houses = services.getAll();
        System.out.println(houses);


    }
}

