package animalgame.game;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * Creates new scenes. This class is inherited every time a new scene is launched.
 * @author Lara Ibrahim, William Hökegård, Benjamin Nilsson, Fredrik Jonsson.
 */
public class SceneCreator {
    private Game game;
    /**
     * Building the scene that is passed and setting the value for the instance variable loader
     *
     * @param sceneName launching the new scene based on the .fxml file name passed in the argument as a String variable
     * @throws Exception
     */
    public static void launchScene(String sceneName) throws Exception {
        FXMLLoader loader = new FXMLLoader(Gui.class.getResource(sceneName));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Gui.getStage().setScene(scene);
        Gui.getStage().show();
    }
}
