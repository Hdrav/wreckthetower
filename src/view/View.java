package view;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.sceneloader.SceneLoader;

public interface View {
	
    Stage getPrimaryStage(); //prima scena

    Stage getSecondaryStage(); //seconda scena
 
    SceneLoader getSceneLoader();
}
