package leangains.model;

import javafx.beans.property.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WeightTracker {

    private final IntegerProperty id;
    private final SimpleStringProperty userName;
    private final ObjectProperty<LocalDate> date;
    private final DoubleProperty weight;
    private final IntegerProperty waist;
    private final IntegerProperty abdominal;
    private final IntegerProperty hips;
    private final IntegerProperty chest;
    private final IntegerProperty neck;
    private final IntegerProperty biceps;

    public WeightTracker() {
        this(1, "default", null, 0, 0, 0, 0, 0, 0, 0);
    }

    public WeightTracker(int id, String userName, LocalDate date, double weight, int waist, int abdominal, int hips,
                         int chest, int neck, int biceps){

        this.id = new SimpleIntegerProperty(id);
        this.userName = new SimpleStringProperty(userName);
        this.date = new SimpleObjectProperty<LocalDate>(date);
        this.weight = new SimpleDoubleProperty(weight);
        this.waist = new SimpleIntegerProperty(waist);
        this.abdominal = new SimpleIntegerProperty(abdominal);
        this.hips = new SimpleIntegerProperty(hips);
        this.chest = new SimpleIntegerProperty(chest);
        this.neck = new SimpleIntegerProperty(neck);
        this.biceps = new SimpleIntegerProperty(biceps);

    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getUserName() {
        return userName.get();
    }

    public SimpleStringProperty userNameProperty() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName.set(userName);
    }

    public LocalDate getDate() {
        return date.get();
    }

    public ObjectProperty dateProperty() {
        return date;
    }

    public void setDate(LocalDate date) {

        this.date.set(date);
    }

    public double getWeight() {
        return weight.get();
    }

    public DoubleProperty weightProperty() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight.set(weight);
    }

    public int getWaist() {
        return waist.get();
    }

    public IntegerProperty waistProperty() {
        return waist;
    }

    public void setWaist(int waist) {
        this.waist.set(waist);
    }

    public int getAbdominal() {
        return abdominal.get();
    }

    public IntegerProperty abdominalProperty() {
        return abdominal;
    }

    public void setAbdominal(int abdominal) {
        this.abdominal.set(abdominal);
    }

    public int getHips() {
        return hips.get();
    }

    public IntegerProperty hipsProperty() {
        return hips;
    }

    public void setHips(int hips) {
        this.hips.set(hips);
    }

    public int getChest() {
        return chest.get();
    }

    public IntegerProperty chestProperty() {
        return chest;
    }

    public void setChest(int chest) {
        this.chest.set(chest);
    }

    public int getNeck() {
        return neck.get();
    }

    public IntegerProperty neckProperty() {
        return neck;
    }

    public void setNeck(int neck) {
        this.neck.set(neck);
    }

    public int getBiceps() {
        return biceps.get();
    }

    public IntegerProperty bicepsProperty() {
        return biceps;
    }

    public void setBiceps(int biceps) {
        this.biceps.set(biceps);
    }

    public static LocalDate today() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        LocalDate ld = LocalDate.parse(dtf.format(now));
        return ld;
    }
}
