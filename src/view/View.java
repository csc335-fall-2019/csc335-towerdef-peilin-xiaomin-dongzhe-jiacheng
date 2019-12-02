package view;

import java.util.Observable;
import java.util.Observer;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class View extends Application implements Observer{
	
	private GridPane grid;
	
	public View() {
		
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		stage.setTitle("Tower Defense");
		
		BorderPane window = new BorderPane();
		grid = new GridPane();
		grid.setPrefSize(600, 400);
		window.setCenter(grid);
	
		ImageView img = new ImageView();
		Image image = new Image("start.jpg");
		img.setImage(image);
		
		window.getChildren().add(img);
		
		Circle circle = new Circle();
		circle.setRadius(20);
		grid.getChildren().add(circle);
		TranslateTransition trans = new TranslateTransition();
		trans.setDuration(Duration.millis(2000));
		trans.setNode(circle);
		trans.setByX(200);
		trans.setCycleCount(5);
		trans.setAutoReverse(false);
		trans.play();
		
		grid.setAlignment(Pos.CENTER);
		
		
		
		
		
		
		
		
		
		Scene scene = new Scene(window);
		stage.setScene(scene);
		stage.show();
		
	}
}
