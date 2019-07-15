package com.thoughtworks.tdd;

public class Customer  {
    private Parkable parkable;
    private Fetchable fetchable;
    private Car myCar;
    private Ticket parkingTicket;

    public void setParkable(Parkable parkable) {
        this.parkable = parkable;
    }

    public void setFetchCar(Fetchable fetchable) {
        this.fetchable = fetchable;
    }

    public Car getMyCar() {
        return myCar;
    }

    public void setMyCar(Car myCar) {
        this.myCar = myCar;
    }

    public Ticket getParkingTicket() {
        return parkingTicket;
    }

    public void setParkingTicket(Ticket parkingTicket) {
        this.parkingTicket = parkingTicket;
    }

    public String fetchMyCar(){
        try {
            Car car = fetchable.fetchCar(parkingTicket);
            setMyCar(car);
        } catch (Exception e) {
            return e.getMessage();
        }
        return null;
    }

    public String parkMyCar() {
        try {
            Ticket ticket=parkable.parkCar(myCar);
            setParkingTicket(ticket);
        } catch (Exception e) {
            return e.getMessage();
        }
        return null;
    }
}
