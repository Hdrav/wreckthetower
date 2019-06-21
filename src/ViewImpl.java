import java.io.IOException;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;





public class ViewImpl extends Application implements View {

	private Stage primaryStage;
	private Stage secondaryStage;
	 private SceneLoader sceneLoader;
	
	
	public ViewImpl() {
		
		super();
		this.secondaryStage=new Stage();
		this.secondaryStage.setTitle("Units Selection");
		this.secondaryStage.setResizable(false);
	}
	
	
	public Stage getPrimaryStage() {
		return this.primaryStage;
	}

    public Stage getSecondaryStage() {
		return this.secondaryStage;
    }

    public SceneLoader getSceneLoader() {
		return this.sceneLoader;
    	
    }
    
    
	@Override
    public final void start(final Stage primaryStage) throws IOException {
		  SceneController sceneController;

	        this.primaryStage = primaryStage;
	        this.sceneLoader = new SceneLoaderImpl(this);
	     /*   sceneController = new SceneControllerImpl();
	        sceneController.setSceneLoader(this.sceneLoader);*/

	        this.secondaryStage.setX(0);
	        this.secondaryStage.setY(0);

	        this.primaryStage.setTitle("WRECK THE TOWER");
	        this.primaryStage.setResizable(false);
	        this.primaryStage.centerOnScreen();

	        this.sceneLoader.load(SceneTypes.STARTWINDOW);
	        this.primaryStage.sizeToScene();
	        this.primaryStage.show();
    }
	
	
}



