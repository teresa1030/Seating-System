package com.example.seating.controller;

import com.example.seating.dto.EmployeeRequest;
import com.example.seating.model.Employee;
import com.example.seating.model.Seat;
import com.example.seating.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("/api/employees")
    public ResponseEntity<List<Employee>> allEmployees() {
        List<Employee> empList = seatService.getAllEmployees();

        if (empList != null) {
            return ResponseEntity.status(HttpStatus.OK).body(empList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/api/employees/{userId}")
    public ResponseEntity<Employee> updateSeat(@PathVariable Integer userId,
                                           @RequestBody EmployeeRequest empRequest) {

//        Vote vote = voteService.getVoteById(voteId);
//
//        if (vote == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }

        seatService.updateSeat(userId, empRequest);

        Employee updateEmployee = seatService.getEmployeeById(userId);

        return ResponseEntity.status(HttpStatus.OK).body(updateEmployee);
    }
}
