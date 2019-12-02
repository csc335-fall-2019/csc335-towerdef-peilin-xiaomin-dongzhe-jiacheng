package view;

import java.awt.Toolkit;
import java.util.Observable;
import java.util.Observer;

import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Tower;
import model.TowerDefMoveMessage;
import model.TowerMessage;

public class View extends Application implements Observer{
	
	private GridPane grid;
	
	public View() {
		
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		TowerDefMoveMessage msg = (TowerDefMoveMessage) arg;
		if (msg instanceof TowerMessage) {
			Tower tower;
			if ((Tower) msg.getObj() != null) {
				tower = (Tower) msg.getObj();
			}
			// update on stage;
		}
		else { 
			
		}
		
	}

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		stage.setTitle("Tower Defense");
		
		BorderPane window = new BorderPane();
		grid = new GridPane();
		grid.setPrefSize(800, 450);
		
		window.setCenter(grid);

		
		Circle circle = new Circle();
		circle.setRadius(20);
		grid.getChildren().add(circle);
		
		
		Image image = new Image("/img/background.jpg");
		
		
		grid.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT
				,BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));


		Image image2 = new Image("/img/newbullet.png");
		ImageView img2 = new ImageView(image2);
<<<<<<< HEAD
		img2.setFitHeight(20);
		img2.setFitWidth(30);
		grid.getChildren().add(img2);

=======
		img2.setFitHeight(50);
		img2.setFitWidth(50);
		
		
>>>>>>> refs/heads/master
		TranslateTransition trans = new TranslateTransition();
		trans.setDuration(Duration.millis(2000));
		trans.setNode(img2);
		trans.setByX(600);
		trans.setCycleCount(Timeline.INDEFINITE);
		trans.setAutoReverse(false);
		trans.play();
		
		Image image3 = new Image("/img/TOWER.png");
		ImageView img3 = new ImageView(image3);
		img3.setFitHeight(120);
		img3.setFitWidth(120);
		
		Image image4 = new Image("/img/enemy.png");
		ImageView img4 = new ImageView(image4);
		img4.setFitHeight(80);
		img4.setFitWidth(60);
		
		
		Button text = new Button("NEW GAME");
		text.setFont(Font.font("Verdana", FontWeight.BOLD, 45));
		text.setStyle("-fx-focus-color: transparent;");
		
		VBox vb = new VBox();
		HBox hb1 = new HBox();
		HBox hb2 = new HBox();
		HBox hb3 = new HBox();
		
		hb1.getChildren().add(text);
		hb1.setAlignment(Pos.CENTER);
		
		hb2.getChildren().add(img3);
		hb2.getChildren().add(img2);
		
		
		vb.getChildren().addAll(hb1,hb2);
		
		grid.setAlignment(Pos.CENTER);
		grid.setPadding(new Insets(30));
		grid.setHgap(100);
		grid.setVgap(100);
		grid.getChildren().add(vb);
		
<<<<<<< HEAD
		
		Label text = new Label("NEW GAME");
		text.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		grid.getChildren().add(text);
=======
		mouseClick(text,stage, image3);
	
>>>>>>> refs/heads/master
		
		
		Scene scene = new Scene(window);
		stage.setScene(scene);
		stage.show();
		
	}
	
	
	
	private void mouseClick(Button label, Stage stage, Image image) {
		label.setOnMouseClicked((event) -> {
			stage.close();
			createNewGame(image);
		});
	}
	
	
	private void createNewGame(Image image) {
		Stage stage = new Stage();
		stage.setTitle("Tower Defense");
		
		BorderPane window = new BorderPane();
		
		GridPane grid = new GridPane();
		grid.setPrefSize(800, 350);
		grid.setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE, null, null)));
		
		GridPane grid2 = new GridPane();
		grid2.setPrefSize(800, 100);
		grid2.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		
		ImageView img3 = new ImageView(image);
		grid2.getChildren().add(img3);
		window.setTop(grid);
		window.setBottom(grid2);
		
		Scene scene = new Scene(window);
		stage.setScene(scene);
		
		stage.show();
		
		
	}
}

