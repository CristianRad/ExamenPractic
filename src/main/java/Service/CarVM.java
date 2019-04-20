package Service;

import Domain.Car;

public class CarVM {

    public Car car;
    public int days;

    public String id;
    public String model;

    public CarVM(Car car, int days) {
        this.car = car;
        this.days = days;
    }

    public int getDays() {
        return days;
    }

    public String getId() {
        return car.getId();
    }

    public String getModel() {
        return car.getModel();
    }

}
