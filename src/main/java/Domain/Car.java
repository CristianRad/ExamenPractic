package Domain;

public class Car extends Entity {

    private String model;
    private int initialKm;
    private double price;

    public Car(String id, String model, int initialKm, double price) {
        super(id);
        this.model = model;
        this.initialKm = initialKm;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", initialKm=" + initialKm +
                ", price=" + price +
                '}';
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getInitialKm() {
        return initialKm;
    }

    public void setInitialKm(int initialKm) {
        this.initialKm = initialKm;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
