package com.example.seating.dao.impl;

import com.example.seating.dao.SeatDao;
import com.example.seating.dto.EmployeeRequest;
import com.example.seating.dto.SeatRequest;
import com.example.seating.model.Employee;
import com.example.seating.rowmapper.EmployeeRowMapper;
import com.example.seating.rowmapper.SeatRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import com.example.seating.model.Seat;

import java.math.BigInteger;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SeatDaoImpl implements SeatDao{
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Seat> getAllSeats() {

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

        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(namedParameterJdbcTemplate.getJdbcTemplate())
                .withProcedureName("GetEmployeeById")
                .returningResultSet("employee", new EmployeeRowMapper());

        SqlParameterSource paramMap = new MapSqlParameterSource()
                .addValue("p_userId", userId);

        Map<String, Object> result = jdbcCall.execute(paramMap);
        List<Employee> empList = (List<Employee>) result.get("employee");

        if (empList.size() > 0) {
            return empList.get(0);
        } else {
            return null;
        }
    }
    public Seat getSeatById(Integer seatId) {

        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(namedParameterJdbcTemplate.getJdbcTemplate())
                .withProcedureName("GetSeatById")
                .returningResultSet("seat", new SeatRowMapper());

        SqlParameterSource paramMap = new MapSqlParameterSource()
                .addValue("p_seatId", seatId);

        Map<String, Object> result = jdbcCall.execute(paramMap);
        List<Seat> seatList = (List<Seat>) result.get("seat");

        if (seatList.size() > 0) {
            return seatList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Integer createSeat(SeatRequest seatRequest) {
//        String sql = "INSERT INTO seatInfo(floor_no, seat_no) " +
//                "VALUES (:floorNo, :SeatNo)";
//        Map<String, Object> map = new HashMap<>();
//        map.put("floorNo", seatRequest.getFloorNo());
//        map.put("SeatNo", seatRequest.getSeatNo());
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);
//        int seatId = keyHolder.getKey().intValue();
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(namedParameterJdbcTemplate.getJdbcTemplate())
                .withProcedureName("CreateSeat")
                .declareParameters(
                        new SqlParameter("p_floorNo", Types.INTEGER),
                        new SqlParameter("p_seatNo", Types.INTEGER),
                        new SqlOutParameter("floor_seat_seq", Types.BIGINT)
                );

        MapSqlParameterSource paramMap = new MapSqlParameterSource()
                .addValue("p_floorNo", seatRequest.getFloorNo())
                .addValue("p_seatNo", seatRequest.getSeatNo());

        Map<String, Object> result = jdbcCall.execute(paramMap);
        System.out.println("Result from stored procedure: " + result);

        List<Map<String, Object>> resultList = (List<Map<String, Object>>) result.get("#result-set-1");
        if (resultList != null && !resultList.isEmpty()) {
            Map<String, Object> resultMap = resultList.get(0); // Assuming only one row is returned
            BigInteger seatIdBigInteger = (BigInteger) resultMap.get("floor_seat_seq");
            Integer seatId = seatIdBigInteger.intValue();
            System.out.println("Generated seatId: " + seatId);
            return seatId;
        } else {
            throw new RuntimeException("Failed to retrieve generated seatId from stored procedure");
        }
    }

    @Override
    public void createEmployee(EmployeeRequest empRequest) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(namedParameterJdbcTemplate.getJdbcTemplate())
                .withProcedureName("CreateEmployee")
                .declareParameters(
                        new SqlParameter("p_userId", Types.INTEGER),
                        new SqlParameter("p_name", Types.VARCHAR),
                        new SqlParameter("p_email", Types.VARCHAR),
                        new SqlParameter("p_seatId", Types.INTEGER)
                );

        MapSqlParameterSource paramMap = new MapSqlParameterSource()
                .addValue("p_userId", empRequest.getUserId())
                .addValue("p_name", empRequest.getName())
                .addValue("p_email", empRequest.getEamil())
                .addValue("p_seatId", empRequest.getSeatId());

        Map<String, Object> result = jdbcCall.execute(paramMap);
        System.out.println("Result from stored procedure: " + result);

    }

    @Override
    public void updateSeat(Integer userId, EmployeeRequest empRequest) {

        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(namedParameterJdbcTemplate.getJdbcTemplate())
                .withProcedureName("UpdateSeat");

        SqlParameterSource paramMap = new MapSqlParameterSource()
                .addValue("p_userId", userId)
                .addValue("p_seatId", empRequest.getSeatId());

        Map<String, Object> result = jdbcCall.execute(paramMap);
    }
}
