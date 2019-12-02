package view.utilities;

public enum SceneTypes {

	 /**
     * To get StartWindow fxml file.
     */
    STARTWINDOW("MainWindow.fxml"),
    
    STARTMENU("StartMenu.fxml"),
    /**
     * To get ChooseUnits fxml file.
     */
    CHOOSEUNITS("ChooseUnits.fxml");
   

	  private static final String SCENE_PATH = "/FXMLfiles/";
	    private final String selectedScene;

	    SceneTypes(final String sceneName) { 
	        this.selectedScene = sceneName;  
	    }
	    /**
	     *
	     * @return the path to fxml file
	     */
	    public String getPath() { 
	        return SceneTypes.SCENE_PATH + this.selectedScene;
	    
	    }
	
	
	
}
