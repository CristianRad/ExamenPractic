package Domain;

public class Car extends Entity {

    private String model;
    private int kilometres;
    private double price;

    public Car(String id, String model, int kilometres, double price) {
        super(id);
        this.model = model;
        this.kilometres = kilometres;
        this.price = price;
    }

    
}
