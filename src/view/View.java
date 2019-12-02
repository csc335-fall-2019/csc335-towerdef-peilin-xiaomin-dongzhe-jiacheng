package view;

import java.util.Observable;
import java.util.Observer;


import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
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
		
		
		Image image = new Image("https://img.pcpjrj.com/uploads/allimg/150606/co150606191G1-0.jpg");
		
		grid.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT
				,BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
		
		
		
		
		

		Image image2 = new Image("http://pic.616pic.com/ys_img/00/29/84/RG6pxCBkZs.jpg");
		ImageView img2 = new ImageView(image2);
		img2.setFitHeight(30);
		img2.setFitWidth(30);
		
//		Circle circle = new Circle();
//		circle.setRadius(20);
		grid.getChildren().add(img2);
		
		TranslateTransition trans = new TranslateTransition();
		trans.setDuration(Duration.millis(2000));
		trans.setNode(img2);
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
