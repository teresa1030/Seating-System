package com.example.seating.controller;

import com.example.seating.dto.EmployeeRequest;
import com.example.seating.dto.SeatRequest;
import com.example.seating.model.Employee;
import com.example.seating.model.Seat;
import com.example.seating.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @PostMapping("/api/seats")
    public ResponseEntity<Seat> createSeat(@RequestBody SeatRequest seatRequest) {
        Integer seatId = seatService.createSeat(seatRequest);
        Seat seat = seatService.getSeatById(seatId);

        return ResponseEntity.status(HttpStatus.CREATED).body(seat);
    }

    @PostMapping("/api/employees")
    public ResponseEntity<Employee> createEmployee(@RequestBody EmployeeRequest empRequest) {
        Employee employee = seatService.getEmployeeById(empRequest.getUserId());
        if (employee != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        seatService.createEmployee(empRequest);
        Employee newEmployee = seatService.getEmployeeById(empRequest.getUserId());

        return ResponseEntity.status(HttpStatus.CREATED).body(newEmployee);
    }

    @PutMapping("/api/employees/{userId}")
    public ResponseEntity<Employee> updateSeat(@PathVariable Integer userId,
                                           @RequestBody EmployeeRequest empRequest) {

        seatService.updateSeat(userId, empRequest);

        Employee updateEmployee = seatService.getEmployeeById(userId);

        return ResponseEntity.status(HttpStatus.OK).body(updateEmployee);
    }
}
