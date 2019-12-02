package application;

import controller.ControllerImpl;
import javafx.application.Application;
import view.ViewImpl;
/*
 * starts the application
 * 
 */
public final class Launcher {

	  public static void main(final String[] args) {

	        Application.launch(ViewImpl.class, args);
	        ControllerImpl.getLog();
	    }
	  
	  private Launcher() {
	    }
}
