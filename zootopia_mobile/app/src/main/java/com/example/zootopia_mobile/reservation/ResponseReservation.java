package com.example.zootopia_mobile.reservation;


import java.util.List;
public class ResponseReservation {
    private List<Reservation> data;

    public List<Reservation> getUserReservations() { return data; }

    public void setData(List<Reservation> data) {
        this.data = data;
    }
}
