package view.scenecontroller;

import controller.Controller;
import controller.ControllerImpl;
import view.SceneLoader;
import view.SceneLoaderImpl;

public class SceneControllerImpl implements SceneController {
	
    private SceneLoader sceneLoader= SceneLoaderImpl.getLog();
    private final Controller controller = ControllerImpl.getLog();

    /**
     *
     * @return the controller instance
     */
    protected Controller getController() {
        return this.controller;
    }

    @Override
    public final SceneLoader getSceneLoader() {
        return this.sceneLoader;
    }
    
    @Override
    public final void setSceneLoader(final SceneLoader sceneLoader) {
        this.sceneLoader = sceneLoader;
    }

}
