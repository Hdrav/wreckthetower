
public enum SceneTypes {

	 /**
     * To get StartWindow fxml file.
     */
    STARTWINDOW("StartWindow.fxml"),

    /**
     * To get ChooseUnits fxml file.
     */
    MODALITYMENU("ChooseUnits.fxml");
   

	  private static final String SCENE_PATH = "/FXMLfiles/";
	    private final String selectedScene;

	    SceneTypes(final String sceneName) { //setta il nome della scena passata come argomento (il
	    									//costruttore nelle enumerazioni prende come valori quelli elencati
	    								   //nelle sue enumerazioni. es/: se facessi SceneTypes.STARTWINDOW 
	    								  //inizializzerebbe la scena con il nome del file -> "StartWindow.fxml")
	        this.selectedScene = sceneName;  
	    }
	    /**
	     *
	     * @return the path to fxml file
	     */
	    public String getPath() { //
	        System.out.println(SceneTypes.SCENE_PATH+this.selectedScene);
	        return SceneTypes.SCENE_PATH + this.selectedScene;
	    
	    }
	
	
	
}
