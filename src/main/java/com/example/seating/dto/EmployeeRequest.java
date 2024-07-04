package com.example.seating.dto;

public class EmployeeRequest {

    private String name;
    private String email;
    private Integer seatId;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEamil() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSeatId() {
        return seatId;
    }
    public void setSeatId(Integer seatId) {
        this.seatId = seatId;
    }
}
