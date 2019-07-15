package com.thoughtworks.tdd;

import java.util.Comparator;
import java.util.List;

public class SuperSmartParkingBoy extends ParkingBoy {
    @Override
    public Ticket parkCar(Car car) throws Exception {
        List<ParkingLot> parkingLots= super.getParkingLots();
        long parkingLothasEmptyPostionCount = parkingLots.stream()
                .filter(parkingLot -> parkingLot.getEmptyPosition() > 0)
                .count();
        if(parkingLothasEmptyPostionCount == 0){
            throw new Exception("Not enough position.");
        }
        return parkingLots.stream()
                .filter(parkingLot -> parkingLot.getEmptyPosition() > 0)
                .max(Comparator.comparingDouble(ParkingLot::getAvailablePositionRate))
                .get()
                .parkCar(car);
    }
}
