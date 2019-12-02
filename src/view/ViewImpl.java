package view;
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import view.scenecontroller.SceneController;
import view.scenecontroller.SceneControllerImpl;
import view.utilities.SceneTypes;






public class ViewImpl extends Application implements View {

	private Stage primaryStage;
	private final Stage secondaryStage;
	private final Stage thirdStage;
	private SceneLoader sceneLoader;
	
	/**
	 * constructor
	 */
	public ViewImpl() {
		
		super();
		this.secondaryStage=new Stage();
		this.secondaryStage.setTitle("Units Selection");
		this.secondaryStage.setResizable(false);
		this.thirdStage=new Stage();
		this.thirdStage.setTitle("Wreck the Tower!");
		this.thirdStage.setResizable(false);
	}
	
	
	public Stage getPrimaryStage() {
		return this.primaryStage;
	}

    public Stage getChooseUnitsStage() {
		return this.secondaryStage;
    }
    
    public Stage getMainWindowStage() {
    	return this.thirdStage;
    }

    public SceneLoader getSceneLoader() {
		return this.sceneLoader;
    	
    }
    
    
    public final void start(final Stage primaryStage) throws IOException {

		    SceneController sceneController;
	        this.primaryStage = primaryStage;
	        SceneLoaderImpl.initializeLog(this);
	        this.sceneLoader=SceneLoaderImpl.getLog();
	        sceneController = new SceneControllerImpl();
	        sceneController.setSceneLoader(this.sceneLoader);
	        this.secondaryStage.setX(0);
	        this.secondaryStage.setY(0);
	        this.primaryStage.setTitle("WRECK THE TOWER");
	        this.primaryStage.setResizable(false);
	        this.primaryStage.centerOnScreen();
	        this.sceneLoader.load(SceneTypes.STARTMENU);
	        this.primaryStage.sizeToScene();
	        this.primaryStage.show();
    }
	
	
}



