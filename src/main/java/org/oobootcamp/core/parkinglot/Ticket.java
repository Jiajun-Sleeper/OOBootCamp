package org.oobootcamp.core.parkinglot;

public class Ticket {
    private final String carNo;

    public Ticket(String carNo) {
        this.carNo = carNo;
    }

    public String getCarNo() {
        return carNo;
    }
}
