package view;

import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameMenu{

	
	private GameStage newGame = new GameStage();
	public GameMenu(Stage stage, BorderPane window) {
		basicStage(stage, window,"English");
		
	}
//	
//	private void languageClick(BorderPane window, Button language, Stage stage) {
//		// TODO Auto-generated method stub
//		language.setOnMouseClicked((event) -> {
//			stage.close();
//			
//		});
//		
//	}
	
	
//	private String choiceLanguage() {
//		String language = null;
//		Stage stage = new Stage();
//		Button english = new Button("English");
//		Button chinese = new Button("Chinese");
//
//		return language;
//	}

	private void mouseClick(Button label, Stage stage, Image image) {
		label.setOnMouseClicked((event) -> {
			stage.close();
			newGame.createNewGame(stage);
		});
	}
	
	
	private void basicStage(Stage stage, BorderPane window, String lang) {
		Button text;
		Button language;
		stage.setTitle("Tower Defense");
		GridPane grid = new GridPane();
		grid = new GridPane();
		grid.setPrefSize(800, 450);
		
		window.setCenter(grid);
		Image image = new Image("/img/background.jpg");
		
		
		grid.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT
				,BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));


		Image image2 = new Image("/img/newbullet.png");
		ImageView img2 = new ImageView(image2);
		img2.setFitHeight(50);
		img2.setFitWidth(50);
		
		
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
		
//		if(lang.equals("English")) {
//			text = new Button("NEW GAME");
//			language = new Button("Language");
//		}
//		else if(lang.equals("Chinese")) {
//			text = new Button("新游戏");
//			language = new Button("语言");
//		}
		
		text = new Button("NEW GAME");
		language = new Button("Language");
		text.setFont(Font.font("Verdana", FontWeight.BOLD, 45));
		text.setStyle("-fx-focus-color: transparent;");
		
		
		language.setFont(Font.font("Verdana", FontWeight.BOLD, 45));
		language.setStyle("-fx-focus-color: transparent;");
		
		VBox vb = new VBox();
		HBox hb1 = new HBox();
		HBox hb2 = new HBox();
		HBox hb3 = new HBox();
		
		hb1.getChildren().add(text);
		hb1.setAlignment(Pos.CENTER);
		
		
		hb2.getChildren().add(language);
		hb2.setAlignment(Pos.CENTER);
		
		hb3.getChildren().add(img3);
		hb3.getChildren().add(img2);
		
		
		vb.getChildren().addAll(hb1,hb2,hb3);
		
		grid.setAlignment(Pos.CENTER);
		grid.setPadding(new Insets(30));
		grid.setHgap(100);
		grid.setVgap(100);
		grid.getChildren().add(vb);
		
		mouseClick(text,stage, image3);
//		languageClick(language, stage);
	}
	
}
