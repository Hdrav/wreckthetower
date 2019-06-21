import java.io.IOException;

import javafx.stage.Stage;


public interface SceneLoader {

	  /**
    *
    * @return the primary stage of the view
    */
   Stage getStage();

   /**
    *
    * @param scene
    *            the scene to load on the stage
    * @throws IOException
    *             if an I/O error occurs
    */
   void load(SceneTypes scene) throws IOException;
}
