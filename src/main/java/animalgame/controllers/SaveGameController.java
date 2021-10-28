package animalgame.controllers;

import animalgame.game.Game;
import animalgame.game.Gui;
import animalgame.game.SceneCreator;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Saves the games data to a text file.
 */
public class SaveGameController {

    @FXML
    private TextField gameName;

    private final Game game;

    public SaveGameController() {
        this.game = Gui.getGameObject();
    }

    /**
     * Saves the games data to a text file that the user gets to name.
     * Launches the playerTurnMenuScene.
     * @throws Exception
     */
    public void saveCurrentGame() throws Exception{
        this.game.saveGame(gameName.getText() + ".ser");
        SceneCreator.launchScene("/scenes/PlayerTurnMenuScene.fxml");
    }
}
