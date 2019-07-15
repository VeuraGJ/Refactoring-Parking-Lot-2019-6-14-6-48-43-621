package com.thoughtworks.tdd;

import java.util.List;

public class SuperSmartParkingBoy extends ParkingBoy {
    @Override
    public Ticket parkCar(Car car) throws Exception {
        double maxAvailablePositionRate = -1d;
        int parkingLotIndex = 0;
        List<ParkingLot> parkingLotList = super.getParkingLots();
        for(int i=0;i<parkingLotList.size();i++){
            if(parkingLotList.get(i).getAvailablePositionRate()> maxAvailablePositionRate){
                maxAvailablePositionRate = parkingLotList.get(i).getAvailablePositionRate();
                parkingLotIndex = i;
            }
        }
        return parkingLotList.get(parkingLotIndex).parkCar(car);
    }
}
