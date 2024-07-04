package com.example.seating.rowmapper;

import com.example.seating.model.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper<Employee> {
    public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
        Employee user = new Employee();

        user.setUserId(resultSet.getInt("emp_id"));
        user.setName(resultSet.getString("name"));
        user.setEmail(resultSet.getString("email"));
        user.setSeatId(resultSet.getInt("seat_id"));

        return user;
    }
}
