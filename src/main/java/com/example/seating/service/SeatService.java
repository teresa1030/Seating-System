package com.example.seating.service;

import com.example.seating.dto.EmployeeRequest;
import com.example.seating.dto.SeatRequest;
import com.example.seating.model.Employee;
import com.example.seating.model.Seat;

import java.util.List;

public interface SeatService {

    List<Seat> getAllSeats();
    List<Employee> getAllEmployees();

    Employee getEmployeeById(Integer userId);
    Seat getSeatById(Integer seatId);

    Integer createSeat(SeatRequest seatRequest);
    void createEmployee(EmployeeRequest empRequest);

    void updateSeat(Integer userId, EmployeeRequest empRequest);

}
