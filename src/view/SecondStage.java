package view;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import controller.TowerDefController;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;


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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import javafx.util.Duration;
import model.BasicMonster;
import model.BasicTower;
import model.Images;
import model.LoseHealthMessage;
import model.Monster;
import model.MonsterDeadMessage;
import model.MonsterMoveMessage;
import model.Player;
import model.Point;
import model.Tower;
import model.TowerChangeMessage;
import model.TowerDefModel;
import model.TowerDefMoveMessage;
import model.TowerMessage;
import model.Turret;

public class SecondStage implements Observer {
	
	public final double RECTSIZE = 70.0f;
	private GridPane grid;
	private ImageView current;
	private Tower currentTower;
	
	private Thread gameThread;

	private Rectangle[][] rectangles;
	private TowerDefModel model;
	private TowerDefController controller;
	private int totalGold;
	private Label goldL;
	private HBox hb;
	private HBox hb2;
	private Label number;
	private Label healL;
	private int heal;
	private Label number2;
	private GridPane grid2;
	private GridPane grid3;
	private ArrayList<Point> road;
	private ArrayList<Monster> monsters;
	private Label currName;
	private String towername;
	private HashMap<Point,Timeline> BulletsTimeline;
	private HashMap<Point,ImageView> BulletsImageView;
	private Images images;

	
	public SecondStage() {
		this.model = new TowerDefModel();
		this.controller = new TowerDefController(model);
		controller.buildStage();
		controller.getModel().addObserver(this);
		this.rectangles = new Rectangle[controller.HEIGHT][controller.WIDTH];
		// this.images = new ImageView[controller.HEIGHT][controller.WIDTH];
		road = controller.getModel().getMap().getRoad();
		monsters = controller.getModel().getMonsterWaves()[0];
		BulletsTimeline = new HashMap<Point,Timeline>();
		BulletsImageView = new HashMap<Point,ImageView>();
		images = new Images();
	}
	
	
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
		if (arg instanceof TowerChangeMessage) {
			TowerChangeMessage msg = (TowerChangeMessage) arg;
			
			Tower tower = (Tower) msg.getTower();
			// Point point = tower.getPoint();
			if(msg.getMoney()> 0) {
				rectangles[msg.getRow()][msg.getColumn()].setFill(Color.GREEN);
				
			} else if(msg.getMoney()< 0) {
				rectangles[msg.getRow()][msg.getColumn()].setFill(new ImagePattern(current.getImage()));
			}
			//System.out.println(model.getMap().getPlayer().getMoney());
			controller.changeMoney(msg.getMoney());
			totalGold = controller.getModel().getMap().getPlayer().getMoney();
			System.out.println(totalGold);
			
			updateGold();
			
		}
		else if (arg instanceof MonsterDeadMessage) {
			MonsterDeadMessage msg = (MonsterDeadMessage) arg;
			Monster currMonster = msg.getMonster();
			currMonster.getImg().setVisible(false);
			
		}
		else if (arg instanceof MonsterMoveMessage) {
			MonsterMoveMessage msg = (MonsterMoveMessage) arg;
			Point currPoint = msg.getCurrPoint();
			Point nextRoad = msg.getNextRoad();
			Monster monster = msg.getMonster();
			ImageView monsterImg = monster.getImg();
			
			grid.getChildren().remove(monsterImg);
			
			if (currPoint.isStart()) {
				monsterImg.setTranslateX(currPoint.getY()*RECTSIZE+RECTSIZE/4);
				monsterImg.setTranslateY(currPoint.getX()*RECTSIZE);
			}
			else if (nextRoad.isEnd()) {
				monsterImg.setVisible(false);
				// controller.lossHealth(monster);
			}
			else {
				double currX = monsterImg.getTranslateX();
				double currY = monsterImg.getTranslateY();
				if (moveLeft(currPoint, nextRoad)) {
					monsterImg.setTranslateX(currX-1.0);
				}
				else if (moveRight(currPoint, nextRoad)) {
					monsterImg.setTranslateX(currX+1.0);
				}
				else if (moveUp(currPoint, nextRoad)) {
					monsterImg.setTranslateY(currY+1.0);
				}
				else if (moveDown(currPoint, nextRoad)) {
					monsterImg.setTranslateY(currY-1.0);
				}
			}
			grid.getChildren().add(monsterImg);
		}
		else if (arg instanceof LoseHealthMessage) {
			LoseHealthMessage msg = (LoseHealthMessage) arg;
			Monster currMonster = msg.getMonster();
			Player player = msg.getPlayer();
		}
	}
	
	private boolean moveLeft(Point currPoint, Point nextRoad) {
		if(currPoint.getX() == nextRoad.getX() && currPoint.getY() > nextRoad.getY()) {
			return true;
		}
		return currPoint.equals(nextRoad);
	}
	
	private boolean moveRight(Point currPoint, Point nextRoad) {
		if(currPoint.getX() == nextRoad.getX() && currPoint.getY() < nextRoad.getY()) {
			return true;
		}
		
		return currPoint.equals(nextRoad);
	}
	
	private boolean moveUp(Point currPoint, Point nextRoad) {
		if(currPoint.getY() == nextRoad.getY() && currPoint.getX() < nextRoad.getX()) {
			return true;
		}
		return currPoint.equals(nextRoad);
	}
	
	private boolean moveDown(Point currPoint, Point nextRoad) {
		if(currPoint.getY() == nextRoad.getY() && currPoint.getX() > nextRoad.getX()) {
			return true;
		}
		
		return currPoint.equals(nextRoad);
	}
	
	public void updateGold() {
		goldL = new Label(String.valueOf(totalGold));
		goldL.setTextFill(Color.ORANGE);
		goldL.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
		
		grid3.getChildren().clear();
		hb = new HBox();
		hb2 = new HBox();
		hb.getChildren().addAll(images.getHealth(), number, healL);
		hb2.getChildren().addAll(images.getGold(), number2, goldL);
		grid3.add(hb, 0, 0);
		grid3.add(hb2, 1, 0);
		grid3.setHgap(30);
	}
	
	
	
	public void createNewGame(Stage stage) {

		stage.setTitle("Tower Defense");
		
		BorderPane window = new BorderPane();
		
		grid = new GridPane();
		grid.setPrefSize(520, 350);
		grid.setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE, null, null)));
		setGridPane(grid);
		
		grid2 = new GridPane();
		grid2.setPrefSize(520, 75);
		grid2.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		
		grid3 = new GridPane();
		grid3.setPrefSize(520, 30);
		grid3.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		
		ArrayList<Tower> availTowers = controller.getModel().getAvailTowers();

		
//		sell = new Image("/img/sell.png");
//		sellImg = new ImageView(sell);
		
//		health = new Image("/img/health.png");
//		healthImg = new ImageView(health);
		
		number = new Label("X");
		number.setTextFill(Color.ORANGE);
		number.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
		
		
		heal = model.getMap().getPlayer().getHealth();
		healL = new Label(String.valueOf(heal));
		healL.setTextFill(Color.ORANGE);
		healL.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
		
//		gold = new Image("/img/gold.png");
//		goldImg = new ImageView(gold);
		
		
		number2 = new Label("X");
		number2.setTextFill(Color.ORANGE);
		number2.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
		
		
		totalGold = model.getMap().getPlayer().getMoney();
		goldL = new Label(String.valueOf(totalGold));
		goldL.setTextFill(Color.ORANGE);
		goldL.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
		
			
		


		BasicTower firstImg = new BasicTower();
		Turret secondImg = new Turret();
		firstImg.getImg().setFitHeight(50);
		firstImg.getImg().setFitWidth(50);
			
		secondImg.getImg().setFitHeight(50);
		secondImg.getImg().setFitWidth(50);
		
		
		images.getSell().setFitHeight(50);
		images.getSell().setFitWidth(50);
		
		images.getHealth().setFitHeight(50);
		images.getHealth().setFitWidth(50);
		
		images.getGold().setFitHeight(50);
		images.getGold().setFitWidth(50);
		

		grid2.add(firstImg.getImg(),0,0);
		grid2.add(secondImg.getImg(), 1, 0);
		grid2.add(images.getSell(), 2, 0);
		grid2.setHgap(10);
		
		
		doImg(firstImg.getImg(), availTowers.get(0));
		doImg(secondImg.getImg(), availTowers.get(1));
		doImg(images.getSell(), null);
		
		
		hb = new HBox();
		hb2 = new HBox();
		hb.getChildren().addAll(images.getHealth(), number, healL);
		hb2.getChildren().addAll(images.getGold(), number2, goldL);
		grid3.add(hb, 0, 0);
		grid3.add(hb2, 1, 0);
		grid3.setHgap(30);
		
		

		window.setTop(grid3);
		window.setCenter(grid);
		window.setBottom(grid2);
		
		Scene scene = new Scene(window);
		stage.setScene(scene);
		
		stage.show();
		
		AnimationTimer at = new AnimationTimer() {
			@Override
			public void handle(long now) {
				// perform ticksPerFrame ticks
				// by default this is 1
				for (int i = 0; i < 60; i++) {
					model.tick();
				}
			}
		};
		
		at.start();
		
	}


	
	/**
	 * this class is used to draw circles
	 * @param pane is the gridpane object.
	 */
	public void setGridPane(GridPane grid) {
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
					doRectangle(rectangle, Color.GREEN);
				}
				this.rectangles[i][j] = rectangle;
				grid.add(rectangle, j, i);
			}
		}
	}

	
	private void doImg(ImageView image, Tower currTower) {
		
		image.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub 
				if ((image == images.getSell() && currTower == null)
					|| controller.canBuyTower(currTower)) {
					image.setFitHeight(65);
					image.setFitWidth(65);
					if(image == images.getSell()) {
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
				if (image == images.getSell() && currTower == null 
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
	
	
	private void doRectangle(Rectangle ret, Color color) {
		ret.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				
				if(color == Color.WHITE) {
					
					towername = "Error";
//					System.out.println(towername);
				}
				else {
					
					towername = "GO";
					
					int x = 0, y = 0;
					for (int i = 0; i < controller.HEIGHT; i++) {
						for (int j = 0; j < controller.WIDTH; j++) {
							if (rectangles[i][j].equals(ret)) {
								x = i;
								y = j;
							}
						}
					}
					if(current == images.getSell()) {
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
			}
			
		});
	}
	
	private void setHome(Rectangle ret) {
		Image image = new Image("/img/home.png");
		ret.setFill(new ImagePattern(image));
	}
	
	
}
