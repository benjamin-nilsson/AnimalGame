package animalgame.controllers;

import animalgame.game.Game;
import animalgame.game.Gui;
import animalgame.game.SceneCreator;
import animalgame.game.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.*;

/**
 * Sorts the players that made it until the end and then displays the players
 * placement for the played game.
 */
public class AfterGameMenuController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    private Game game;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.game = Gui.getGameObject();
        sortRemainingPlayersAndAddToResultOrder();
        displayPlayersPlacement();
    }

    /**
     * Sorts the players that haven't lost the game according to the money that they have left.
     * The players are then added to the resultOrder list according to their placement.
     */
    private void sortRemainingPlayersAndAddToResultOrder() {
        this.game.getMyPlayerList().sort(new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                return o1.getMyMoney() - o2.getMyMoney();
            }
        });

        for (Player player : this.game.getMyPlayerList()) {
            this.game.getResultOrder().add(player);
        }
    }

    /**
     * Displays the players placement in the game.
     */
    private void displayPlayersPlacement() {
        int place = 1;
        int layoutY = 100;
        // as the losers are added first in the list when they lose we go through the list backwards.
        for (int i = this.game.getResultOrder().size(); i > 0; i--) {
            Text text = new Text();
            text.setLayoutX(339);
            text.setLayoutY(layoutY);
            text.setStyle("-fx-font-size: 15px; "
                    + "-fx-font-weight: bold;");
            text.setText(String.valueOf(place)+ ". " + this.game.getResultOrder().get(i -1).getMyName()
                    + " (" + String.valueOf(this.game.getResultOrder().get(i-1).getMyMoney()) + " AB)");

            anchorPane.getChildren().add(text);

            place++;
            layoutY += 45;
        }
    }

    /**
     * Resets all the game data and launches the StartGameMenuScene.
     * @param actionEvent Action event represents a click on the start game button.
     * @throws Exception
     */
    public void openStartMenu(ActionEvent actionEvent) throws Exception {
        // todo: reset all fields.
        SceneCreator.launchScene("/scenes/StartGameMenuScene.fxml");
    }

    /**
     * Exits the game.
     */
    public void exitGame() {
        Gui.getStage().close();
    }
}
