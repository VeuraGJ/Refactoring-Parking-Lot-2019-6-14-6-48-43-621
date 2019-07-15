package com.thoughtworks.tdd;

import java.util.List;

public class SmartParkingBoy extends ParkingBoy {
    @Override
    public Ticket parkCar(Car car) throws Exception {
        int maxEmptyPositionsNumber = 0;
        int parkingLotIndex = 0;
        List<ParkingLot> parkingLotList = super.getParkingLots();
        for(int i=0;i<parkingLotList.size();i++){
            if(parkingLotList.get(i).getEmptyPosition()> maxEmptyPositionsNumber){
                maxEmptyPositionsNumber = parkingLotList.get(i).getEmptyPosition();
                parkingLotIndex = i;
            }
        }
        return parkingLotList.get(parkingLotIndex).parkCar(car);
    }
}
