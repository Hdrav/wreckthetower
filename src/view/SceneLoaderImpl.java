package view;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import view.scenecontroller.SceneController;
import view.utilities.SceneTypes;





public class SceneLoaderImpl implements SceneLoader {
	private static SceneLoaderImpl loader;
	 	private final Stage stage;
	    private final Stage chooseUnitsStage;
	    private final Stage mainWindowStage;
	    private final View view;
	
	    /*
	     * constructor
	     * */
	    public SceneLoaderImpl(final View view) {
	        this.stage = view.getPrimaryStage();
	        this.chooseUnitsStage = view.getChooseUnitsStage();
	        this.mainWindowStage=view.getMainWindowStage();
	        this.view = view;
	       
	    }
 
	    
	    
	public Stage getStage() {
		 return this.stage;
	}
	
	public Stage getMainWindowStage() {
		return this.mainWindowStage;
	}

	public final void load(final SceneTypes scene) throws IOException { 
		 	final Region root; 
	        final FXMLLoader file = new FXMLLoader(); 

	        file.setLocation((this.getClass().getResource(scene.getPath())));																//da caricare nella root
	        root = file.load();
	        final SceneController controller = file.getController();
	        controller.setSceneLoader(this.view.getSceneLoader());
	        if ((scene == SceneTypes.STARTMENU)) {
	        	
	            	this.stage.setScene(new Scene(root));    
	                this.stage.sizeToScene();
	        }
	        
	        
	        if ((scene == SceneTypes.STARTWINDOW)) {
	            this.mainWindowStage.setScene(new Scene(root));    
	                this.mainWindowStage.sizeToScene();
	                this.mainWindowStage.centerOnScreen();
		            this.mainWindowStage.show();
		          
	        }

	        else if (scene==SceneTypes.CHOOSEUNITS) {
	            this.chooseUnitsStage.setScene(new Scene(root));
	            this.chooseUnitsStage.sizeToScene();
	            this.chooseUnitsStage.centerOnScreen();
	            this.chooseUnitsStage.show();
	           
	        }
	}

	public static synchronized void initializeLog(final View view) {
        if (loader == null) {
            loader = new SceneLoaderImpl(view);
        }
	}
	
	public static SceneLoader getLog() {
		return loader;
	}

	
}
