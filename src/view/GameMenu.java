package view;

import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
import model.BasicTower;

public class GameMenu{

	private Images images;
	private String choice = "English";
	private GameStage newGame;
	private BasicTower tower;
	private Label languageAll;
	private ToggleGroup group;
	private RadioButton English;
	private RadioButton Chinese;
	private ToggleGroup groupLevel;
	private Label levelsAll;
	private RadioButton Level1;
	private RadioButton Level2;
	private RadioButton Level3;
	private Button Levels;
	private int levelNum = 1;
	public GameMenu(Stage stage, BorderPane window) {
		images = new Images();
		basicStage(stage, window,choice);
		
	}
	
	private void languageClick(Button language, Stage stage) {
		// TODO Auto-generated method stub
		language.setOnMouseClicked((event) -> {
			stage.close();
			Stage stageLang = new Stage();
			HBox hb = new HBox();
			
			stageLang.setTitle("Language Setting");
			BorderPane windowLang = new BorderPane();
			
			
			if(choice.equals("English")) {
				languageAll = new Label("Language:");
				group = new ToggleGroup();
				English = new RadioButton("English");
				Chinese = new RadioButton("Chinese");
			}
			else {
				languageAll = new Label("语言:");
				group = new ToggleGroup();
				English = new RadioButton("英语");
				Chinese = new RadioButton("中文");
			} 
			
			
			English.setToggleGroup(group);
			Chinese.setToggleGroup(group);
			hb.getChildren().addAll(languageAll, English, Chinese);
			
			English.setOnMouseClicked((eventE)->{
				choice = "English";
			});
			
			Chinese.setOnMouseClicked((eventC)->{
				choice = "Chinese";
			});
			
			
			VBox vb = new VBox();
		
			Button OK = new Button("OK");
			vb.getChildren().addAll(hb,OK);
			
			windowLang.setCenter(vb);
			Scene scene = new Scene(windowLang, 300, 150);
			stageLang.setScene(scene);
			stageLang.show();
			
			OK.setOnMouseClicked((event2)->{
				stageLang.close();
				System.out.println("nw");
				BorderPane window = new BorderPane();
				basicStage(stage, window, choice);
			});
			
		});
		
	}
	

	private void mouseClick(Button now, Stage stage) {
		now.setOnMouseClicked((event) -> {
			stage.close();
			newGame = new GameStage(levelNum);
			newGame.createNewGame();
		});
	}
	
	
	private void basicStage(Stage stage, BorderPane window, String lang) {
		System.out.println("1");
		Button text;
		Button language;
		stage.setTitle("Tower Defense");
		GridPane grid = new GridPane();
//		System.out.println(2);
		grid = new GridPane();
		grid.setPrefSize(800, 450);
		
		window.setCenter(grid);
//		Image image = new Image("/img/background.jpg");
		
		
		grid.setBackground(new Background(new BackgroundImage(images.getBackImg(), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT
				,BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));


//		Image image2 = new Image("/img/newbullet.png");
//		ImageView img2 = new ImageView(image2);
		images.getNewbullet().setFitHeight(50);
		images.getNewbullet().setFitWidth(50);
		
		
		TranslateTransition trans = new TranslateTransition();
		trans.setDuration(Duration.millis(2000));
		trans.setNode(images.getNewbullet());
		trans.setByX(600);
		trans.setCycleCount(Timeline.INDEFINITE);
		trans.setAutoReverse(false);
		trans.play();
		
//		Image image3 = new Image("/img/TOWER.png");
//		ImageView img3 = new ImageView(image3);
		tower = new BasicTower();
		tower.setImg(new ImageView(new Image("/img/TOWER.png")));
		tower.getImg().setFitHeight(120);
		tower.getImg().setFitWidth(120);
		
		System.out.println(choice);
		if(choice.equals("English")) {
			text = new Button("NEW GAME");
			language = new Button("Language");
		}
		else {
			System.out.println("Chinese");
			text = new Button("新游戏");
			language = new Button("语言");
		}
		

		text.setFont(Font.font("Verdana", FontWeight.BOLD, 45));
		text.setStyle("-fx-focus-color: transparent;");
		
//		
		language.setFont(Font.font("Verdana", FontWeight.BOLD, 45));
		language.setStyle("-fx-focus-color: transparent;");
		
		
		
		VBox vb = new VBox();
		HBox hb1 = new HBox();
		HBox hb2 = new HBox();
		HBox hb3 = new HBox();
		HBox hb4 = new HBox();
		
		hb1.getChildren().add(text);
		hb1.setAlignment(Pos.CENTER);
		
		hb2.getChildren().add(language);
		hb2.setAlignment(Pos.CENTER);
		
		hb3.getChildren().add(tower.getImg());
		hb3.getChildren().add(images.getNewbullet());
		
		Levels = new Button("Levels");
		
		hb4.getChildren().add(Levels);
		
		vb.getChildren().addAll(hb1,hb2,hb3,hb4);
		
		
		grid.setAlignment(Pos.CENTER);
		grid.setPadding(new Insets(30));
		grid.setHgap(100);
		grid.setVgap(100);
		grid.getChildren().add(vb);
		
		mouseClick(text,stage);
		languageClick(language, stage);
		levelsOnClick(Levels);
		
		Scene scene = new Scene(window);
		stage.setScene(scene);
		stage.show();
	}
	
	private void levelsOnClick(Button levels) {
		levels.setOnMouseClicked((event) ->{
			Stage levelS = new Stage();
			levelS.setTitle("Select Levels");
			BorderPane windowL = new BorderPane();
			HBox hb = new HBox();
			if(choice.equals("English")) {
				levelsAll = new Label("Levels:");
				groupLevel = new ToggleGroup();
				Level1 = new RadioButton("Level1");
				Level2 = new RadioButton("Level2");
				Level3 = new RadioButton("Level3");
			}
			else {
				levelsAll = new Label("关卡:");
				groupLevel = new ToggleGroup();
				Level1 = new RadioButton("关卡1");
				Level2 = new RadioButton("关卡2");
				Level3 = new RadioButton("关卡3");
			} 
			Level1.setToggleGroup(groupLevel);
			Level2.setToggleGroup(groupLevel);
			Level3.setToggleGroup(groupLevel);
			
			Level1.setOnAction((event1) ->{
				levelNum = 1;
			});
			
			Level2.setOnAction((event2) ->{
				levelNum = 2;
			});
			
			Level3.setOnAction((event3) ->{
				levelNum = 3;
			});
			
			
			
			
			hb.getChildren().addAll(levelsAll, Level1, Level2, Level3);
			
			VBox vb = new VBox();
			
			Button OK = new Button("OK");
			vb.getChildren().addAll(hb,OK);
			
			windowL.setCenter(vb);
			Scene scene = new Scene(windowL, 300, 150);
			levelS.setScene(scene);
			levelS.show();
			
			OK.setOnMouseClicked((event2)->{
				levelS.close();
			});
			
			
			
		});
	}
	
}
