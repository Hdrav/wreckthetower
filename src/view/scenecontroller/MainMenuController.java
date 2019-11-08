package view.scenecontroller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import view.utilities.SceneTypes;

public class MainMenuController extends SceneControllerImpl {
		private Button resumeGame;

	/*    @FXML
	   private void initialize() {
	        this.resumeGame.setDisable(!this.getController().savedGamePresent());
	    }*/

	    @FXML
	    private void startGame() throws IOException {
	        this.getSceneLoader().load(SceneTypes.STARTWINDOW);
	    }

	    @FXML
	    private void unitsMenu() throws IOException {
	    	if(this.getSceneLoader()==null)
	    		System.out.println("oh wow");
	        this.getSceneLoader().load(SceneTypes.CHOOSEUNITS);
	    }


}
