package animalgame.controllers;

import animalgame.game.Game;
import animalgame.game.Gui;
import animalgame.game.Player;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.File;
import java.net.URL;
import java.util.*;

/**
 * Sorts the players that made it until the end and then displays the players
 * placement for the played game.
 */
@SuppressWarnings("GrazieInspection")
public class AfterGameMenuController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private MediaView mediaView;

    private Game game;

    private Media media;
    private File file;
    private MediaPlayer mediaPlayer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.game = Gui.getGameObject();
        sortRemainingPlayersAndAddToResultOrder();
        displayPlayersPlacement();


        file = new File("C:\\Users\\b3nni\\AnimalGame\\src\\main\\resources\\images\\fireworks.mp4");
        media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();
    }

    /**
     * Sorts the players that haven't lost the game according to the money that they have left.
     * The players are then added to the resultOrder list according to their placement.
     */
    private void sortRemainingPlayersAndAddToResultOrder() {
        this.game.getMyPlayerList().sort((o1, o2) -> o1.getMyMoney() - o2.getMyMoney());

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
            if (place == 1) {
                text.setFill(Color.GOLD);
                text.setStyle("-fx-font-size: 15px; "
                                + "-fx-font-weight: bold; "
                                + "-fx-text-fill: #ffffff;");
            }
            else if (place == 2){
                text.setFill(Color.SILVER);
            }
            else if (place == 3){
                text.setFill(Color.valueOf("#cd7f32"));
            }
            text.setStyle("-fx-font-size: 15px; "
                        + "-fx-font-weight: bold;");
            text.setText(place + ". " + this.game.getResultOrder().get(i -1).getMyName()
                    + " (" + this.game.getResultOrder().get(i - 1).getMyMoney() + " AB)");

            anchorPane.getChildren().add(text);

            place++;
            layoutY += 45;
        }
    }

    /**
     * Exits the game.
     */
    public void exitGame() {
        Gui.getStage().close();
    }
}
