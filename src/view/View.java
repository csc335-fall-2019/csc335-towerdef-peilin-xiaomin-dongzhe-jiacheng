package view;

import java.awt.Toolkit;
import java.util.Observable;
import java.util.Observer;

import controller.TowerDefController;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Tower;
import model.TowerDefModel;
import model.TowerDefMoveMessage;
import model.TowerMessage;

public class View extends Application implements Observer{
	
	private GridPane grid;

	private ImageView current;
	
	private Rectangle[][] rectangles;
	private TowerDefModel model;
	private TowerDefController controller;
	

	public View() {
		this.rectangles = new Rectangle[10][20];
		this.model = new TowerDefModel();
		this.controller = new TowerDefController(model);
		controller.buildBasicStage();
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		TowerDefMoveMessage msg = (TowerDefMoveMessage) arg;
		if (msg instanceof TowerMessage) {

			Tower tower = (Tower) msg.getObj();;
			if(msg.getMoney()> 0) {
				rectangles[msg.getRow()][msg.getColumn()].setFill(Color.GREEN);
			}else if(msg.getMoney()< 0) {
				rectangles[msg.getRow()][msg.getColumn()].setFill(new ImagePattern(current.getImage()));
			}

			model.getMap().getPlayer().changeMoney(msg.getMoney());
			//rectangles[msg.getRow()][msg.getColumn()].setFill(Color.RED);
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
		
		mouseClick(text,stage, image3);
	
		
		
		Scene scene = new Scene(window);
		stage.setScene(scene);
		stage.show();
		
	}
	
	
	
	private void mouseClick(Button label, Stage stage, Image image) {
		label.setOnMouseClicked((event) -> {
			stage.close();
			createNewGame();
		});
	}
	
	
	private void createNewGame() {
		Stage stage = new Stage();
		stage.setTitle("Tower Defense");
		
		BorderPane window = new BorderPane();
		
		
		GridPane grid = new GridPane();
		grid.setPrefSize(800, 350);
		grid.setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE, null, null)));
		this.setGridPane(grid);
		
		GridPane grid2 = new GridPane();
		grid2.setPrefSize(800, 75);
		grid2.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		
		Image first = new Image("/img/TOWER.png");
		ImageView firstImg = new ImageView(first);
		
		Image second = new Image("/img/tower2.png");
		ImageView secondImg = new ImageView(second);
		
		
		
		
		
		HBox hb = new HBox();
		
		firstImg.setFitHeight(50);
		firstImg.setFitWidth(50);
		
		
		secondImg.setFitHeight(50);
		secondImg.setFitWidth(50);
		hb.getChildren().addAll(firstImg, secondImg);
		grid2.getChildren().add(hb);
		
		doImg(firstImg);
		doImg(secondImg);
		
		window.setTop(grid);
		window.setBottom(grid2);
		
		Scene scene = new Scene(window);
		stage.setScene(scene);
		
		stage.show();
		
		
	}
	
	
	/**
	 * this class is used to draw circles
	 * @param pane is the gridpane object.
	 */
	public void setGridPane(GridPane grid) {
		//grid.setBackground(new Background(new BackgroundFill(Color.BLUE, null, null)));
		grid.setHgap(1);
		grid.setVgap(1);
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 20; j++) {
				Rectangle rectangle = new Rectangle();
				rectangle.setWidth(30.0f);
				rectangle.setHeight(30.0f);
				
				if(model.getMap().getGraph()[i][j].isRoad()) {
					rectangle.setFill(Color.WHITE);
				}else {
					rectangle.setFill(Color.GREEN);
					doRectangle(rectangle);
				}
				this.rectangles[i][j] = rectangle;
				grid.add(rectangle, j, i);
			}
		}
	}

	private void doImg(ImageView image) {
		
		image.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				image.setFitHeight(65);
				image.setFitWidth(65);
				
			}
			
		});
		
		image.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				image.setFitHeight(50);
				image.setFitWidth(50);
				
			}
			
		});
		
		image.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				current = image;
				System.out.println("current changed");
			}
			
		});
		
	}
	
	private void doRectangle(Rectangle ret) {
		ret.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				ret.setFill(new ImagePattern(current.getImage()));
			}
			
		});
	}
	
	
	
}
