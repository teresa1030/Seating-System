package com.example.seating.service.impl;

import com.example.seating.dao.SeatDao;
import com.example.seating.dto.EmployeeRequest;
import com.example.seating.dto.SeatRequest;
import com.example.seating.model.Employee;
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
    @Override
    public List<Employee> getAllEmployees() {
        return seatDao.getAllEmployees();
    }
    @Override
    public Employee getEmployeeById(Integer userId) {
        return seatDao.getEmployeeById(userId);
    }
    @Override
    public Seat getSeatById(Integer seatId) {
        return seatDao.getSeatById(seatId);
    }

    @Override
    public Integer createSeat(SeatRequest seatRequest) {
        return seatDao.createSeat(seatRequest);
    }
    @Override
    public void createEmployee(EmployeeRequest empRequest) {
        seatDao.createEmployee(empRequest);
    }

    @Override
    public void updateSeat(Integer userId, EmployeeRequest empRequest) {
        seatDao.updateSeat(userId, empRequest);
    }
}
