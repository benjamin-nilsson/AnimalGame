package javacode;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.*;
import javafx.scene.text.Text;
public class GameController {
    @FXML
    private TextField numberOfPlayers, turns;

    @FXML
    private Text errorText, emptyFieldError;

    @FXML
    private Button createPlayersButton;

    public void openTurnScene() throws Exception {
        createPlayersButton.disableProperty().bind(
                Bindings.isEmpty(numberOfPlayers.textProperty())
                        .or(Bindings.isEmpty(turns.textProperty()))
        );


        if (createPlayersButton.isDisabled()) {
            emptyFieldError.setVisible(true);
        }

        try {
            int players = Integer.parseInt(numberOfPlayers.getText());
            var numberOfTurns = Integer.parseInt(turns.getText());
            if (players < 2 || players > 4 || numberOfTurns < 5 || numberOfTurns > 30) {
                errorText.setVisible(true);
                emptyFieldError.setVisible(false);
                return;
            }

            if (!createPlayersButton.isDisabled()) {
                Game.setMyPlayerList(players);
                Game.setTurns(numberOfTurns);
                SceneCreator.launchScene("/scenes/CreatePlayersScene.fxml");
            }
        } catch (NumberFormatException ignore) {}

    }

    public void exitGame(MouseEvent mouseEvent) {
        Game.getStage().close();
    }
}
