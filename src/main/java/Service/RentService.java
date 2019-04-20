package Service;

import Domain.Car;
import Domain.Rent;
import Repository.IRepository;

import java.util.List;

public class RentService {

    private IRepository<Rent> rentRepository;
    private IRepository<Car> carRepository;

    public RentService(IRepository<Rent> rentRepository, IRepository<Car> carRepository) {
        this.rentRepository = rentRepository;
        this.carRepository = carRepository;
    }

    /**
     * Adds a rent.
     * @param id is the ID of the rent.
     * @param carId is the ID of the rented car.
     * @param days is the length of the rent in days.
     * @param kilometresDone is the number of kilometres done during the rent.
     */

    public void addRent(String id, String carId, int days, int kilometresDone) {
        Rent rent = new Rent(id, carId, days, kilometresDone);
        if (rentRepository.getStorage().containsKey(id))
            throw new RentServiceException(String.format("A rent with the ID %s already exists!", id));
        if (!carRepository.getStorage().containsKey(carId))
            throw new RentServiceException(String.format("There is no car with the ID %s!", carId));
        rentRepository.insert(rent);
    }

    /**
     * @return a list of all rents.
     */

    public List<Rent> getAllRents() {
        return rentRepository.getAll();
    }

}
