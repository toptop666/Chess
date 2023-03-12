package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerMenu {

    @FXML
    private ChoiceBox gameModeChoiceBox;
    @FXML
    private Text softwareOutput;


    public void start(ActionEvent event) throws IOException {

        if(this.gameModeChoiceBox.getValue() == null) {
            this.softwareOutput.setText("you have to choose a game mode");
            return;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ControllerGame controller = loader.getController();
        if(((String) this.gameModeChoiceBox.getValue()).equals("Player vs Player")) {
            controller.setGameModePC(false);
        }
        if(((String) this.gameModeChoiceBox.getValue()).equals("Player vs Computer")) {
            controller.setGameModePC(true);
        }
        stage.setScene(scene);
        stage.show();
    }
}
