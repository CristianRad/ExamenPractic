package Service;

import Domain.Car;
import Domain.Rent;
import Repository.IRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarService {

    private IRepository<Car> carRepository;
    private IRepository<Rent> rentRepository;

    /**
     * Instantiates a service for cars.
     * @param carRepository is the car repository used.
     * @param rentRepository is the rent repository used.
     */

    public CarService(IRepository<Car> carRepository, IRepository<Rent> rentRepository) {
        this.carRepository = carRepository;
        this.rentRepository = rentRepository;
    }

    /**
     * Adds a car.
     * @param id is the ID of the car.
     * @param model is the car model.
     * @param initialKilometres is the current usage of the car.
     * @param price is the rent price per day for the car.
     */

    public void addCar(String id, String model, int initialKilometres, Double price) {
        Car car = new Car(id, model, initialKilometres, price);
        carRepository.insert(car);
    }

    /**
     * @return a list of all cars.
     */

    public List<Car> getAllCars() {
        return carRepository.getAll();
    }

    public IRepository<Car> getCarRepository() { return carRepository; }

    /**
     * @return a list of cars sorted (descending) by the number of days they've been rented for.
     */

    public List<CarVM> getOrderedByNumberOfDays() {
        Map<String, Integer> rentDays = new HashMap<>();
        for (Rent rent : rentRepository.getAll()) {
            String carId = rent.getCarId();
            if (rentDays.containsKey(carId))
                rentDays.put(carId, rentDays.get(carId) + rent.getDays());
            else
                rentDays.put(carId, rent.getDays());
        }

        List<CarVM> results = new ArrayList<>();
        for (String carId : rentDays.keySet()) {
            Car car = carRepository.findById(carId);
            results.add(new CarVM(car, rentDays.get(carId)));
        }
        results.sort((f1, f2) -> f2.days - f1.days);
        return results;
    }

}
