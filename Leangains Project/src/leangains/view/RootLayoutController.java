package leangains.view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import leangains.MainApp;
import leangains.data.Datasource;
import leangains.model.TrainingLog;
import leangains.model.User;
import leangains.model.WeightTracker;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class RootLayoutController {

    // Class fields.
    public WeightTracker selectedRecord;
    private User newUser;
    private User user;
    public static boolean addClicked = false;
    public static boolean editClicked = false;

    // Reference to the main application.
    private MainApp mainApp;

    /**
     * Constructor is called before initialize() method.
     */
    public RootLayoutController() {
    }

    /**
     * Profile tab text fields and labels.
     */
    @FXML
    private ChoiceBox<String> genderField;

    @FXML
    private TextField ageField;

    @FXML
    private TextField weightField;

    @FXML
    private TextField heightField;

    @FXML
    private TextField bodyFatField;

    @FXML
    private TextField muscleMassField;

    @FXML
    private TextField acvitityField;

    @FXML
    private Label currentWeight;

    @FXML
    private Label weeklyAverage;

    @FXML
    private Label biweeklyAverage;

    @FXML
    private Label monthlyAverage;

    @FXML
    private Label deadliftPB;

    @FXML
    private Label squatPB;

    @FXML
    private Label benchPressPB;

    @FXML
    private Label calorieMaintenance;

    @FXML
    private Label calorieDeficit;

    /**
     * TableView and TableViewColumnsfor Weight Tracker.
     */
    @FXML
    private TableView<WeightTracker> weightTrackerTable;

    @FXML
    private TableColumn<WeightTracker, Integer> idCol;

    @FXML
    private TableColumn<WeightTracker, String> dateCol;

    @FXML
    private TableColumn<WeightTracker, Double> weightCol;

    @FXML
    private TableColumn<WeightTracker, Integer> waistCol;

    @FXML
    private TableColumn<WeightTracker, Integer> abdominalCol;

    @FXML
    private TableColumn<WeightTracker, Integer> hipsCol;

    @FXML
    private TableColumn<WeightTracker, Integer> chestCol;

    @FXML
    private TableColumn<WeightTracker, Integer> neckCol;

    @FXML
    private TableColumn<WeightTracker, Integer> bicepsCol;

    /**
     * TableView and TableViewColumns for Training Log.
     */
    @FXML
    private TableView<TrainingLog> trainingLogTable;

    @FXML
    private TableColumn<TrainingLog, Integer> trainingLogID;

    @FXML
    private TableColumn<TrainingLog, String> exerciseCol;

    @FXML
    private TableColumn<TrainingLog, Double> weightFirstSetCol;

    @FXML
    private TableColumn<TrainingLog, Integer> repsFirstSetCol;

    @FXML
    private TableColumn<TrainingLog, Double> weightSecondSetCol;

    @FXML
    private TableColumn<TrainingLog, Integer> repsSecondSetCol;

    @FXML
    private TableColumn<TrainingLog, Double> weightThirdSetCol;

    @FXML
    private TableColumn<TrainingLog, Integer> repsThirdSetCol;

    @FXML
    private TableColumn<TrainingLog, Double> weightFourthSetCol;

    @FXML
    private TableColumn<TrainingLog, Integer> repsFourthSetCol;

    @FXML
    private TableColumn<TrainingLog, Double> weightFifthSetCol;

    @FXML
    private TableColumn<TrainingLog, Integer> repsFifthSetCol;

    @FXML
    private TableColumn<TrainingLog, String> commentsCol;

    @FXML
    private DatePicker trainingLogDateField;

    /**
     * Method to handle Update button.
     *
     * @throws SQLException
     */
    public void handleUpdate() throws SQLException {
        // Getting field values from the window for multiple use.
        String userName = "default";
        String gender = genderField.getValue();
        int age = Integer.parseInt(ageField.getText());
        double weight = Double.parseDouble(weightField.getText()); // Use it later for the calculator.
        int height = Integer.parseInt(heightField.getText());
        int bodyFat = Integer.parseInt(bodyFatField.getText());
        int muscleMass = Integer.parseInt(muscleMassField.getText());
        int activity = Integer.parseInt(acvitityField.getText());

        // Get a new User instance of the specific user name.
        user = Datasource.getInstance().queryUser(userName);

        // Check if user exists in the database.
        if (user != null) {
            // If user exists then update the record.
            Datasource.getInstance().updateUser(userName, "password", gender, age, height, bodyFat, muscleMass, activity);
        } else {
            // If user doesn't exist then create a new one.
            int userId = Datasource.getInstance().insertUser(userName, "password", gender, age, height, bodyFat, muscleMass, activity);
            System.out.println(userId);
        }
    }

    /**
     * Updates Profile tab information on the right side.
     */
//    public void setProfileInfo() {
//        // Calculate the calorie maintenance, deficit and update correct fields.
//        double result = Calculator.calCalories(gender, age, weight, height, bodyFat, muscleMass, activity);
//
//        DecimalFormat df = new DecimalFormat("#");
//
//        calorieMaintenance.setText(df.format(result));
//
//        if (user.getGender().equals("Man")) {
//            newCalorieDeficit = result - 500;
//            calorieDeficit.setText(df.format(newCalorieDeficit));
//        } else if (user.getGender().equals("Woman")) {
//            newCalorieDeficit = result - 350;
//            calorieDeficit.setText(df.format(newCalorieDeficit));
//        }
//    }

    /**
     * Called when the user clicks the new button. Opens a dialog to edit details for a new person.
     */
    @FXML
    private void handleAddWeight() {
        int i = 0; // Index of the last object from the 'weightTrackersList'.
        addClicked = true; // User clicked Add button so we set the flag to true.
        WeightTracker tempWeighTracker = new WeightTracker();

        // Get all WeightTracker objects and add them to the new list.
        List<WeightTracker> weightTrackersList = Datasource.getInstance().queryWeightTracker("default");
        Iterator<WeightTracker> itr = weightTrackersList.iterator();

        while (itr.hasNext()) {
            i = itr.next().getId(); // Get an id of the last object.
        }
        if (i != 0) {
            tempWeighTracker.setId(i + 1);
        }
        // Shows the dialog window and sets the value for okClicked. The WeightTracker object passed as a argument
        // will be later used in WeightTrackerDialogController to retrieve data and fill in the UI text fields,,
        // if for example user clicks Edit button.
        boolean okClicked = mainApp.showWeightTrackerEditDialog(tempWeighTracker);
        // If user clicked OK button we gonna add a new object to the WeightLogData.
        if (okClicked) {
            mainApp.getWeightTrackerData().add(tempWeighTracker); // Add a new object to the WeightLogData ObservableList.
            mainApp.getWeightTrackerData().sort((o1, o2) -> o2.getDate().compareTo(o1.getDate())); // Order by date, using lambda.
        }
        // Updates information in the profile tab.
        refreshProfile();
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditWeightTracker() {
        editClicked = true; // User clicked Edit button.
        selectedRecord = weightTrackerTable.getSelectionModel().getSelectedItem(); // Get index of the selected row in the TableView.
        // If selectedRecord won't be null (meaning we gonna select the row) then show Add/Edit window for that record
        // with appropriate data.
        if (selectedRecord != null) {
            mainApp.showWeightTrackerEditDialog(selectedRecord);
            mainApp.getWeightTrackerData().sort((o1, o2) -> o2.getDate().compareTo(o1.getDate())); // Sort data by Date, using lambda.
        }
        // Update/Refresh information in the profile tab.
        refreshProfile();
    }

    /**
     * Called when the user clicks on the delete button in the WeightTracker tab.
     */
    @FXML
    public void handleDeleteWeight() {
        int selectedIndex = weightTrackerTable.getSelectionModel().getSelectedIndex(); // Get index for the selected record.
        if (selectedIndex >= 0) {
            int objectId = idCol.getCellObservableValue(selectedIndex).getValue(); // Retrieve id of the selected record from idCol.
            Datasource.getInstance().deleteWeightTracker(objectId); // Delete the object from the database, id of the object equals index of the column.
            weightTrackerTable.getItems().remove(selectedIndex); // Delete the record from TableView.
        } else {
            // Show alert if we won't select any record and click Delete.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Record Selected");
            alert.setContentText("Please select a record in the table.");
            alert.showAndWait();
        }
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded. In this section we gonna add all of the data to the Profile,
     * Weight Tracker, Training Log and Settings in the UI as soon as application starts.
     */
    @FXML
    public void initialize() {
        // Add data to the WeightTracker TableView cells.
        idCol.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        dateCol.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        weightCol.setCellValueFactory(cellData -> cellData.getValue().weightProperty().asObject());
        waistCol.setCellValueFactory(cellData -> cellData.getValue().waistProperty().asObject());
        abdominalCol.setCellValueFactory(cellData -> cellData.getValue().abdominalProperty().asObject());
        hipsCol.setCellValueFactory(cellData -> cellData.getValue().hipsProperty().asObject());
        chestCol.setCellValueFactory(cellData -> cellData.getValue().chestProperty().asObject());
        neckCol.setCellValueFactory(cellData -> cellData.getValue().neckProperty().asObject());
        bicepsCol.setCellValueFactory(cellData -> cellData.getValue().bicepsProperty().asObject());

        // Add data to the TrainingLog TableView cells.
        trainingLogID.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        exerciseCol.setCellValueFactory(cellData -> cellData.getValue().exerciseProperty());
        weightFirstSetCol.setCellValueFactory(cellData -> cellData.getValue().weightFirstSetProperty().asObject());
        weightSecondSetCol.setCellValueFactory(cellData -> cellData.getValue().weightSecondSetProperty().asObject());
        weightThirdSetCol.setCellValueFactory(cellData -> cellData.getValue().weightThirdSetProperty().asObject());
        weightFourthSetCol.setCellValueFactory(cellData -> cellData.getValue().weightFourthSetProperty().asObject());
        weightFifthSetCol.setCellValueFactory(cellData -> cellData.getValue().weightFirstSetProperty().asObject());
        repsFirstSetCol.setCellValueFactory(cellData -> cellData.getValue().repsFirstSetProperty().asObject());
        repsSecondSetCol.setCellValueFactory(cellData -> cellData.getValue().repsSecondSetProperty().asObject());
        repsThirdSetCol.setCellValueFactory(cellData -> cellData.getValue().repsThirdSetProperty().asObject());
        repsFourthSetCol.setCellValueFactory(cellData -> cellData.getValue().repsFourthSetProperty().asObject());
        repsFifthSetCol.setCellValueFactory(cellData -> cellData.getValue().repsFifthSetProperty().asObject());
        commentsCol.setCellValueFactory(cellData -> cellData.getValue().commentsProperty());

        // Get a User from the database.
        user = Datasource.getInstance().queryUser("default");

        // Checking if the user exists in the database, if it doesn't then we will create a new default user.
        if (user != null) {
            // Put data in the Profile fields from the existing User.
            genderField.setValue(user.getGender());
            ageField.setText(Integer.toString(user.getAge()));
            weightField.setText(Double.toString(user.getWeight()));
            heightField.setText(Integer.toString(user.getHeight()));
            bodyFatField.setText(Integer.toString(user.getBodyFat()));
            muscleMassField.setText(Integer.toString(user.getMuscleMass()));
            acvitityField.setText(Integer.toString(user.getActivity()));
        } else {
            // User doesn't exist so we gonna create a sample one and add default values to the GUI fields.
            newUser = new User();
            genderField.setValue(newUser.getGender());
            ageField.setText(Integer.toString(newUser.getAge()));
            weightField.setText(Double.toString(newUser.getWeight()));
            heightField.setText(Integer.toString(newUser.getHeight()));
            bodyFatField.setText(Integer.toString(newUser.getBodyFat()));
            muscleMassField.setText(Integer.toString(newUser.getMuscleMass()));
            acvitityField.setText(Integer.toString(newUser.getActivity()));
        }
    }

    /**
     * Here we gonna add all of the gathered data from the initialize() method to the TableView etc.
     *
     * @param mainApp pass mainApp reference to be able to use @method getWeightLogData().
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Sort WeightlogData by Date using lambda straight away after application will load.
        mainApp.getWeightTrackerData().sort((o1, o2) -> o2.getDate().compareTo(o1.getDate()));

        // Add ObservableList data to the TableView.
        if (!mainApp.getWeightTrackerData().isEmpty()) {
            weightTrackerTable.setItems(mainApp.getWeightTrackerData()); // Adds data to the TableView.
            refreshProfile(); // Refresh the Profile tab with new information.
        } else {
            weightTrackerTable.setItems(mainApp.getWeightTrackerData());
        }

        // Add ObservableList data to the TableView.
        if (!mainApp.getTrainingLogData().isEmpty()) {
            trainingLogTable.setItems(mainApp.getTrainingLogData()); // Adds data to the TableView.
            // refreshProfile(); // Refresh the Profile tab with new information.
        } else {
            trainingLogTable.setItems(mainApp.getTrainingLogData());
        }
    }

    /**
     * Refresh information in the Profile tab in accordance to the data from Weight Tracker and Training Log.
     */
    public void refreshProfile() {
        // Set weight from the first record in the Weight Tracker (the latest added weight taking in to consideration Date).
        weightField.setText(weightCol.getCellObservableValue(0).getValue().toString());
        currentWeight.setText(weightCol.getCellObservableValue(0).getValue().toString());
    }
}
