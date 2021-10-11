package javacode;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    @FXML
    private TextField numberOfPlayers, turns;

    @FXML
    private Text errorText, emptyFieldError;

    @FXML
    private Button createPlayersButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ensureFieldsAreFilledOut();
    }

    private void ensureFieldsAreFilledOut() {
        createPlayersButton.disableProperty().bind(
                Bindings.isEmpty(numberOfPlayers.textProperty())
                        .or(Bindings.isEmpty(turns.textProperty()))
        );
    }

    public void openTurnScene() throws Exception {
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
                Game.setTurns(numberOfTurns);
                Game.setNumberOfPlayers(Integer.parseInt(numberOfPlayers.getText()));
                SceneCreator.launchScene("/scenes/CreatePlayersScene.fxml");
            }
        } catch (NumberFormatException ignore) {}

    }

    public void exitGame(MouseEvent mouseEvent) {
        Game.getStage().close();
    }
}
