package animalgame.controllers;

import animalgame.game.Game;
import animalgame.game.SceneCreator;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Saves the games data to a text file.
 */
public class SaveGameController {

    @FXML
    private TextField gameName;
    private Game game;

    // should the savefile really be of .txt?
    /**
     * Saves the games data to a text file that the user gets to name.
     * Launches the playerTurnMenuScene.
     * @throws Exception
     */
    public void saveCurrentGame() throws Exception{
        this.game.saveGame(gameName.getText() + ".txt");
        SceneCreator.launchScene("/scenes/PlayerTurnMenuScene.fxml");
    }
}
