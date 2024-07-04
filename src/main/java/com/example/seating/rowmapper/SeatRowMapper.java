package com.example.seating.rowmapper;

import com.example.seating.model.Seat;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SeatRowMapper implements RowMapper<Seat> {

    @Override
    public Seat mapRow(ResultSet resultSet, int i) throws SQLException {

        Seat seat = new Seat();

        seat.setSeatId(resultSet.getInt("floor_seat_seq"));
        seat.setFloorNo(resultSet.getInt("floor_no"));
        seat.setSeatNo(resultSet.getInt("seat_no"));
//        System.out.println("Mapping seatId: " + resultSet.getInt("floor_seat_seq"));
//        System.out.println("Mapping floorNo: " + resultSet.getInt("floor_no"));
//        System.out.println("Mapping seatNo: " + resultSet.getInt("seat_no"));
        return seat;
    }

}
