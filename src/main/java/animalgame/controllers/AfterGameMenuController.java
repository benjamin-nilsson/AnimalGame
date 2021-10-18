package animalgame.controllers;

import animalgame.game.Game;
import animalgame.game.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.*;

public class AfterGameMenuController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button exitGameButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Game.getMyPlayerList().sort(new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                return o1.getMyMoney() - o2.getMyMoney();
            }
        });

        for (Player player : Game.getMyPlayerList())
            Game.getResultOrder().add(player);

        int place = 1;
        int layoutY = 100;
        for (int i = Game.getResultOrder().size(); i > 0; i--) {
            Text text = new Text();
            text.setLayoutX(339);
            text.setLayoutY(layoutY);
            text.setStyle("-fx-font-size: 15px; "
                    + "-fx-font-weight: bold;");
            text.setText(String.valueOf(place)+ ". " + Game.getResultOrder().get(i -1).getMyName()
                            + " (" + String.valueOf(Game.getResultOrder().get(i-1).getMyMoney()) + " AB)");
            anchorPane.getChildren().add(text);
            place++;
            layoutY += 45;
        }

        exitGameButton.setOnMouseClicked(event -> Game.getStage().close());
    }

    public void openTurnScene(ActionEvent actionEvent) {
        // todo: make it so you can play again with the same players from the start.
    }
}
