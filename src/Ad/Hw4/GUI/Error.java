package Ad.Hw4.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Error {

    @FXML
    void Check(ActionEvent event) throws IOException {
        Parent Show = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxmlController.fxml")));
        Scene printscene = new Scene(Show);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(printscene);
        window.show();
    }

}
