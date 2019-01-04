package leangains.model;

import javafx.beans.property.*;

import java.time.LocalDate;

public class TrainingLog {
    private final IntegerProperty id;
    private final StringProperty userName;
    private final StringProperty exercise;
    private ObjectProperty<LocalDate> date;
    private final DoubleProperty weightFirstSet;
    private final DoubleProperty weightSecondSet;
    private final DoubleProperty weightThirdSet;
    private final DoubleProperty weightFourthSet;
    private final DoubleProperty weightFifthSet;
    private final IntegerProperty repsFirstSet;
    private final IntegerProperty repsSecondSet;
    private final IntegerProperty repsThirdSet;
    private final IntegerProperty repsFourthSet;
    private final IntegerProperty repsFifthSet;
    private final StringProperty comments;

    /**
     * Default constructor.
     */
    public TrainingLog() {
        this(0, "default", "exercise", LocalDate.now(), 100.0, 90.0, 80.0, 70.0, 60.0, 8, 7, 6, 5, 4, "Awesome exercise!" );
    }

    /**
     * Constructor.
     */
    public TrainingLog(int id, String userName, String exercise, LocalDate date, double weightFirstSet, double weightSecondSet, double weightThirdSet,
                       double weightFourthSet, double weightFifthSet, int repsFirstSet, int repsSecondSet, int repsThirdSet,
                       int repsFourthSet, int repsFifthSet, String comments) {

        this.id = new SimpleIntegerProperty(id);
        this.userName = new SimpleStringProperty(userName);
        this.exercise = new SimpleStringProperty(exercise);
        this.date = new SimpleObjectProperty<LocalDate>(date);
        this.weightFirstSet = new SimpleDoubleProperty(weightFirstSet);
        this.weightSecondSet = new SimpleDoubleProperty(weightSecondSet);
        this.weightThirdSet = new SimpleDoubleProperty(weightThirdSet);
        this.weightFourthSet = new SimpleDoubleProperty(weightFourthSet);
        this.weightFifthSet = new SimpleDoubleProperty(weightFifthSet);
        this.repsFirstSet = new SimpleIntegerProperty(repsFirstSet);
        this.repsSecondSet = new SimpleIntegerProperty(repsSecondSet);
        this.repsThirdSet = new SimpleIntegerProperty(repsThirdSet);
        this.repsFourthSet = new SimpleIntegerProperty(repsFourthSet);
        this.repsFifthSet = new SimpleIntegerProperty(repsFifthSet);
        this.comments = new SimpleStringProperty(comments);
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

    public StringProperty userNameProperty() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName.set(userName);
    }

    public String getExercise() {
        return exercise.get();
    }

    public StringProperty exerciseProperty() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise.set(exercise);
    }

    public LocalDate getDate() {
        return date.get();
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date.set(date);
    }

    public double getWeightFirstSet() {
        return weightFirstSet.get();
    }

    public DoubleProperty weightFirstSetProperty() {
        return weightFirstSet;
    }

    public void setWeightFirstSet(double weightFirstSet) {
        this.weightFirstSet.set(weightFirstSet);
    }

    public double getWeightSecondSet() {
        return weightSecondSet.get();
    }

    public DoubleProperty weightSecondSetProperty() {
        return weightSecondSet;
    }

    public void setWeightSecondSet(double weightSecondSet) {
        this.weightSecondSet.set(weightSecondSet);
    }

    public double getWeightThirdSet() {
        return weightThirdSet.get();
    }

    public DoubleProperty weightThirdSetProperty() {
        return weightThirdSet;
    }

    public void setWeightThirdSet(double weightThirdSet) {
        this.weightThirdSet.set(weightThirdSet);
    }

    public double getWeightFourthSet() {
        return weightFourthSet.get();
    }

    public DoubleProperty weightFourthSetProperty() {
        return weightFourthSet;
    }

    public void setWeightFourthSet(double weightFourthSet) {
        this.weightFourthSet.set(weightFourthSet);
    }

    public double getWeightFifthSet() {
        return weightFifthSet.get();
    }

    public DoubleProperty weightFifthSetProperty() {
        return weightFifthSet;
    }

    public void setWeightFifthSet(double weightFifthSet) {
        this.weightFifthSet.set(weightFifthSet);
    }

    public int getRepsFirstSet() {
        return repsFirstSet.get();
    }

    public IntegerProperty repsFirstSetProperty() {
        return repsFirstSet;
    }

    public void setRepsFirstSet(int repsFirstSet) {
        this.repsFirstSet.set(repsFirstSet);
    }

    public int getRepsSecondSet() {
        return repsSecondSet.get();
    }

    public IntegerProperty repsSecondSetProperty() {
        return repsSecondSet;
    }

    public void setRepsSecondSet(int repsSecondSet) {
        this.repsSecondSet.set(repsSecondSet);
    }

    public int getRepsThirdSet() {
        return repsThirdSet.get();
    }

    public IntegerProperty repsThirdSetProperty() {
        return repsThirdSet;
    }

    public void setRepsThirdSet(int repsThirdSet) {
        this.repsThirdSet.set(repsThirdSet);
    }

    public int getRepsFourthSet() {
        return repsFourthSet.get();
    }

    public IntegerProperty repsFourthSetProperty() {
        return repsFourthSet;
    }

    public void setRepsFourthSet(int repsFourthSet) {
        this.repsFourthSet.set(repsFourthSet);
    }

    public int getRepsFifthSet() {
        return repsFifthSet.get();
    }

    public IntegerProperty repsFifthSetProperty() {
        return repsFifthSet;
    }

    public void setRepsFifthSet(int repsFifthSet) {
        this.repsFifthSet.set(repsFifthSet);
    }

    public String getComments() {
        return comments.get();
    }

    public StringProperty commentsProperty() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments.set(comments);
    }
}
