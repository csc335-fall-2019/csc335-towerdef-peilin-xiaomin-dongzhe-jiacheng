package view;


import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import javafx.stage.Stage;

public class View extends Application{

	public View() {


	}



	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub

		BorderPane window = new BorderPane();
		
		gameMenu menu = new gameMenu(stage, window);
	
		Scene scene = new Scene(window);
		stage.setScene(scene);
		stage.show();
	}
	
}
