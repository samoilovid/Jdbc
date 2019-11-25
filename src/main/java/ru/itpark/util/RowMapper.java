package ru.itpark.util;

import ru.itpark.model.House;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface RowMapper {
  House map(ResultSet resultSet) throws SQLException;
}
