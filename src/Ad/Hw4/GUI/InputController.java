package Ad.Hw4.GUI;

import Ad.Hw4.Puzzle.Board;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class InputController {

    @FXML
    private TextField input1;

    @FXML
    private TextField input5;

    @FXML
    private TextField input9;

    @FXML
    private TextField input2;

    @FXML
    private TextField input4;

    @FXML
    private TextField input7;

    @FXML
    private TextField input3;

    @FXML
    private TextField input6;

    @FXML
    private TextField input8;

    @FXML
    void saveInput(ActionEvent event) throws IOException {
        int[][] values = new int[3][3];
        try {
            values[0][0] = Integer.parseInt(input1.getText());
            values[0][1] = Integer.parseInt(input2.getText());
            values[0][2] = Integer.parseInt(input3.getText());
            values[1][0] = Integer.parseInt(input4.getText());
            values[1][1] = Integer.parseInt(input5.getText());
            values[1][2] = Integer.parseInt(input6.getText());
            values[2][0] = Integer.parseInt(input7.getText());
            values[2][1] = Integer.parseInt(input8.getText());
            values[2][2] = Integer.parseInt(input9.getText());
        }
        catch (Exception e){
            return;
        }
        Board board = Board.getInstance();
        board.setValues(values);
        System.out.println("input:  " + Arrays.deepToString(board.getValues()));
        Parent Show = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxmlController.fxml")));
        Scene printscene = new Scene(Show);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(printscene);
        window.show();
    }
}
