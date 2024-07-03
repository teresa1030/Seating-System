package com.example.seating.dao.impl;

import com.example.seating.dao.SeatDao;
import com.example.seating.rowmapper.SeatRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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
        String sql = "SELECT *" +
                "FROM seatInfo ";
//        String sql = "SELECT vote_id, title, image_url, content" +
//                "FROM vote "; 不知道為什麼不行vote_id也符合命名規範

        Map<String, Object> map = new HashMap<>();

        List<Seat> seatList = namedParameterJdbcTemplate.query(sql, map, new SeatRowMapper());

        if (seatList.size() > 0) {
            return seatList;
        } else {
            return null;
        }
    }
}
