package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import controller.TowerDefController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;

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
import model.Monster;

import model.Point;
import model.Tower;
import model.TowerDefModel;
import model.TowerDefMoveMessage;
import model.TowerMessage;

public class GameStage implements Observer {
	
	public final double RECTSIZE = 70.0f;
	private GridPane grid;
	private ImageView current;
	private Tower currentTower;
	private ImageView sellImg;

	private Thread gameThread;

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
	private GridPane grid2;
	private GridPane grid3;
	//private int getRoad = 0;
	private ArrayList<Point> road;
	private ArrayList<Monster> monsters;
	private Label currName;
	private String towername;
	private HashMap<Point,Timeline> BulletsTimeline;
	private HashMap<Point,ImageView> BulletsImageView;
	// private ImageView[][] images;
	
	
	public GameStage() {
		this.model = new TowerDefModel();
		this.controller = new TowerDefController(model);
		controller.buildBasicStage();
		controller.getModel().addObserver(this);
		this.rectangles = new Rectangle[controller.HEIGHT][controller.WIDTH];
		// this.images = new ImageView[controller.HEIGHT][controller.WIDTH];
		road = controller.getModel().getMap().getRoads();
		monsters = controller.getModel().getMonsters();
		BulletsTimeline = new HashMap<Point,Timeline>();
		BulletsImageView = new HashMap<Point,ImageView>();
	}
	
	
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		try {
			TowerDefMoveMessage msg = (TowerDefMoveMessage) arg;
			if (msg instanceof TowerMessage) {

				Tower tower = (Tower) msg.getObj();;
				Point point = model.getMap().getGraph()[msg.getRow()][msg.getColumn()];
				if(msg.getMoney()> 0) {
					rectangles[msg.getRow()][msg.getColumn()].setFill(Color.GREEN);
					Timeline BulletTime = BulletsTimeline.get(point);
					ImageView img = BulletsImageView.get(point);
					BulletTime.stop();
					BulletTime = null;
					img.setVisible(false);
					
					img = null;
					BulletsTimeline.remove(point);
					BulletsImageView.remove(point);
				}else if(msg.getMoney()< 0) {
					rectangles[msg.getRow()][msg.getColumn()].setFill(new ImagePattern(current.getImage()));
					Image bImg = new Image("/img/bullet1.JPG");
					ImageView bulletImg = new ImageView(bImg);
					bulletImg.setFitHeight((int) RECTSIZE / 4);
					bulletImg.setFitWidth((int) RECTSIZE / 4);
					BulletHandler move = new BulletHandler(bulletImg,point);
					KeyFrame BulletKey = new KeyFrame(Duration.millis(1000/900),move);
					Timeline BulletTimeline = new Timeline(BulletKey);
					BulletTimeline.setAutoReverse(true);
					BulletTimeline.setCycleCount(Timeline.INDEFINITE);
					move.addTimeline(BulletTimeline);
					BulletTimeline.play();
					BulletsTimeline.put(point, BulletTimeline);
					BulletsImageView.put(point,bulletImg);
					
				}
				//System.out.println(model.getMap().getPlayer().getMoney());
				model.getMap().getPlayer().changeMoney(msg.getMoney());
				totalGold = model.getMap().getPlayer().getMoney();
				System.out.println(totalGold);
				
				goldL = new Label(String.valueOf(totalGold));
				goldL.setTextFill(Color.ORANGE);
				goldL.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
				
				System.out.println(towername);
				currName = new Label(towername);
				currName.setTextFill(Color.ORANGE);
				currName.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
				
				hb2.getChildren().clear();
				hb2.getChildren().addAll(healthImg, number, healL, goldImg, number2, goldL, currName);
				//rectangles[msg.getRow()][msg.getColumn()].setFill(Color.RED);
				// update on stage;
			}
		}catch (Exception e) {
			heal = model.getMap().getPlayer().getHealth();
			healL.setText(String.valueOf(heal));
		
		}
		
	}
	
	public void createNewGame(Stage stage) {
//		Stage stage = new Stage();
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
		
		
		gameThread = new Thread() {
			int count = 0;
			public void run() {
				while(count<monsters.size()&& model.getMap().getPlayer().getHealth() > 0 ) {
					//System.out.println(1);
					createMonster(monsters.get(count));
					count++;
					try {
						Thread.sleep(1300);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		

		gameThread.start();
		//monsterImg.setTranslateX(2*RECTSIZE);
		//monsterImg.setTranslateY(1*RECTSIZE);
//		Image monster = new Image("/img/monster1.JPG");
//		ImageView monsterImg = new ImageView(monster);
//		monsterImg.setFitHeight((int) RECTSIZE / 2);
//		monsterImg.setFitWidth((int) RECTSIZE / 2);
//		this.enemyWave(monsterImg);
		
		

		// this.enemyWave(monsterImg);
		//grid.getChildren().add(monsterImg);
		
		
//		Timeline timeline = new Timeline(
//				new KeyFrame(Duration.millis(100),
//				new AnimationHandler()));
		


		window.setTop(grid3);
		window.setCenter(grid);
		window.setBottom(grid2);
		
		Scene scene = new Scene(window);
		stage.setScene(scene);
		
		stage.show();
		
		
	}

	
	

	
	private void createMonster(Monster monster) {
		if(monster instanceof BasicMonster) {
			ImageView monsterImg = monster.getImg();
			monsterImg.setFitHeight((int) RECTSIZE / 2);
			monsterImg.setFitWidth((int) RECTSIZE / 2);
			MonsterHandler move = new MonsterHandler(monsterImg,monster);
			KeyFrame monsterKey = new KeyFrame(Duration.millis(1000/200),move);
			Timeline monsterTimeline = new Timeline(monsterKey);
			monsterTimeline.setAutoReverse(false);
			monsterTimeline.setCycleCount(Timeline.INDEFINITE);
			move.addTimeline(monsterTimeline);
			monsterTimeline.play();
			//System.out.println(monsterImg.getTranslateX());
		}
		
	}
	
	private class BulletHandler implements EventHandler<ActionEvent>{

		
		ImageView img;
		Point point;
		int count = 0 ;
		Timeline time;
		public BulletHandler(ImageView imgView,Point point) {
			img = imgView;
			this.point = point;
			img.setTranslateX(point.getY()*RECTSIZE+RECTSIZE/8);
			img.setTranslateY(point.getX()*RECTSIZE);
		}
		public void addTimeline(Timeline timeline) {
			time = timeline;
			
		}
		@Override
		public void handle(ActionEvent event) {
			// TODO Auto-generated method stub]
			grid.getChildren().remove(img);
			if(heal == 0) {
				time.stop();
			}
			if(moveLeft()) {
				img.setTranslateX(img.getTranslateX()-1.0);
			}else if(moveRight()) {
				img.setTranslateX(img.getTranslateX()+1.0);
			}else if (moveUp()) {
				img.setTranslateY(img.getTranslateY()+1.0);
			}else if(moveDown()) {
				img.setTranslateY(img.getTranslateY()-1.0);
			}
			
			if(count >=RECTSIZE) {
				count = 0;
				img.setTranslateX(point.getY()*RECTSIZE+RECTSIZE/8);
				img.setTranslateY(point.getX()*RECTSIZE);
			}
			grid.getChildren().add(img);
			count++;
		}
		public void sell() {
			img.setVisible(false);
		}
		private boolean moveDown() {
			try {
				return model.getMap().getGraph()[point.getX()-1][point.getY()].isRoad();
			}catch (Exception e) {
				return false;
			}
		}
		private boolean moveUp() {
			try {
				return model.getMap().getGraph()[point.getX()+1][point.getY()].isRoad();
			}catch (Exception e) {
				return false;
			}
		}
		private boolean moveRight() {
			try {
				return model.getMap().getGraph()[point.getX()][point.getY()+1].isRoad();
			}catch (Exception e) {
				return false;
			}
		}
		
		private boolean moveLeft() {
			try {
				return model.getMap().getGraph()[point.getX()][point.getY()-1].isRoad();
			}catch (Exception e) {
				return false;
			}
		}
	}
	private class MonsterHandler implements EventHandler<ActionEvent> {
		int currentRoad = 0;
		double count = 0.0 ;
		Point nextPoint;
		ImageView img;
		Timeline time;
		Monster monster;
		public MonsterHandler(ImageView monsterImg,Monster monster) {
			nextPoint  = road.get(currentRoad+1);
			this.img = monsterImg;
			img.setTranslateX(road.get(currentRoad).getY()*RECTSIZE+RECTSIZE/4);
			img.setTranslateY(road.get(currentRoad).getX()*RECTSIZE);
			this.monster = monster;
			road.get(currentRoad).setMonster(monster);
		}
		
		@Override
		public void handle(ActionEvent event) {
			//System.out.println(1000);
			grid.getChildren().remove(img);
			
			if(currentRoad == road.size()-1) {
				time.stop();
				img.setVisible(false);
				model.lossHealth(monster);
				//time.setRate(value);
				//System.out.println(1);
			}else if(heal == 0){
				time.stop();
			}else {
				if(moveLeft()) {
					img.setTranslateX(img.getTranslateX()-1.0);
				}else if(moveRight()) {
					img.setTranslateX(img.getTranslateX()+1.0);
				}else if (moveUp()) {
					img.setTranslateY(img.getTranslateY()+1.0);
				}else if(moveDown()) {
					img.setTranslateY(img.getTranslateY()-1.0);
				}
				
				if(count >=RECTSIZE) {
					count = 0;
					road.get(currentRoad).clearMonster(monster);
					currentRoad++;
					road.get(currentRoad).setMonster(monster);
					if(currentRoad < road.size()-1) {
						nextPoint  = road.get(currentRoad+1);
					}
				}
				
			}
			grid.getChildren().add(img);
			count++;
		}
		public void addTimeline(Timeline time) {
			this.time = time;
		}
		private boolean moveLeft() {
			Point point = road.get(currentRoad);
			if(point.getX() == nextPoint.getX() && point.getY() > nextPoint.getY()) {
				return true;
			}
			
			return point.equals(nextPoint);
		}
		private boolean moveRight() {
			Point point = road.get(currentRoad);
			if(point.getX() == nextPoint.getX() && point.getY() < nextPoint.getY()) {
				return true;
			}
			
			return point.equals(nextPoint);
		}
		private boolean moveUp() {
			Point point = road.get(currentRoad);
			if(point.getY() == nextPoint.getY() && point.getX() < nextPoint.getX()) {
				return true;
			}
			
			return point.equals(nextPoint);
		}
		private boolean moveDown() {
			Point point = road.get(currentRoad);
			if(point.getY() == nextPoint.getY() && point.getX() > nextPoint.getX()) {
				return true;
			}
			
			return point.equals(nextPoint);
		}
		
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
			}
			
		});
	}
	
	private void setHome(Rectangle ret) {
		Image image = new Image("/img/home.png");
		ret.setFill(new ImagePattern(image));
	}
}
