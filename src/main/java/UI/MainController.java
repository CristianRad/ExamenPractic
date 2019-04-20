package UI;

import Domain.Car;
import Domain.Rent;
import Service.CarService;
import Service.RentService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MainController {
    
    public TableView tblCars;
    public TableColumn carId;
    public TableColumn carModel;
    public TableColumn carInitialKm;
    public TableColumn carPrice;
    public TableView tblRents;
    public TableColumn rentId;
    public TableColumn rentCarId;
    public TableColumn rentDays;
    public TableColumn rentKmDone;
    public Button btnAddCar;
    public Button btnAddRent;
    public TextField txtCarId;
    public TextField txtCarModel;
    public TextField txtCarInitialKm;
    public TextField txtCarPrice;
    public TextField txtRentId;
    public TextField txtRentCarId;
    public TextField txtRentDays;
    public TextField txtRentKmDone;
    public TextField txtCarIdForKm;
    public TextField txtCarTotalKm;
    public TextField txtCarIdForIncome;
    public TextField txtCarTotalIncome;
    public Button btnCalcKm;
    public Button btnCalcIncome;

    public CarService carService;
    public RentService rentService;

    public void setServices(CarService carService, RentService rentService) {
        this.carService = carService;
        this.rentService = rentService;
    }

    private ObservableList<Car> cars = FXCollections.observableArrayList();
    private ObservableList<Rent> rents = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        Platform.runLater(() -> {
            cars.addAll(carService.getAllCars());
            tblCars.setItems(cars);
            rents.addAll(rentService.getAllRents());
            tblRents.setItems(rents);
        });
    }

    public void btnAddCarClick(ActionEvent actionEvent) {
        try {
            String id = txtCarId.getText();
            String model = txtCarModel.getText();
            int initialKm = Integer.parseInt(txtCarInitialKm.getText());
            double price = Double.parseDouble(txtCarPrice.getText());

            carService.addCar(id, model, initialKm, price);
            cars.clear();
            cars.addAll(carService.getAllCars());
        } catch (RuntimeException error) {
            Common.showValidationError(error.getMessage());
        }
    }

    public void btnAddRentClick(ActionEvent actionEvent) {
        try {
            String id = txtRentId.getText();
            String carId = txtRentCarId.getText();
            int days = Integer.parseInt(txtRentDays.getText());
            int KmDone = Integer.parseInt(txtRentKmDone.getText());

            rentService.addRent(id, carId, days, KmDone);
            rents.clear();
            rents.addAll(rentService.getAllRents());
        } catch (RuntimeException error) {
            Common.showValidationError(error.getMessage());
        }
    }

    public void btnCalculateKmClick(ActionEvent actionEvent) {
        try {
            String carId = txtCarIdForKm.getText();
            Car car = carService.getCarRepository().findById(carId);
            int totalKm = car.getInitialKm();

            for (Rent rent : rentService.getAllRents())
                if (rent.getCarId().equals(carId))
                    totalKm += rent.getKilometresDone();
            txtCarTotalKm.setText(totalKm + "");
        } catch (RuntimeException error) {
            Common.showValidationError(error.getMessage());
        }
    }

    public void btnCalculateIncomeClick(ActionEvent actionEvent) {
        try {
            String carId = txtCarIdForIncome.getText();
            Car car = carService.getCarRepository().findById(carId);
            double price = car.getPrice();
            double totalIncome = 0;

            for (Rent rent : rentService.getAllRents())
                if (rent.getCarId().equals(carId))
                    totalIncome = totalIncome + price * rent.getDays();
            txtCarTotalIncome.setText(totalIncome + "");
        } catch (RuntimeException error) {
            Common.showValidationError(error.getMessage());
        }
    }

}
