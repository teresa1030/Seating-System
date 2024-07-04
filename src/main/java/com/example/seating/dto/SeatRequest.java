package com.example.seating.dto;

public class SeatRequest {
    private Integer floorNo;
    private Integer seatNo;

    public Integer getFloorNo() {
        return floorNo;
    }
    public void setFloorNo(Integer floorNo) {
        this.floorNo = floorNo;
    }

    public Integer getSeatNo() {
        return seatNo;
    }
    public void setSeatNo(Integer seatNo) {
        this.seatNo = seatNo;
    }
}
