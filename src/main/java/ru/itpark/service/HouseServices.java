package ru.itpark.service;

import ru.itpark.model.House;
import ru.itpark.util.JdbcTemplate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HouseServices {
    public HouseServices() {
    }

    public List<House> getAll() throws SQLException {
        return JdbcTemplate.executeQuery(
                "jdbc:sqlite:db.sqlite",
                "SELECT id, price, rooms, district, underground FROM house",
                resultSet -> new House(
                        resultSet.getInt("id"),
                        resultSet.getInt("price"),
                        resultSet.getInt("rooms"),
                        resultSet.getString("district"),
                        resultSet.getString("underground")
                )
        );
    }

    public void insert(int price, int rooms, String district, String underground) throws SQLException {
        JdbcTemplate.executeUpdate("jdbc:sqlite:db.sqlite",
                "INSERT INTO houses(price, rooms, district, underground) values(?,?,?,?)",
                statement -> {
                    statement.setInt(1, price);
                    statement.setInt(2, rooms);
                    statement.setString(3, district);
                    statement.setString(4, underground);
                });
    }

    public void update(int id, int price, int rooms, String district, String underground) throws SQLException {
        JdbcTemplate.executeUpdate("jdbc:sqlite:db.sqlite",
                "UPDATE houses SET price = ?, rooms = ?, district = ?, underground = ? where id = ?",
                statement -> {
                    statement.setInt(1, price);
                    statement.setInt(2, rooms);
                    statement.setString(3, district);
                    statement.setString(4, underground);
                    statement.setInt(5, id);
                });
    }

    public void delete(int id) throws SQLException {
        JdbcTemplate.executeUpdate("jdbc:sqlite:db.sqlite",
                "DELETE FROM houses where id = ?",
                statement -> {
                    statement.setInt(1, id);
                });
    }

    public List<House> sortByPrice() throws SQLException {
        List<House> houses = getAll();
        houses.sort((o1, o2) -> o1.getPrice() - o2.getPrice());
        return houses;
    }

    public List<House> sortByUnderground() throws SQLException {
        List<House> houses = getAll();
        houses.sort((o1, o2) -> o1.getUnderground().compareTo(o2.getUnderground()));
        return houses;
    }

    public List<House> searchByPrice(int min, int max) throws SQLException {
        List<House> houses = sortByPrice();
        List<House> result = new ArrayList<>();
        for (House house : houses) {
            if (house.getPrice() >= min && house.getPrice() <= max) {
                result.add(house);
            }
        }
        return result;
    }

    public List<House> searchByUnderground(String metro) throws SQLException {
        List<House> houses = getAll();
        List<House> result = new ArrayList<>();
        for (House house : houses) {
            if (house.getUnderground().contains(metro)) {
                result.add(house);
            }
        }
        return result;
    }

}