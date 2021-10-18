package javacode;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SaveGameController implements Initializable {
    @FXML
    private Button saveGame;

    @FXML
    private TextField gameName;


    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    public void saveCurrentGame() {
        Game.saveGame(gameName.getText() + ".txt");
    }
}
