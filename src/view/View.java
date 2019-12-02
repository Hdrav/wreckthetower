package view;
import javafx.stage.Stage;
import view.SceneLoader;

public interface View {
	
	/**
    *
    * @return the primary stage
    */
    Stage getPrimaryStage(); 
    
    /**
     * 
     * @return the choose unit stage
     */
    Stage getChooseUnitsStage(); 

    
    /**
    *
    * @return the scene loader
    */
    SceneLoader getSceneLoader();

    /**
     * 
     * @return the main game stage
     */
	Stage getMainWindowStage();
}
