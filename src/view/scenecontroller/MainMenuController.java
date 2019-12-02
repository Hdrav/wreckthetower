package view.scenecontroller;

import java.io.IOException;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
//import javafx.scene.control.Button;
import view.utilities.SceneTypes;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;

public class MainMenuController extends SceneControllerImpl {
	
		@FXML
		private VBox levelBox;
	
		@FXML
		private Text levelText;
		
		private Spinner<Integer> levelSpinner;

		private Timeline updateSpinner;
		
		private IntegerSpinnerValueFactory valueFactory;
	    
		public void initialize() throws IOException {
			this.levelText.setFont(new Font("DejaVu Serif Bold",21));
			this.levelText.setFill(Color.BLACK);
			this.levelText.setText("Level:");
			this.levelBox.setBackground(new Background(new BackgroundFill(Color.DIMGREY,null,null)));
			
			this.levelSpinner=new Spinner<>();
			this.levelSpinner.setPrefSize(78, 28);
			this.valueFactory =new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 
        			this.getController().getMaxLevel(), this.getController().getMaxLevel());
			this.levelSpinner.setValueFactory(valueFactory);
			this.levelBox.getChildren().add(levelSpinner);
			this.getController().initializeGame(this.levelSpinner.getValue());
			this.updateSpinner();
		}
		
		
		
		
	    @FXML
	    private void startGame() throws IOException {
	    	
	    	this.getController().initializeGame(this.levelSpinner.getValue());
	    	this.getController().setEquipmentUnitTemplate();
	        this.getSceneLoader().load(SceneTypes.STARTWINDOW);
	    }
	    
	    
	    @FXML
	    private void unitsMenu() throws IOException {
	        this.getSceneLoader().load(SceneTypes.CHOOSEUNITS);
	    }

	    private void updateSpinner() {
	    	this.updateSpinner= new Timeline();
	    	this.updateSpinner.setCycleCount(Animation.INDEFINITE);
	    	this.updateSpinner.getKeyFrames().add(new KeyFrame(Duration.millis(1000),(ActionEvent e)->  {
	    		if(this.getController().getMaxLevel()>this.valueFactory.getMax()) {
	    			this.valueFactory =new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 
	            			this.getController().getMaxLevel(),this.getController().getMaxLevel());
	    			this.levelSpinner.setValueFactory(this.valueFactory);
	    		}
	    	}));
	    	this.updateSpinner.play();
	    }


}
