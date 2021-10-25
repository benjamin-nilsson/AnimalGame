package animalgame.controllers;

import animalgame.fileutilities.FilesUtils;
import animalgame.game.*;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Responsible for setting the number of turns and players the game will have. Also responsible for launching
 * a saved game file.
 * @author Lara Ibrahim, William Hökegård, Benjamin Nilsson, Fredrik Jonsson.
 */
public class GameController implements Initializable {

    @FXML
    private TextField player1Text, player2Text, player3Text, player4Text, turns, oldGameFileText;

    @FXML
    private FontAwesomeIconView infoIcon;

    @FXML
    private Button startGameButton;

    @FXML
    private TextArea infoText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ensureAllFieldsAreFilledOut();
        displayInfoToUser();
    }

    /**
     * Ensure that the user can't start the game without filling out the amount of turns it wants to play
     * as well as with how many players.
     */
    private void ensureAllFieldsAreFilledOut() {
        // we need at least 2 players and at least 5 turns
        startGameButton.disableProperty().bind(
                Bindings.isEmpty(player1Text.textProperty())
                        .or(Bindings.isEmpty(player2Text.textProperty()))
                        .or(Bindings.isEmpty(turns.textProperty()))
        );
    }

    /**
     * Makes the infoIcon display the infoText when the mouse is hovering it.
     */
    private void displayInfoToUser() {
        infoIcon.setOnMouseEntered(event -> infoText.setVisible(true));
        infoIcon.setOnMouseExited(event -> infoText.setVisible(false));
    }

    /**
     * Adds filled out players to the Game and makes sure that the numbers of turns
     * is within its specifications.
     * Launches the PlayerTurnMenuScene if the specifications are correct.
     * @throws Exception
     */
    public void openTurnScene() throws Exception {
        // todo: fix so they cant have the same name and that it cant be empty.
        Game.addPlayer(new Player(player1Text.getText()));
        Game.addPlayer(new Player(player2Text.getText()));
        if (!player3Text.getText().isEmpty()) {
            Game.addPlayer(new Player(player3Text.getText()));
        }

        if (!player4Text.getText().isEmpty()) {
            Game.addPlayer(new Player(player4Text.getText()));
        }

        var numberOfTurns = Integer.parseInt(turns.getText());
        if (numberOfTurns < 5 || numberOfTurns > 30) {
            infoText.setVisible(true);
            return;
        }

        if (!startGameButton.isDisabled()) {
            Game.setTurns(numberOfTurns);
            SceneCreator.launchScene("/scenes/PlayerTurnMenuScene.fxml");
        }
    }

    /**
     * Exits the game.
     * @param mouseEvent Mouse event represents a click on the exitGame button.
     */
    public void exitGame(MouseEvent mouseEvent) {
        Gui.getStage().close();
    }

    /**
     * Takes a saved file and instantiates the required values of the Game to enable a user to pick up
     * from where the user last saved the game. Then launches the scene where the user last saved.
     * @throws Exception
     */
    public void loadOldGameFile() throws Exception {
        GameData saveGameData = (GameData) FilesUtils.readFile(oldGameFileText.getText());
        Game.setMyPlayerList(saveGameData.getMyPlayerList());
        Game.setTurns(saveGameData.getTurns());
        Game.setCurrentTurn(saveGameData.getCurrentTurn());
        Game.setCurrentPlayer(saveGameData.getCurrentPlayer());
        Game.setCurrentPlayerIndex(saveGameData.getCurrentPlayerIndex());
        Game.setResultOrder(saveGameData.getResultOrder());
        SceneCreator.launchScene("/scenes/PlayerTurnMenuScene.fxml");
    }
}
