package view;
import java.io.IOException;

import javafx.stage.Stage;
import view.utilities.SceneTypes;


public interface SceneLoader {

	  /**
    *
    * @return the primary stage of the view
    */
   Stage getStage();
/**
 * 
 * @return the main game scene of the view
 */
   Stage getMainWindowStage();
   
   /**
    *
    * @param scene
    *            the scene to load on the stage
    * @throws IOException
    *             if an I/O error occurs
    */
   void load(SceneTypes scene) throws IOException;

 
}

	


