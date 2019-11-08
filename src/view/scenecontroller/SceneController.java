package view.scenecontroller;

import view.SceneLoader;

public interface SceneController {
    
	SceneLoader getSceneLoader();

	    /**
	     *
	     * @param sceneLoader
	     *            scene loader.
	     */
	void setSceneLoader(SceneLoader sceneLoader);

	
}
