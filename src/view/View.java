package view;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.SceneLoader;

public interface View {
	
    Stage getPrimaryStage(); //prima scena

    Stage getChooseUnitsStage(); //seconda scena
 
    SceneLoader getSceneLoader();

	Stage getMainWindowStage();
}
