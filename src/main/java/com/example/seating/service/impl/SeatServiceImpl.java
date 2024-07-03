package com.example.seating.service.impl;

import com.example.seating.dao.SeatDao;
import com.example.seating.model.Seat;
import com.example.seating.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class SeatServiceImpl implements SeatService {
    @Autowired
    private SeatDao seatDao;

    @Override
    public List<Seat> getAllSeats() {
        return seatDao.getAllSeats();
    }
}
