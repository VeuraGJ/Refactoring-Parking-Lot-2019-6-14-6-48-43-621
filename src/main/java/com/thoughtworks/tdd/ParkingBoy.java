package com.thoughtworks.tdd;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ParkingBoy implements Parkable, Fetchable {
    private List<ParkingLot> parkingLots;

    public ParkingBoy() {
        parkingLots = new ArrayList<>();
    }

    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }

    public void setParkingLots(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    @Override
    public Ticket parkCar(Car car) throws Exception {
        long parkingLothasEmptyPostionCount = parkingLots.stream()
                .filter(parkingLot -> parkingLot.getEmptyPosition() > 0)
                .count();
        if(parkingLothasEmptyPostionCount == 0){
            throw new Exception("Not enough position.");
        }
        return parkingLots.stream()
                .filter(parkingLot -> parkingLot.getEmptyPosition() > 0)
                .collect(Collectors.toList())
                .get(0)
                .parkCar(car);
    }
    @Override
    public Car fetchCar(Ticket ticket) throws Exception {
        for(int i=0 ;i<parkingLots.size();i++){
            try {
                return parkingLots.get(i).fetchCar(ticket);
            } catch (Exception e) {
                if(i < parkingLots.size()-1){
                    continue;
                }
                throw e;
            }
        }
        return null;
    }

    public void manageParkingLot(ParkingLot parkingLot) {
        parkingLots.add(parkingLot);
    }
}
