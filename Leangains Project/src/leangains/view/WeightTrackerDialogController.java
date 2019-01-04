package leangains.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import leangains.data.Datasource;
import leangains.model.WeightTracker;

import java.sql.SQLException;

public class WeightTrackerDialogController {

    // Class fields ----------------------
    private Stage dialogStage;
    private WeightTracker weightTracker;
    private boolean okClicked = false;
    // -----------------------------------

    /**
     * Returns the value of okClicked flag. Flag is changes when the OK button is clicked.
     *
     * @return true or false
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Add/Edit dialog fields.
     */
    @FXML
    private DatePicker dateField;

    @FXML
    private TextField weightField;

    @FXML
    private TextField waistField;

    @FXML
    private TextField abdominalField;

    @FXML
    private TextField hipsField;

    @FXML
    private TextField chestField;

    @FXML
    private TextField bicepsField;

    @FXML
    private TextField neckField;

    /**
     * Initialize WeightTrackerDialogController.
     */
    @FXML
    void initialize() {
    }

    /**
     * Method to set dialogStage so we can communicate with MainApp class.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Creates a new WeightTracker object and sets values to it. We can then use it in the MainApp
     * and show the edit window with correct data.
     *
     * @param weightTracker
     */
    public void setWeightTracker(WeightTracker weightTracker) {
        this.weightTracker = weightTracker;
        // We have to get data from Weight Tracker object and convert them into Strings for the UI text fields.
        weightField.setText(Double.toString(weightTracker.getWeight()));
        dateField.setValue(weightTracker.getDate());
        waistField.setText(Integer.toString(weightTracker.getWaist()));
        abdominalField.setText(Integer.toString(weightTracker.getAbdominal()));
        hipsField.setText(Integer.toString(weightTracker.getHips()));
        chestField.setText(Integer.toString(weightTracker.getChest()));
        neckField.setText(Integer.toString(weightTracker.getNeck()));
        bicepsField.setText(Integer.toString(weightTracker.getBiceps()));
    }

    /**
     * Event handler for OK button. It adds values to the weightTracker object and inserts data into the database.
     *
     * @throws SQLException
     */
    @FXML
    private void handleOk() throws SQLException {
        if (isInputValid()) {
            weightTracker.setId(weightTracker.getId());
            weightTracker.setWeight(Double.parseDouble(weightField.getText()));
            weightTracker.setWaist(Integer.parseInt(waistField.getText()));
            weightTracker.setAbdominal(Integer.parseInt(abdominalField.getText()));
            weightTracker.setHips(Integer.parseInt(hipsField.getText()));
            weightTracker.setChest(Integer.parseInt(chestField.getText()));
            weightTracker.setNeck(Integer.parseInt(neckField.getText()));
            weightTracker.setBiceps(Integer.parseInt(bicepsField.getText()));
            weightTracker.setDate(dateField.getValue());

            // If the Add button was clicked (check the addClicked flag) earlier we gonna add the new data to the database.
            if (RootLayoutController.addClicked) {
                Datasource.getInstance().insertWeightTracker(
                        weightTracker.getId(),
                        "default",
                        dateField.getValue(),
                        Double.parseDouble(weightField.getText()),
                        Integer.parseInt(waistField.getText()),
                        Integer.parseInt(abdominalField.getText()),
                        Integer.parseInt(hipsField.getText()),
                        Integer.parseInt(chestField.getText()),
                        Integer.parseInt(bicepsField.getText()),
                        Integer.parseInt(neckField.getText())
                );
                // Set addClicked flag to false so if the user clicks Add again
                // the if statement won't be skipped.
                RootLayoutController.addClicked = false;
            }

            // If Edit button was clicked (check the editClicked flat) earlier we gonna update appropriate record.
            if (RootLayoutController.editClicked) {
                Datasource.getInstance().updateWeightTracker(
                        weightTracker.getId(),
                        weightTracker.getDate(),
                        weightTracker.getWeight(),
                        weightTracker.getWaist(),
                        weightTracker.getAbdominal(),
                        weightTracker.getHips(),
                        weightTracker.getChest(),
                        weightTracker.getBiceps(),
                        weightTracker.getNeck());
                // Set editClicked flag to false so if the user clicks Edit again
                // the if statement won't be skipped.
                RootLayoutController.editClicked = false;
            }
            okClicked = true;
            dialogStage.close(); // Close the window after clicking OK.
        }
    }

    /**
     * Closes the dialog window if Cancel was clicked.
     */
    @FXML
    public void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validation of the data entered in the dialog window. So far we want from user to type in at least weight
     * and date of the weighing.
     *
     * @return true if is valid, false if it is not
     */
    private boolean isInputValid() {
        String errorMessage = "";

        // Check if the weight field is empty.
        if (weightField.getText() == null || weightField.getText().length() == 0) {
            errorMessage += "No valid weight!\n";
        }

        // Check if the weight date field is empty.
        if (dateField.getValue() == null) {
            errorMessage += "No valid date!\n";
        }

        // If there was na errorMessage generated we gonna return true.
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR); // Create a Alert object.
            alert.initOwner(dialogStage); // Set the owner of the Alert window.
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            alert.showAndWait(); // You won't be able to click anywhere else if the alert window is still open.
            return false; // User didn't type in data into appropriate fields so he will have to try again.
        }
    }
}
