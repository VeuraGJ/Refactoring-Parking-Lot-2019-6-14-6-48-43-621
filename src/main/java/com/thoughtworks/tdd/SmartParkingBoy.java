package com.thoughtworks.tdd;

import java.util.Comparator;
import java.util.List;

public class SmartParkingBoy extends ParkingBoy {
    @Override
    public Ticket parkCar(Car car) throws Exception {
        long parkingLothasEmptyPostionNumber = super.getParkingLothasEmptyPostionNumber();
        if(parkingLothasEmptyPostionNumber == 0){
            throw new Exception("Not enough position.");
        }
        return super.getParkingLots().stream()
                .filter(parkingLot -> parkingLot.getEmptyPosition() > 0)
                .max(Comparator.comparingInt(ParkingLot::getEmptyPosition))
                .get()
                .parkCar(car);
    }
}
