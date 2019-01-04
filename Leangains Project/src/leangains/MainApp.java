package leangains;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import leangains.data.Datasource;
import leangains.model.TrainingLog;
import leangains.model.WeightTracker;
import leangains.view.RootLayoutController;
import leangains.view.WeightTrackerDialogController;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class MainApp extends Application {
    private Stage primaryStage;
    private ObservableList<WeightTracker> weightTrackerData;
    private ObservableList<TrainingLog> trainingLogData;

    /**
     * Constructor.
     * This is a constructor for the MainApp.
     * It opens new connection with the database inputs data into ObservableList.
     */
    public MainApp() {

        // Open database connection.
        if (!Datasource.getInstance().open()) {
            System.out.println("ERROR: Could not connect to database.");
            Platform.exit();
        }
        // Get data from the database.
        weightTrackerData = Datasource.getInstance().queryWeightTracker("default");
        trainingLogData = Datasource.getInstance().queryTrainingLog("default");
    }

    /**
     * Getter for the TrainingLog ObservableList.
     *
     * @return reference to the TrainingLog ObservableList.
     */
    public ObservableList<TrainingLog> getTrainingLogData() {
        return trainingLogData;
    }

    /**
     * Getter for the WeightTracker ObservableList.
     *
     * @return reference to the WeightTracker ObservableList
     */
    public ObservableList<WeightTracker> getWeightTrackerData() {
        return weightTrackerData;
    }

    /**
     * Getter for the Stage.
     *
     * @return reference to the primaryStage.
     */
    public Stage getPrimaryStage() {
        return this.primaryStage;
    }

    /**
     * Start method from Application class. We have to override it for JavaFX to work.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        this.primaryStage = primaryStage;
        initRootLayout();

    }

    /**
     * Initializes the RootLayoutController and sets up the stage.
     *
     * @throws IOException
     */
    void initRootLayout() throws IOException {
        FXMLLoader loader = new FXMLLoader(); // Create a new FXMLLoader.
        loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml")); // Set the loaders location.

        Scene scene = new Scene(loader.load()); // Create new scene and pass the fxml scene graph from fxml file.

        RootLayoutController controller = loader.getController(); // Get an instance of RootLayoutController.
        controller.setMainApp(this); // Pass MainApp instance as a parameter using 'this' to the method from RootLayoutController.

        primaryStage.setTitle("Leangains Project");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * This method creates a scene and a stage for the Add/Edit dialog window for Weight Tracker.
     * Window will pop up when the user clicks Add or Edit button in the Weight Tracker tab.
     *
     * @param weightTracker
     * @return
     */
    public boolean showWeightTrackerEditDialog(WeightTracker weightTracker) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/WeightTrackerEditDialog.fxml"));
            AnchorPane page = loader.load();

            // Create the dialog Stage for adding and editing WeightTracker records.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Weight Tracker Record");
            // You will not be able to do anything on the primaryStage, you have to close the dialog window first.
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage); // Sets the owner of the dialogStage.

            // Create new scene and pass the fxml scene graph.
            Scene scene = new Scene(page);

            dialogStage.setScene(scene);

            // Set the Weight Tracker into controller.
            WeightTrackerDialogController controller = loader.getController(); // Get an instance of the controller from the fxml file.
            controller.setDialogStage(dialogStage); // We gonna send a reference dialogStage to the WeightTrackerDialogController.
            controller.setWeightTracker(weightTracker); // Send instance of Weight Tracker from the methods argument.

            dialogStage.showAndWait(); // Shows dialog window and waits for it to be hidden (closed) before returning to the caller.

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
