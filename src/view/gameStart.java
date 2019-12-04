package view;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import controller.TowerDefController;

import javafx.animation.TranslateTransition;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Point;
import model.Tower;
import model.TowerDefModel;
import model.TowerDefMoveMessage;
import model.TowerMessage;

public class gameStart implements Observer {
	
	public final double RECTSIZE = 70.0f;
	private GridPane grid;
	private ImageView current;
	private Tower currentTower;
	private ImageView sellImg;
	
	private Rectangle[][] rectangles;
	private TowerDefModel model;
	private TowerDefController controller;
	private int totalGold;
	private Label goldL;
	private HBox hb;
	private HBox hb2;
	private Image first;
	private Image second;
	private ImageView firstImg;
	private ImageView secondImg;
	private Image sell;
	private Image health;
	private ImageView healthImg;
	private Label number;
	private Label healL;
	private int heal;
	private Image gold;
	private ImageView goldImg;
	private Label number2;
	
	// private ImageView[][] images;
	
	
	public gameStart() {
		this.model = new TowerDefModel();
		this.controller = new TowerDefController(model);
		controller.buildBasicStage();
		controller.getModel().addObserver(this);
		this.rectangles = new Rectangle[controller.HEIGHT][controller.WIDTH];
		// this.images = new ImageView[controller.HEIGHT][controller.WIDTH];
	}
	
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		TowerDefMoveMessage msg = (TowerDefMoveMessage) arg;
		if (msg instanceof TowerMessage) {

			Tower tower = (Tower) msg.getObj();;
			System.out.println(msg.getMoney());
			if(msg.getMoney()> 0) {
				rectangles[msg.getRow()][msg.getColumn()].setFill(Color.GREEN);
			}else if(msg.getMoney()< 0) {
				rectangles[msg.getRow()][msg.getColumn()].setFill(new ImagePattern(current.getImage()));
			}
			System.out.println(model.getMap().getPlayer().getMoney());
			System.out.println();
			model.getMap().getPlayer().changeMoney(msg.getMoney());
			totalGold = model.getMap().getPlayer().getMoney();
			System.out.println(totalGold);
			
			goldL = new Label(String.valueOf(totalGold));
			goldL.setTextFill(Color.ORANGE);
			goldL.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
			//rectangles[msg.getRow()][msg.getColumn()].setFill(Color.RED);
			// update on stage;
		}
		else { 
			
		}
		
	}
	
	public void createNewGame(Stage stage) {
//		Stage stage = new Stage();
		stage.setTitle("Tower Defense");
		
		BorderPane window = new BorderPane();
		
		GridPane grid = new GridPane();
		grid.setPrefSize(520, 350);
		grid.setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE, null, null)));
		setGridPane(grid);
		
		GridPane grid2 = new GridPane();
		grid2.setPrefSize(520, 75);
		grid2.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		
		
		
		GridPane grid3 = new GridPane();
		grid3.setPrefSize(520, 30);
		grid3.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		
		ArrayList<Tower> availTowers = controller.getModel().getAvailTowers();

		first = new Image("/img/TOWER.png");
		firstImg = new ImageView(first);
		
		second = new Image("/img/tower2.png");
		secondImg = new ImageView(second);
		
		sell = new Image("/img/sell.png");
		sellImg = new ImageView(sell);
		
		health = new Image("/img/health.png");
		healthImg = new ImageView(health);
		
		number = new Label("X");
		number.setTextFill(Color.ORANGE);
		number.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
		
		
		heal = model.getMap().getPlayer().getHealth();
		healL = new Label(String.valueOf(heal));
		healL.setTextFill(Color.ORANGE);
		healL.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
		
		gold = new Image("/img/gold.png");
		goldImg = new ImageView(gold);
		
		
		number2 = new Label("X");
		number2.setTextFill(Color.ORANGE);
		number2.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
		
		
		totalGold = model.getMap().getPlayer().getMoney();
		goldL = new Label(String.valueOf(totalGold));
		goldL.setTextFill(Color.ORANGE);
		goldL.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
			
		
		hb = new HBox();
		hb2 = new HBox();
		HBox hb = new HBox();

		
		firstImg.setFitHeight(50);
		firstImg.setFitWidth(50);
			
		secondImg.setFitHeight(50);
		secondImg.setFitWidth(50);
		
		
		sellImg.setFitHeight(50);
		sellImg.setFitWidth(50);
		
		healthImg.setFitHeight(50);
		healthImg.setFitWidth(50);
		
		goldImg.setFitHeight(50);
		goldImg.setFitWidth(50);
		
		
		hb.getChildren().addAll(firstImg, secondImg, sellImg);
		
		grid2.getChildren().add(hb);
		
		doImg(firstImg, availTowers.get(0));
		doImg(secondImg, availTowers.get(1));
		doImg(sellImg, null);
		

		hb2.getChildren().addAll(healthImg, number, healL, goldImg, number2, goldL);
		
		grid3.getChildren().add(hb2);

		
		Image monster = new Image("/img/monster1.JPG");
		ImageView monsterImg = new ImageView(monster);
		monsterImg.setFitHeight((int) RECTSIZE / 2);
		monsterImg.setFitWidth((int) RECTSIZE / 2);
		this.enemyWave(monsterImg);

		// this.enemyWave(monsterImg);
		grid.getChildren().add(monsterImg);
		

		window.setTop(grid3);
		window.setCenter(grid);
		window.setBottom(grid2);
		
		Scene scene = new Scene(window);
		stage.setScene(scene);
		
		stage.show();
		
		
	}
	
	public void enemyWave(ImageView monsterImg) {
		Path path = new Path();
		path.getElements().add(new MoveTo(0, RECTSIZE + (int) RECTSIZE / 4));
		path.getElements().add(new LineTo(7 * RECTSIZE + (int) RECTSIZE / 2, RECTSIZE + (int) RECTSIZE / 4));
		path.getElements().add(new LineTo(7 * RECTSIZE + (int) RECTSIZE / 2, 4 * RECTSIZE + (int) RECTSIZE / 4));
		path.getElements().add(new LineTo(0, 4 * RECTSIZE + (int) RECTSIZE / 4));
		PathTransition pathTransition = new PathTransition();
		pathTransition.setDuration(Duration.millis(10000));
		pathTransition.setNode(monsterImg);
		pathTransition.setPath(path);
		pathTransition.play();
	}
//	
//	private class AnimationHandler implements EventHandler<ActionEvent> {
//		int tick = 0; // This handle method gets called every 100 ms
//		@Override
//		public void handle(ActionEvent event) {
//			tick++; gc.drawImage(backGround, 0, 0);
//			x += 1.5;
//			y -= 0.08;
//			gc.drawImage(ship, x, y);
//			if (tick > 200) {
//				timeline.stop();
//			}	
//		}
//	}

	
	/**
	 * this class is used to draw circles
	 * @param pane is the gridpane object.
	 */
	public void setGridPane(GridPane grid) {
		//grid.setBackground(new Background(new BackgroundFill(Color.BLUE, null, null)));

//		grid.setHgap(1);
//		grid.setVgap(1);
		Point point;
		Image image = new Image("/img/enemy.png");

		for (int i = 0; i < controller.HEIGHT; i++) {
			for (int j = 0; j < controller.WIDTH; j++) {
				Rectangle rectangle = new Rectangle();
				
				rectangle.setWidth(RECTSIZE);
				rectangle.setHeight(RECTSIZE);

				Point currentPoint = model.getMap().getGraph()[i][j];
				if(currentPoint.isRoad()) {

					rectangle.setFill(Color.WHITE);
					point = model.getMap().getGraph()[i][j];
					if(point.isEnd()) {
						System.out.println("end");
						setHome(rectangle);
					}
					if (point.isStart()) {
						rectangle.setFill(new ImagePattern(image));
					}
				}else {
					rectangle.setFill(Color.GREEN);
					doRectangle(rectangle);
				}
				this.rectangles[i][j] = rectangle;
				grid.add(rectangle, j, i);
				
				
//				ImageView currImage = new ImageView();
//				Point currentPoint = model.getMap().getGraph()[i][j];
//				if (currentPoint.isRoad()) {
//					Image road = new Image();
//					currImage.setImage(road);
//					//if (currentPoint.isStart()) {
//					//	Image start = new Image();
//					//	currImage.setImage(start);
//					//}
//					if (currentPoint.isEnd()) {
//						Image end = new Image("/img/end.png");
//						currImage.setImage(start);
//					}
//				}
//				else {
//					Image land = new Image();
//					currImage.setImage(land);
//				}
//				currImage.setFitHeight(70.0f);
//				currImage.setFitWidth(70.0f);
//				this.images[i][j] = currImage;
//				grid.add(currImage, j, i);
			}
		}
	}
	
	
	private void doImg(ImageView image, Tower currTower) {
		
		image.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub 
				if ((image == sellImg && currTower == null)
					|| controller.canBuyTower(currTower)) {
					image.setFitHeight(65);
					image.setFitWidth(65);
					if(image == sellImg) {
						Tooltip.install(image, new Tooltip("Sell tower: -20% of original price"));
					}
					else {
						Tooltip.install(image, new Tooltip("$" + currTower.getCost()));
					}
				}

				
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
				if (image == sellImg && currTower == null 
					|| controller.canBuyTower(currTower)) {
					current = image;
					currentTower = currTower;
					System.out.println("current changed");
				}
				else {
					System.out.println("not changed");
				}
			}
			
		});
		
	}
	
	
	private void doRectangle(Rectangle ret) {
		ret.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				int x = 0, y = 0;
				for (int i = 0; i < controller.HEIGHT; i++) {
					for (int j = 0; j < controller.WIDTH; j++) {
						if (rectangles[i][j].equals(ret)) {
							x = i;
							y = j;
						}
					}
				}
				if(current == sellImg) {
					if (controller.getModel().getMap().getGraph()[x][y].getTower() != null) {
						controller.sellTower(x, y);
						current = null;
					}
					//ret.setFill(Color.GREEN);
				}
				else if (controller.canSetTower(x,y) && currentTower != null) {
					controller.buildTower(x, y, currentTower);
					current = null;
					currentTower = null;
					//ret.setFill(new ImagePattern(current.getImage()));
				}
			}
			
		});
	}
	
	
	private void setHome(Rectangle ret) {
		Image image = new Image("/img/home.png");
		ret.setFill(new ImagePattern(image));
	}
}
