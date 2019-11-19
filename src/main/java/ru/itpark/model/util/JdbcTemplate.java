package ru.itpark.model.util;

import ru.itpark.model.model.House;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class JdbcTemplate {
    private JdbcTemplate(){

    }
    public static List<House> executeQuery(String url, String sql, RowMapper mapper)throws SQLException {
        try(
                Connection connection = DriverManager.getConnection(url);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                ) {
            List<House> result = new LinkedList<>();
            while (resultSet.next()) {
                result.add(mapper.map(resultSet));
            }
            return result;
        }
    }
}
