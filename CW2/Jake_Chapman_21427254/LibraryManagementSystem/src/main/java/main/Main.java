package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class that launches the library management system application.
 */
public class Main extends Application {

    /**
     * The entry point of the application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Starts the JavaFX application by loading the main GUI window.
     *
     * @param primaryStage The primary stage/window of the application.
     * @throws Exception In case of any error during the application start.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the main FXML file.
        Parent root = FXMLLoader.load((getClass().getResource("/views/Main.fxml")));

        // Set up the main scene.
        Scene scene = new Scene(root, 1200, 800); // Dimensions as specified

        // Set up the primary stage.
        primaryStage.setTitle("Library Management System");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);  // Making the window non-resizable
        primaryStage.show();
    }

}
