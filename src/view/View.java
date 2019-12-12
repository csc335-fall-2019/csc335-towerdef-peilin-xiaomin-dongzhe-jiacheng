package view;

/**
 * @author Peilin Feng
 * @author Xiaomin Zhao
 * @author Dongzhe Chen
 * @author Jiacheng He
 * CSC 335, Fall 2019
 * team project, View.java
 * 
 * purpose: this is the View class which launched by Tower Defense main GUI
 * 			and it will launch the game menu immediately
 */

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import javafx.stage.Stage;

public class View extends Application{

	public View() {
		
	}

	/**
	 * start method which launch the menu stage directly
	 */
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub

		BorderPane window = new BorderPane();
		
		GameMenu menu = new GameMenu(stage, window);
		
		
//		Scene scene = new Scene(window);
//		stage.setScene(scene);
//		stage.show();
	}
	
}
