package org.oobootcamp.core.parkinglot;

public class Car {
    public String getCarNo() {
        return carNo;
    }

    private final String carNo;

    public Car(String carNo) {
        this.carNo = carNo;
    }
}
