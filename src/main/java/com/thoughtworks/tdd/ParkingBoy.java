package com.thoughtworks.tdd;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        long parkingLothasEmptyPostionCount = getParkingLothasEmptyPostionNumber();
        if(parkingLothasEmptyPostionCount == 0){
            throw new Exception("Not enough position.");
        }
        return parkingLots.stream()
                .filter(parkingLot -> parkingLot.getEmptyPosition() > 0)
                .collect(Collectors.toList())
                .get(0)
                .parkCar(car);
    }

    protected long getParkingLothasEmptyPostionNumber() {
        return parkingLots.stream()
                    .filter(parkingLot -> parkingLot.getEmptyPosition() > 0)
                    .count();
    }

    @Override
    public Car fetchCar(Ticket ticket) throws Exception {
        if(ticket == null){
            throw new Exception("Please provide your parking ticket.");
        }
        List<ParkingLot> parkingLotContainsCar = parkingLots.stream()
                .filter(parkingLot -> parkingLot.getEmptyPosition() < parkingLot.getCapacity())
                .filter(parkingLot -> parkingLot.isCarInParkingLot(ticket))
                .collect(Collectors.toList());
        if(parkingLotContainsCar.size() == 0){
            throw new Exception("Unrecognized parking ticket.");
        }
        return  parkingLotContainsCar.get(0).fetchCar(ticket);

    }

    public void manageParkingLot(ParkingLot parkingLot) {
        parkingLots.add(parkingLot);
    }
}
