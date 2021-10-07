package javacode;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

/**
 * Creates new scenes. This class is inherited every time a new scene is launched.
 *
 * @author Benjamin Nilsson
 */
public class SceneCreator {
    /**
     * building the scene that is passed and setting the value for the instance variable loader
     *
     * @param sceneName launching the new scene based on the .fxml file name passed in the argument as a String variable
     * @throws Exception
     */
    public static void launchScene(String sceneName) throws Exception {
        var createScene = new CreateScene();
        // todo: fix a class that creates the first scenebuilder and that we can save variables in. Maybe game?
        FXMLLoader loader = new FXMLLoader(CreateScene.class.getResource(sceneName));
        createScene.setRoot(loader.load());
        Scene scene = new Scene(createScene.getRoot());
        createScene.getStage().setScene(scene);
        createScene.getStage().show();
    }
}