
import javafx.application.*;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.stage.*;
import javafx.scene.layout.AnchorPane;


public class GameWindow extends Application{
	/* 
	*
	*  class who start the application the method FXMLLoader load a FXML file 
	*  with the implementation of all the nodes of the javafx application
	*
	*
	*/
	public void start(Stage stage) throws Exception {
		
		AnchorPane root = FXMLLoader.load(ClassLoader.getSystemResource("FXMLfiles/MainWindow.fxml"));
        Scene scene=new Scene(root,1280,720);
        
        
		stage.setTitle("Wreck the Tower") ;
		stage.setScene(scene);
		stage.show() ;
	}
	
	public static void main ( String [] args ) {
		launch(args);
	}
}
