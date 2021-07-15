package Ad.Hw4.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxmlController.fxml")));
        primaryStage.setTitle("Puzzle Solver");
        primaryStage.setScene(new Scene(root, 765, 508));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
