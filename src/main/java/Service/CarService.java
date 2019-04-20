package Service;

import Domain.Car;
import Repository.IRepository;

import java.util.List;

public class CarService {

    private IRepository<Car> carRepository;

    public CarService(IRepository<Car> carRepository) {
        this.carRepository = carRepository;
    }

    /**
     * Adds a car.
     * @param id is the ID of the car.
     * @param model is the car model.
     * @param initialKilometres is the usage of the car.
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

}
