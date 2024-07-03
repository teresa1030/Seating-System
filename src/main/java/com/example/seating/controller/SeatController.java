package com.example.seating.controller;

import com.example.seating.model.Seat;
import com.example.seating.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SeatController {
    @Autowired
    private SeatService seatService;

    @RequestMapping("/api/seats")
    public ResponseEntity<List<Seat>> allSeats() {
        List<Seat> seatList = seatService.getAllSeats();

        if (seatList != null) {
            return ResponseEntity.status(HttpStatus.OK).body(seatList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
