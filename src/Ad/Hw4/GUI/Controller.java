package Ad.Hw4.GUI;

import Ad.Hw4.Puzzle.Board;
import Ad.Hw4.Puzzle.Direction;
import Ad.Hw4.Puzzle.PuzzleSolver;
import com.jfoenix.controls.JFXComboBox;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Controller implements Initializable {

    private PuzzleSolver puzzleSolver = new PuzzleSolver(Board.getInstance(), 24);
    private Board board = Board.getInstance();
    private List<Direction> path = new ArrayList();
    private int iterate = 0;
    private final int size = 3;
    private boolean is_solve = false;
    private boolean is_running = false;

    @FXML
    private Label one;

    @FXML
    private Label two;

    @FXML
    private Label four;

    @FXML
    private Label five;

    @FXML
    private Label three;

    @FXML
    private Label six;

    @FXML
    private Label seven;

    @FXML
    private Label eight;

    @FXML
    private Label nine;

    @FXML
    private JFXComboBox<String> combo = new JFXComboBox<>();

    @FXML
    private Label totalMoveLabel;

    @FXML
    void nextStepHandler(ActionEvent event) throws IOException {
        if (this.path.size() > 1) {
            if (iterate == this.path.size()) {
                Parent Show = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("end.fxml")));
                Scene printscene = new Scene(Show);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(printscene);
                window.show();
            } else if (is_solve) {
                setValues(puzzleSolver.getNext(this.path.get(iterate++)));
                totalMoveLabel.setText("Number of total move: " + iterate);
            } else {
                Parent Show = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("error.fxml")));
                Scene printscene = new Scene(Show);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(printscene);
                window.show();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.puzzleSolver.setBoard(this.board);
        this.path.clear();
        this.iterate = 0;
        this.is_solve = false;
        combo.getItems().addAll("BackTracking Algorithm");
        combo.getSelectionModel().select(0);
        setValues(this.board.getValues());
        can_solve();
    }

    @FXML
    void setNumberHandler(ActionEvent event) throws IOException {
        Parent Show = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("inputController.fxml")));
        Scene printscene = new Scene(Show);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(printscene);
        window.setTitle("Set Values");
        window.show();
    }

    void runTask() {
        for (int i = 0; i < path.size(); i++) {
            try {
                Platform.runLater(() -> {
                    setValues(puzzleSolver.getNext(path.get(iterate++)));
                    totalMoveLabel.setText("Number of total move: " + iterate);
                });
                Thread.sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.is_running = false;
    }

    @FXML
    void start(ActionEvent event) throws IOException {
        if (this.board.is_set && !is_running && is_solve) {
            is_running = true;
            Runnable task = this::runTask;
            Thread backgroundThread = new Thread(task);
            backgroundThread.setDaemon(true);
            backgroundThread.start();
        } else {
            Parent Show = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("error.fxml")));
            Scene printscene = new Scene(Show);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(printscene);
            window.show();
        }
    }


    @FXML
    void reset(ActionEvent event) {
        System.out.println(Arrays.deepToString(this.board.getValues()));
    }

    private void setValues(int[][] values) {
        one.setText(values[0][0] != 0 ? String.valueOf(values[0][0]) : "");
        two.setText(values[0][1] != 0 ? String.valueOf(values[0][1]) : "");
        three.setText(values[0][2] != 0 ? String.valueOf(values[0][2]) : "");
        four.setText(values[1][0] != 0 ? String.valueOf(values[1][0]) : "");
        five.setText(values[1][1] != 0 ? String.valueOf(values[1][1]) : "");
        six.setText(values[1][2] != 0 ? String.valueOf(values[1][2]) : "");
        seven.setText(values[2][0] != 0 ? String.valueOf(values[2][0]) : "");
        eight.setText(values[2][1] != 0 ? String.valueOf(values[2][1]) : "");
        nine.setText(values[2][2] != 0 ? String.valueOf(values[2][2]) : "");

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int block = (i + i + i) + (j + 1);
                if (values[i][j] == 0) {
                    switch (block) {
                        case 1:
                            one.setStyle("-fx-border-color:#e9c46a;-fx-border-radius:5;-fx-border-width:3;-fx-border-insets:0;-fx-background-radius: 5px;-fx-pref-height: 90px;-fx-pref-width: 90px;");
                            break;
                        case 2:
                            two.setStyle("-fx-border-color:#e9c46a;-fx-border-radius:5;-fx-border-width:3;-fx-border-insets:0;-fx-background-radius: 5px;-fx-pref-height: 90px;-fx-pref-width: 90px;");
                            break;
                        case 3:
                            three.setStyle("-fx-border-color:#e9c46a;-fx-border-radius:5;-fx-border-width:3;-fx-border-insets:0;-fx-background-radius: 5px;-fx-pref-height: 90px;-fx-pref-width: 90px;");
                            break;
                        case 4:
                            four.setStyle("-fx-border-color:#e9c46a;-fx-border-radius:5;-fx-border-width:3;-fx-border-insets:0;-fx-background-radius: 5px;-fx-pref-height: 90px;-fx-pref-width: 90px;");
                            break;
                        case 5:
                            five.setStyle("-fx-border-color:#e9c46a;-fx-border-radius:5;-fx-border-width:3;-fx-border-insets:0;-fx-background-radius: 5px;-fx-pref-height: 90px;-fx-pref-width: 90px;");
                            break;
                        case 6:
                            six.setStyle("-fx-border-color:#e9c46a;-fx-border-radius:5;-fx-border-width:3;-fx-border-insets:0;-fx-background-radius: 5px;-fx-pref-height: 90px;-fx-pref-width: 90px;");
                            break;
                        case 7:
                            seven.setStyle("-fx-border-color:#e9c46a;-fx-border-radius:5;-fx-border-width:3;-fx-border-insets:0;-fx-background-radius: 5px;-fx-pref-height: 90px;-fx-pref-width: 90px;");
                            break;
                        case 8:
                            eight.setStyle("-fx-border-color:#e9c46a;-fx-border-radius:5;-fx-border-width:3;-fx-border-insets:0;-fx-background-radius: 5px;-fx-pref-height: 90px;-fx-pref-width: 90px;");
                            break;
                        case 9:
                            nine.setStyle("-fx-border-color:#e9c46a;-fx-border-radius:5;-fx-border-width:3;-fx-border-insets:0;-fx-background-radius: 5px;-fx-pref-height: 90px;-fx-pref-width: 90px;");
                            break;
                    }
                } else {
                    switch (block) {
                        case 1:
                            one.setStyle("-fx-background-color: #e76f51;-fx-background-radius: 5px;-fx-pref-height: 90px;-fx-pref-width: 90px;");
                            break;
                        case 2:
                            two.setStyle("-fx-background-color: #e76f51;-fx-background-radius: 5px;-fx-pref-height: 90px;-fx-pref-width: 90px;");
                            break;
                        case 3:
                            three.setStyle("-fx-background-color: #e76f51;-fx-background-radius: 5px;-fx-pref-height: 90px;-fx-pref-width: 90px;");
                            break;
                        case 4:
                            four.setStyle("-fx-background-color: #e76f51;-fx-background-radius: 5px;-fx-pref-height: 90px;-fx-pref-width: 90px;");
                            break;
                        case 5:
                            five.setStyle("-fx-background-color: #e76f51;-fx-background-radius: 5px;-fx-pref-height: 90px;-fx-pref-width: 90px;");
                            break;
                        case 6:
                            six.setStyle("-fx-background-color: #e76f51;-fx-background-radius: 5px;-fx-pref-height: 90px;-fx-pref-width: 90px;");
                            break;
                        case 7:
                            seven.setStyle("-fx-background-color: #e76f51;-fx-background-radius: 5px;-fx-pref-height: 90px;-fx-pref-width: 90px;");
                            break;
                        case 8:
                            eight.setStyle("-fx-background-color: #e76f51;-fx-background-radius: 5px;-fx-pref-height: 90px;-fx-pref-width: 90px;");
                            break;
                        case 9:
                            nine.setStyle("-fx-background-color: #e76f51;-fx-background-radius: 5px;-fx-pref-height: 90px;-fx-pref-width: 90px;");
                            break;
                    }
                }
            }
        }
    }

    private void can_solve() {
        this.is_solve = puzzleSolver.solve(this.path);
    }
}
