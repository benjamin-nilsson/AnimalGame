package javacode;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.*;

public class GameController {
    @FXML
    private TextField rounds;

    public void openRoundScene(MouseEvent mouseEvent) throws Exception {
        Game.setTurns(Integer.parseInt(rounds.getText()));
        SceneCreator.launchScene("/scenes/PlayerTurnMenuScene.fxml");
    }
}
