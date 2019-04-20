package Domain;

public class Rent extends Entity {

    private String carId;
    private int days;
    private int kilometresDone;

    public Rent(String id, String carId, int days, int kilometresDone) {
        super(id);
        this.carId = carId;
        this.days = days;
        this.kilometresDone = kilometresDone;
    }

    @Override
    public String toString() {
        return "Rent{" +
                "carId='" + carId + '\'' +
                ", days=" + days +
                ", kilometresDone=" + kilometresDone +
                '}';
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getKilometresDone() {
        return kilometresDone;
    }

    public void setKilometresDone(int kilometresDone) {
        this.kilometresDone = kilometresDone;
    }

}
