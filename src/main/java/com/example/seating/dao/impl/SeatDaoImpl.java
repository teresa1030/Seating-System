package com.example.seating.dao.impl;

import com.example.seating.dao.SeatDao;
import com.example.seating.dto.EmployeeRequest;
import com.example.seating.model.Employee;
import com.example.seating.rowmapper.EmployeeRowMapper;
import com.example.seating.rowmapper.SeatRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;
import com.example.seating.model.Seat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SeatDaoImpl implements SeatDao{
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Seat> getAllSeats() {
//        String sql = "SELECT *" +
//                "FROM seatInfo ";
//        Map<String, Object> map = new HashMap<>();
//        List<Seat> seatList = namedParameterJdbcTemplate.query(sql, map, new SeatRowMapper());

        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(namedParameterJdbcTemplate.getJdbcTemplate())
                .withProcedureName("GetAllSeats")
                .returningResultSet("seats", new SeatRowMapper());

        SqlParameterSource paramMap = new MapSqlParameterSource();
        Map<String, Object> result = jdbcCall.execute(paramMap);
        List<Seat> seatList = (List<Seat>) result.get("seats");
        if (seatList.size() > 0) {
            return seatList;
        } else {
            return null;
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
//        String sql = "SELECT *" +
//                "FROM employeeInfo ";
//
//        Map<String, Object> map = new HashMap<>();
//
//        List<Employee> empList = namedParameterJdbcTemplate.query(sql, map, new EmployeeRowMapper());

        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(namedParameterJdbcTemplate.getJdbcTemplate())
                .withProcedureName("GetAllEmployees")
                .returningResultSet("employees", new EmployeeRowMapper());

        SqlParameterSource paramMap = new MapSqlParameterSource();
        Map<String, Object> result = jdbcCall.execute(paramMap);
        List<Employee> empList = (List<Employee>) result.get("employees");

        if (empList.size() > 0) {
            return empList;
        } else {
            return null;
        }
    }

    @Override
    public Employee getEmployeeById(Integer userId) {
        String sql = "SELECT *" +
                "FROM employeeInfo WHERE emp_id = :userId";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);

        List<Employee> empList = namedParameterJdbcTemplate.query(sql, map, new EmployeeRowMapper());

        if (empList.size() > 0) {
            return empList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public void updateSeat(Integer userId, EmployeeRequest empRequest) {
        String sql = "UPDATE employeeInfo SET seat_id = :seatId " +
                " WHERE emp_id = :userId";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("seatId", empRequest.getSeatId());
        System.out.println(map);

        namedParameterJdbcTemplate.update(sql, map);
    }
}
