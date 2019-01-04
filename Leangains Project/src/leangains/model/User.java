package leangains.model;

public class User {

    private String userName;
    private String password;
    private String gender;
    private int age;
    private double weight;
    private int height;
    private int bodyFat;
    private int muscleMass;
    private int activity;

    public User() {
        this("default", "default", "Man", 0,0,0,0,0,0);
    }

    public User(String userName, String password, String gender, int age, double weight, int height,
                int bodyFat, int mouscleMass, int activity) {

        this.userName = userName;
        this.password = password;
        this.gender = gender;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.bodyFat = bodyFat;
        this.muscleMass = mouscleMass;
        this.activity = activity;

    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getBodyFat() {
        return bodyFat;
    }

    public void setBodyFat(int bodyFat) {
        this.bodyFat = bodyFat;
    }

    public int getMuscleMass() {
        return muscleMass;
    }

    public void setMuscleMass(int mouscleMass) {
        this.muscleMass = mouscleMass;
    }

    public int getActivity() {
        return activity;
    }

    public void setActivity(int activity) {
        this.activity = activity;
    }

}

