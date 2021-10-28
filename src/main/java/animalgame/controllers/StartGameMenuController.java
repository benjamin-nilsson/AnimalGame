package animalgame.controllers;

import animalgame.game.*;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Responsible for setting the number of turns and players the game will have. Also responsible for launching
 * a saved game file.
 * @author Lara Ibrahim, William Hökegård, Benjamin Nilsson, Fredrik Jonsson.
 */
public class StartGameMenuController implements Initializable {

    @FXML
    private TextField player1Text, player2Text, player3Text, player4Text, turns, oldGameFileText;

    @FXML
    private FontAwesomeIconView infoIcon;

    @FXML
    private Button startGameButton;

    @FXML
    private TextArea infoText;

    private Game game;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ensureAllFieldsAreFilledOut();
        displayInfoToUser();
        this.game = Gui.getGameObject();
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
        // is it a problem if more than one player have the same name?
        // changed Game to this.game

        this.game.addPlayer(new Player(player1Text.getText()));
        this.game.addPlayer(new Player(player2Text.getText()));
        if (!player3Text.getText().isEmpty()) {
           this.game.addPlayer(new Player(player3Text.getText()));
        }

        if (!player4Text.getText().isEmpty()) {
            this.game.addPlayer(new Player(player4Text.getText()));
        }

        var numberOfTurns = Integer.parseInt(turns.getText());
        if (numberOfTurns < 5 || numberOfTurns > 30) {
            infoText.setVisible(true);
            return;
        }

        if (!startGameButton.isDisabled()) {
            this.game.setTurns(numberOfTurns);
            SceneCreator.launchScene("/scenes/PlayerTurnMenuScene.fxml");
        }
    }

    /**
     * Exits the game.
     */
    public void exitGame() {
        Gui.getStage().close();
    }

    /**
     * Takes a saved file and instantiates the required values of the Game to enable a user to pick up
     * from where the user last saved the game. Then launches the scene where the user last saved.
     */
    public void loadOldGameFile() {
        try {
            this.game.loadOldFile(oldGameFileText.getText());
            this.game.nextScene(this.game.getCurrentScene());
        } catch (Exception e) {
            Alert noFileFound = new Alert(Alert.AlertType.ERROR, "No such file found", ButtonType.OK);

            noFileFound.showAndWait();

            if (noFileFound.getResult() == ButtonType.OK) {
                noFileFound.close();
            }
        }
    }
}

