import Domain.Car;
import Domain.Rent;
import Repository.FileRepository;
import Repository.IRepository;
import Service.CarService;
import Service.RentService;
import UI.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mainWindow.fxml"));
        Parent root = fxmlLoader.load();

        IRepository<Car> carRepository = new FileRepository<>("cars", Car.class);
        IRepository<Rent> rentRepository = new FileRepository<>("rents", Rent.class);

        CarService carService = new CarService(carRepository, rentRepository);
        RentService rentService = new RentService(rentRepository, carRepository);

        MainController mainController = fxmlLoader.getController();
        mainController.setServices(carService, rentService);
/*
        carService.addCar("1","Dacia",6300,100.0);
        carService.addCar("2","Renault",1200,85.0);
        carService.addCar("3","Volkswagen",900,205.0);
        carService.addCar("4","Audi",1550,148.0);
        carService.addCar("6","Mercedes",1890,112.0);
        carService.addCar("9","Skoda",2010,79.0);

        rentService.addRent("1","1",5,800);
        rentService.addRent("2","3",9,1950);
        rentService.addRent("3","1",14,4390);
        rentService.addRent("4","2",8,2000);
        rentService.addRent("5","4",3,430);
        rentService.addRent("6","9",10,500);
        rentService.addRent("7","6",2,78);
        rentService.addRent("8","4",7,660);
*/
        primaryStage.setTitle("Car Rent Manager");
        primaryStage.setScene(new Scene(root, 600, 1000));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
