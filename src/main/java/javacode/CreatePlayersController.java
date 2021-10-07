package javacode;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class CreatePlayersController implements Initializable {

    @FXML
    private TextField player1, player2, player3, player4;

    @FXML
    private Button startGameButton;

    @FXML
    private Text emptyFieldError;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        var length = Game.getMyPlayerList().length;
        if (length == 2) {
            player3.setVisible(false);
            player4.setVisible(false);
        } else if (length == 3) {
            player4.setVisible(false);
        }
    }

    public void openTurnScene() throws Exception {
        startGameButton.disableProperty().bind(
                Bindings.isEmpty(player1.textProperty())
                        .or(Bindings.isEmpty(player2.textProperty()))
        );

        var length = Game.getMyPlayerList().length;
        Game.addPlayer(new Player(player1.getText()), 0);
        Game.addPlayer(new Player(player2.getText()), 1);
        if (length == 3) {
            startGameButton.disableProperty().bind(
                    Bindings.isEmpty(player3.textProperty())
            );
            Game.addPlayer(new Player(player3.getText()), 2);
        } else if (length == 4) {
            startGameButton.disableProperty().bind(
                    Bindings.isEmpty(player3.textProperty())
                    .or(Bindings.isEmpty(player4.textProperty()))
            );
            Game.addPlayer(new Player(player3.getText()), 2);
            Game.addPlayer(new Player(player4.getText()), 3);
        }

        if (startGameButton.isDisabled()) {
            emptyFieldError.setVisible(true);
        }

        if (!startGameButton.isDisabled()) {
            SceneCreator.launchScene("/scenes/PlayerTurnMenuScene.fxml");
        }
    }

    public void openMainMenuScene(MouseEvent mouseEvent) throws Exception {
        SceneCreator.launchScene("/scenes/StartGameMenuScene.fxml");
    }
}
