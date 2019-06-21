import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import view.scenecontroller.SceneController;
import view.utilities.Scenes;



public class SceneLoaderImpl implements SceneLoader {

	 	private final Stage stage;
	    private final Stage secondaryStage;
	    private final View view;
	    private boolean splitScreen;
	
	    public SceneLoaderImpl(final View view) {
	        this.stage = view.getPrimaryStage();
	        this.secondaryStage = view.getSecondaryStage();
	      //  this.alert = new AlertWindowImpl("Exit", "Sei sicuro di voler uscire?", "yesNo"); //inizializza l'alert 
	                                                                                           //se verrà cliccata
	        this.view = view;
	        this.splitScreen = false; //finchè non sono aperte due finistre non devo dividere lo schermo
	    }
 
	    
	    
	@Override
	public Stage getStage() {
		// TODO Auto-generated method stub
		 return this.stage;
	}

	@Override
	public void load(SceneTypes scene) throws IOException { //prende d'ingresso uno degli scheletri FXML 
															//contenuti nell'Enumerazione SceneTypes
		// TODO Auto-generated method stub
		 final Region root; //radice dell'applicazione nella quale dovrà essere caricato il file FXML
	        final FXMLLoader file = new FXMLLoader(); //loader di file FXML 

	        file.setLocation((this.getClass().getResource(scene.getPath()))); //setta la locazione del file FXML
	        																//da caricare nella root
	        System.out.println(scene.getPath());
	        root = file.load();
	        this.stage.setScene(new Scene(root));
	        this.stage.sizeToScene();
	      /*  final SceneController controller = file.getController();
	        controller.setSceneLoader(this.view.getSceneLoader());

	        if ((scene != Scenes.RULEBOOK) && (scene != Scenes.RULEBOOKPAGE2)) {
	            this.stage.setScene(new Scene(root));
	            this.stage.setOnCloseRequest(e -> {
	                if (this.alert.showAndWait().get().getButtonData() == ButtonData.YES) {
	                    Runtime.getRuntime().exit(0);
	                }
	                e.consume();
	                this.stage.sizeToScene();
	            });
	        } else {
	            this.splitScreen = true;
	            this.secondaryStage.setScene(new Scene(root));
	            this.secondaryStage.setOnCloseRequest(e -> {
	                this.splitScreen = false;
	                this.secondaryStage.close();
	            });
	            this.secondaryStage.sizeToScene();
	            this.secondaryStage.show();
	        }

	        this.updateStagePosition();*/
	}
	
	
	
	
}
