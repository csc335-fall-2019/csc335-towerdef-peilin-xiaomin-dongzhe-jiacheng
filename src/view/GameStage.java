package view;

/**
 * @author Peilin Feng
 * @author Xiaomin Zhao
 * @author Dongzhe Chen
 * @author Jiacheng He
 * 
 * purpose:
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.TimeUnit;

import controller.TowerDefController;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;

import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
import model.BasicMonster;
import model.BasicTower;
import model.Monster;
import model.Monster3;
import model.Monster4;
import model.Monster5;
import model.Monster6;
import model.Player;
import model.Point;
import model.SecondMonster;
import model.Tower;
import model.Tower3;
import model.Tower4;
import model.Tower5;
import model.Tower6;
import model.TowerDefModel;
import model.TowerDefMoveMessage;
import model.Turret;

public class GameStage implements Observer {
	private int count = 0;
	public final double RECTSIZE = 70.0f;
	private GridPane grid;
	private ImageView current;
	private Tower currentTower;
	private int stageNum;


	private ImageView sellImg;
	private int SLEEP = 1000;

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
	private HashMap<Point,Timeline> BulletsTimeline;
	private HashMap<Point,ImageView> BulletsImageView;
	private Images images;
	private Stage stage; 

	private VBox vb;
	private boolean isPause = false;
	private ArrayList<Timeline> monstersTimeline;
	

	private VBox vbTime;
	private HBox hb3;
	private HBox hb4;
	private ToggleGroup group;
	private RadioButton two;
	private RadioButton one;

	private GridPane grid4;
	
	private HBox hbGrid;
	private String language;

	
	public GameStage(int stageNum, String choice) {
		this.language = choice;
		this.stage = new Stage();

		this.stageNum = stageNum;
		this.model = new TowerDefModel();
		this.controller = new TowerDefController(model);
		if (stageNum == 1) {
			controller.buildBasicStage(1);
		} else if (stageNum == 2) {
			controller.buildBasicStage(2);
		} else if (stageNum == 3) {
			controller.buildBasicStage(3);
		}
		controller.getModel().addObserver(this);
		this.rectangles = new Rectangle[controller.HEIGHT][controller.WIDTH];
		// this.images = new ImageView[controller.HEIGHT][controller.WIDTH];
		road = controller.getModel().getMap().getRoads();
		monsters = controller.getModel().getMonsters();
		BulletsTimeline = new HashMap<Point,Timeline>();
		BulletsImageView = new HashMap<Point,ImageView>();
		images = new Images();
		monstersTimeline = new ArrayList<Timeline>();
	}
	
	/**
	 * this is update method which let the model send a tower message tell
	 * the GUI to build a new tower or sell an exist tower
	 * and this method will enable the tower's bullet function immediately
	 * after it created.
	 */
	@Override
	public void update(Observable o, Object arg) {
		try {
			TowerDefMoveMessage msg = (TowerDefMoveMessage) arg;

			

			Tower tower =msg.getTower();
			
			if(tower instanceof BasicTower) {
				tower = new BasicTower();
				tower.setImg(new ImageView(new Image("/img/TOWER.png")));
				tower.setBullet(new ImageView(new Image("/img/bullet.png")));
			}else if (tower instanceof Turret) {
				tower = new Turret();
				tower.setImg(new ImageView(new Image("/img/tower2.png")));
				tower.setBullet(new ImageView(new Image("/img/bullet1.jpg")));
			}else if(tower instanceof Tower3) {
				tower = new Tower3();
				tower.setImg(new ImageView(new Image("/img/turret1.jpg")));
				tower.setBullet(new ImageView(new Image("/img/bullet2.jpeg")));
			}else if(tower instanceof Tower4) {
				tower = new Tower4();
				tower.setImg(new ImageView(new Image("/img/turret2.jpg")));
				tower.setBullet(new ImageView(new Image("/img/bullet3.jpg")));
			}else if (tower instanceof Tower5) {
				tower = new Tower5();
				tower.setImg(new ImageView(new Image("/img/turret3.jpg")));
				tower.setBullet(new ImageView(new Image("/img/bullet4.jpg")));
			}else if (tower instanceof Tower6) {
				tower = new Tower6();
				tower.setImg(new ImageView(new Image("/img/turret6.png")));
				tower.setBullet(new ImageView(new Image("/img/bullet6.png")));
			}
			
			Point point = model.getMap().getGraph()[msg.getRow()][msg.getColumn()];
			if(msg.getMoney()> 0) {
				rectangles[msg.getRow()][msg.getColumn()].setFill(new ImagePattern(images.getgameOverback()));
				Timeline BulletTime = BulletsTimeline.get(point);
				ImageView img = BulletsImageView.get(point);
				BulletTime.stop();
				BulletTime = null;
				img.setVisible(false);
				img = null;
				BulletsTimeline.remove(point);
				BulletsImageView.remove(point);
			}
			else if(msg.getMoney()< 0) {
				rectangles[msg.getRow()][msg.getColumn()].setFill(new ImagePattern(current.getImage()));
				ImageView bulletImg = tower.getBullet();
				bulletImg.setFitHeight((int) RECTSIZE / 4);
				bulletImg.setFitWidth((int) RECTSIZE / 4);
				BulletHandler move = new BulletHandler(bulletImg,point,tower);
				KeyFrame BulletKey = new KeyFrame(Duration.millis(5*tower.getSpeed()),move);
				Timeline BulletTimeline = new Timeline(BulletKey);
				BulletTimeline.setAutoReverse(true);
				BulletTimeline.setCycleCount(Timeline.INDEFINITE);
				move.addTimeline(BulletTimeline);
				BulletTimeline.play();
				BulletsTimeline.put(point, BulletTimeline);
				BulletsImageView.put(point,bulletImg);
				//SLEEP = 500;
			}
		    changeGold(msg.getMoney());	
		} catch (Exception e) {
			heal = model.getMap().getPlayer().getHealth();
			healL.setText(String.valueOf(heal)); 
			if(heal <= 0) {
				gameOver(stage);
			}
		}
		
	}
	

	/**
	 * this method create the new Game stage with language setting and basic
	 * game interface. it also create a menu bar to control the game start or
	 * exit. And we set up a game thread to control the monster wave.
	 */
	public void createNewGame() {
		// enable different language label
		if(language.equals("English")) {
			stage.setTitle("Protect Earth");
		}
		else {
			stage.setTitle("保卫地球大作战");
		}
		
		BorderPane window = new BorderPane();
		
		// grid will contain the main game interface
		grid = new GridPane();
		grid.setPrefSize(520, 350);
		grid.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		setGridPane(grid);
		
		// grid2 contain all of the available tower images and sell image as
		// a seleciton bar which let the user can buy and sell towers
		grid2 = new GridPane();
		grid2.setPrefSize(520, 75);
		grid2.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		
		// grid3 contain all health and gold infomation of the current player
		grid3 = new GridPane();
		grid3.setPrefSize(520, 30);
		grid3.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		
		// grid4 contain the pause and fast forward button enable the player
		// to pause and make 2X speed of the game
		grid4 = new GridPane();
		grid4.setPrefSize(180, 50);
		grid4.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		
		ArrayList<Tower> availTowers = controller.getModel().getAvailTowers();

		number = new Label("X");
		number.setTextFill(Color.ORANGE);
		number.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
		
		heal = model.getMap().getPlayer().getHealth();
		healL = new Label(String.valueOf(heal));
		healL.setTextFill(Color.ORANGE);
		healL.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
		
		number2 = new Label("X");
		number2.setTextFill(Color.ORANGE);
		number2.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
		
		totalGold = model.getMap().getPlayer().getMoney();
		goldL = new Label(String.valueOf(totalGold));
		goldL.setTextFill(Color.ORANGE);
		goldL.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
		
		// add all of our tower into selection bar
		BasicTower firstImg = new BasicTower();
		Turret secondImg = new Turret();
		Tower3 tower3 = new Tower3();
		Tower4 tower4 = new Tower4();
		Tower5 tower5 = new Tower5();
		Tower6 tower6 = new Tower6();
		firstImg.setImg(new ImageView(new Image("/img/TOWER.png")));
		firstImg.getImg().setFitHeight(50);
		firstImg.getImg().setFitWidth(50);
		secondImg.setImg(new ImageView(new Image("/img/tower2.png")));
		secondImg.getImg().setFitHeight(50);
		secondImg.getImg().setFitWidth(50);
	
		tower3.setImg(new ImageView(new Image("/img/turret1.jpg")));
		tower3.getImg().setFitHeight(50);
		tower3.getImg().setFitWidth(50);
		tower4.setImg(new ImageView(new Image("/img/turret2.jpg")));
		tower4.getImg().setFitHeight(50);
		tower4.getImg().setFitWidth(50);
		tower5.setImg(new ImageView(new Image("/img/turret3.jpg")));
		tower5.getImg().setFitHeight(50);
		tower5.getImg().setFitWidth(50);
		tower6.setImg(new ImageView(new Image("/img/turret6.png")));
		tower6.getImg().setFitHeight(50);
		tower6.getImg().setFitWidth(50);
		
		
		
		
		
		
		
		images.getSell().setFitHeight(50);
		images.getSell().setFitWidth(50);
		
		images.getHealth().setFitHeight(50);
		images.getHealth().setFitWidth(50);
		
		images.getGold().setFitHeight(50);
		images.getGold().setFitWidth(50);
		
		grid2.add(firstImg.getImg(),0,0);
		grid2.add(secondImg.getImg(), 1, 0);
		grid2.add(tower3.getImg(), 2, 0);
		grid2.add(tower4.getImg(), 3, 0);
		grid2.add(tower5.getImg(), 4, 0);
		grid2.add(tower6.getImg(), 5, 0);
		grid2.add(images.getSell(), 6, 0);
		grid2.setHgap(20);
		
		// let the image on the selection bar be able to be selected
		doImg(firstImg.getImg(), availTowers.get(0));
		doImg(secondImg.getImg(), availTowers.get(1));
		doImg(tower3.getImg(), availTowers.get(2));
		doImg(tower4.getImg(), availTowers.get(3));
		doImg(tower5.getImg(), availTowers.get(4));
		doImg(tower6.getImg(), availTowers.get(5));
		
		doImg(images.getSell(), null);
		
		images.getPauseV().setFitHeight(80);
		images.getPauseV().setFitWidth(100);
		
		// set up fast foward function for the game interface
		group = new ToggleGroup();
		one = new RadioButton("X1");
		two = new RadioButton("X2");
		
		one.setToggleGroup(group);
		two.setToggleGroup(group);
		
		vbTime = new VBox();
		vbTime.getChildren().addAll(one,two);
		
		one.setOnMouseClicked((event)->{
			SLEEP = 1000;
		});
		
		two.setOnMouseClicked((event)->{
			SLEEP = 500;
		});
		hb = new HBox();
		hb2 = new HBox();
		hb3 = new HBox();
		hb4 = new HBox();
		hbGrid = new HBox();
		
		hb.getChildren().addAll(images.getHealth(), number, healL);
		hb2.getChildren().addAll(images.getGold(), number2, goldL);
		hb4.getChildren().add(images.getPauseV());
		hb4.getChildren().add(vbTime);
		
		grid3.add(hb, 0, 0);
		grid3.add(hb2, 1, 0);
		grid3.add(hb3, 2, 0);
		grid4.add(hb4, 0, 0);
		grid3.setHgap(30);
		
		hbGrid.getChildren().addAll(grid3,grid4);

		// gameThread which control the monster waves of the game and pause
		gameThread = new Thread() {
			public void run() {
				while(count<monsters.size()&& model.getMap().getPlayer().getHealth() > 0 ) {

					while(isPause) {
						pause();
					}
					createMonster(monsters.get(count), stage);
					count++;
					try {
						Thread.sleep(SLEEP);
						//System.out.println(count);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
			}
			
			// enable pause function for the game
			private void pause() {
				synchronized (this) {
		            try {
		                wait();
		            } catch (InterruptedException e) {
		                e.printStackTrace();
		            }
		        }
			}
		};
		doPause(images.getPauseV());
		
		// create menu bar which let the player could control when to start
		// the game or exit, the start menu will start the game to allow the
		// monster waves starts and the exit menu will just exit the game
		MenuBar menuBar = new MenuBar();
		Menu menu;
		MenuItem start;
		MenuItem exit;
		if(language.equals("English")) {
			menu = new Menu("File"); 
			exit = new MenuItem("Exit");
			start = new MenuItem("Start");
		}
		else {
			menu = new Menu("文件"); 
			exit = new MenuItem("退出");
			start = new MenuItem("开始");
		}
		menu.getItems().addAll(start, exit);  // add menu bar and menu items
		menuBar.getMenus().add(menu);
		
		exit.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				stage.close();
			}
		});
		
		start.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				gameThread.start();			
			}
		});
		
		VBox vb = new VBox();
		vb.getChildren().addAll(menuBar, hbGrid);

		window.setTop(vb);
		window.setCenter(grid);
		window.setBottom(grid2);
		
		Scene scene = new Scene(window);
		stage.setScene(scene);
		stage.show();	
	}

	/**
	 * this is a helper method which create one monster on stage in thread
	 * and it will let the monster automatically start to move along the road
	 * 
	 * @param monster is a single monster class
	 * @param stage is the current game stage
	 */
	private void createMonster(Monster monster, Stage stage) {
		if(monster instanceof BasicMonster) {
			monster.setImg(new ImageView(new Image("/img/giphy.gif")));
		}else if (monster instanceof SecondMonster) {
			monster.setImg(new ImageView(new Image("/img/enemy2.gif")));	
		}else if(monster instanceof Monster3) {
			monster.setImg(new ImageView(new Image("/img/enemy3.gif")));
		}else if (monster instanceof Monster4) {
			monster.setImg(new ImageView(new Image("/img/enemy4.gif")));
		}else if (monster instanceof Monster5) {
			monster.setImg(new ImageView(new Image("/img/enemy5.gif")));
		}else if(monster instanceof Monster6) {
			monster.setImg(new ImageView(new Image("/img/enemy6.gif")));
		}

		ImageView monsterImg = monster.getImg();
		monsterImg.setFitHeight((int) RECTSIZE / 2);
		monsterImg.setFitWidth((int) RECTSIZE / 2);
		MonsterHandler move = new MonsterHandler(monsterImg,monster, stage);
		KeyFrame monsterKey = new KeyFrame(Duration.millis(1000/60*monster.getSpeed()),move);
		Timeline monsterTimeline = new Timeline(monsterKey);
		monsterTimeline.setAutoReverse(false);
		monsterTimeline.setCycleCount(Timeline.INDEFINITE);
		move.addTimeline(monsterTimeline);
		monstersTimeline.add(monsterTimeline);
		monsterTimeline.play();
	}

	/**
	 * here defines the private BulletHandler class which enable the tower's
	 * bullet function and animation. And it will automatically decided the
	 * attack direction for the tower depends on where the monster is
	 */
	private class BulletHandler implements EventHandler<ActionEvent>{
		ImageView img;
		Point point;
		int Bcount = 0 ;
		Timeline time;
		Tower tower;
		public BulletHandler(ImageView imgView,Point point,Tower tower) {
			img = imgView;
			this.point = point;
			img.setTranslateX(point.getY()*RECTSIZE+RECTSIZE/8);
			img.setTranslateY(point.getX()*RECTSIZE);
			this.tower = tower;
		}
		
		/**
		 * this method simply add the timeline into this class
		 * @param timeline is a Timeline object of a bulletTimeline
		 */
		public void addTimeline(Timeline timeline) {
			time = timeline;
		}
		
		/**
		 * this handle method handle the bullet attack direction and the animation
		 * of the bullet
		 */
		@Override
		public void handle(ActionEvent event) {
			if(one.isSelected()){
				time.setRate(1.0);
			} else if(two.isSelected()){
				time.setRate(2.0);
			}
			grid.getChildren().remove(img);
			if(heal == 0) {
				time.stop();
			}
			
			if(moveLeft() || moveRight() || moveUp()||moveDown()) {
				try {
					if(model.getMap().getGraph()[point.getX()][point.getY()-1].getMonster().size()!=0) {
						attackLeft();
					}else if(model.getMap().getGraph()[point.getX()][point.getY()+1].getMonster().size()!=0){
						attackRight();
					}else if(model.getMap().getGraph()[point.getX()+1][point.getY()].getMonster().size()!=0) {
						attackUp();
					}else if(model.getMap().getGraph()[point.getX()-1][point.getY()].getMonster().size()!=0) {
						attackDown();
					}else {
						img.setVisible(false);
					}
				}catch (Exception e) {
					img.setVisible(false);
				}
				
			}
			
			if(Bcount >=RECTSIZE) {
				Bcount = 0;
				img.setTranslateX(point.getY()*RECTSIZE+RECTSIZE/8);
				img.setTranslateY(point.getX()*RECTSIZE);
			}
			grid.getChildren().add(img);
			Bcount++;
		}
		
		/**
		 * this method will set bullet attack monster at tower's left and
		 * reduce the monster's health
		 */
		private void attackLeft() {
			img.setVisible(true);
			model.getMap().getGraph()[point.getX()][point.getY()-1].getMonster().get(0).healthLoss(0.03*tower.getAttack());
			img.setTranslateX(img.getTranslateX()-2.0);
		}
		
		/**
		 * this method will set bullet attack monster at tower's right and
		 * reduce the monster's health
		 */
		private void attackRight() {
			img.setVisible(true);
			model.getMap().getGraph()[point.getX()][point.getY()+1].getMonster().get(0).healthLoss(0.03*tower.getAttack());
			img.setTranslateX(img.getTranslateX()+2.0);
		}
		
		/**
		 * tthis method will set bullet attack monster at tower's up and
		 * reduce the monster's health
		 */
		private void attackUp() {
			img.setVisible(true);
			model.getMap().getGraph()[point.getX()+1][point.getY()].getMonster().get(0).healthLoss(0.03*tower.getAttack());
			img.setTranslateY(img.getTranslateY()+2.0);
		}
		
		/**
		 * this method will set bullet attack monster at tower's down and 
		 * reduce the monster's health
		 */
		private void attackDown() {
			img.setVisible(true);
			model.getMap().getGraph()[point.getX()-1][point.getY()].getMonster().get(0).healthLoss(0.03*tower.getAttack());
			img.setTranslateY(img.getTranslateY()-2.0);
		}
		
		/**
		 * this method will check if the point on tower's down direction is
		 * a road or not
		 */
		private boolean moveDown() {
			try {
				return model.getMap().getGraph()[point.getX()-1][point.getY()].isRoad();
			}catch (Exception e) {
				return false;
			}
		}
		
		/**
		 * this method will check if the point on tower's up direction is
		 * a road or not
		 */
		private boolean moveUp() {
			try {
				return model.getMap().getGraph()[point.getX()+1][point.getY()].isRoad();
			}catch (Exception e) {
				return false;
			}
		}
		
		/**
		 * this method will check if the point on tower's right direction is
		 * a road or not
		 */
		private boolean moveRight() {
			try {
				return model.getMap().getGraph()[point.getX()][point.getY()+1].isRoad();
			}catch (Exception e) {
				return false;
			}
		}
		
		/**
		 * this method will check if the point on tower's left direction is
		 * a road or not
		 */
		private boolean moveLeft() {
			try {
				return model.getMap().getGraph()[point.getX()][point.getY()-1].isRoad();
			}catch (Exception e) {
				return false;
			}
		}
	}

	/**
	 * here defines the private MonsterHandler class which handle how the
	 * monster moves on the game interface
	 */
	private class MonsterHandler implements EventHandler<ActionEvent> {
		int currentRoad = 0;
		double mcount = 0.0 ;
		Point nextPoint;
		ImageView img;
		Timeline time;
		Monster monster;
		Point currPoint;

		public MonsterHandler(ImageView monsterImg,Monster monster, Stage stage) {
			nextPoint  = road.get(currentRoad+1);
			this.img = monsterImg;
			img.setTranslateX(road.get(currentRoad).getY()*RECTSIZE+RECTSIZE/4);
			img.setTranslateY(road.get(currentRoad).getX()*RECTSIZE);
			this.monster = monster;
			road.get(currentRoad).setMonster(monster);
		}
		
		/**
		 * this handle method will define how the monster moves on the game
		 * stage and decided monster move direction and speed.
		 * it also check if monster dead or not, if dead it will remove monster
		 * image from the game stage
		 */
		@Override
		public void handle(ActionEvent event) {
			//System.out.println(1000);
			
			if(one.isSelected()){
				time.setRate(1.0);
			} else if(two.isSelected()){
				time.setRate(2.0);
			} 
			grid.getChildren().remove(img);
			
			if(currentRoad == road.size()-1) {
				time.stop();
				monstersTimeline.remove(time);
				img.setVisible(false);
				rectangles[nextPoint.getX()][nextPoint.getY()].setFill(new ImagePattern(images.getHomeend()));
				model.lossHealth(monster);

				if(count >= monsters.size()-1&& monstersTimeline.isEmpty()) {

					if(stageNum < 3) {
						stageNum++;
						askNext();	
					}
					else {
						System.out.println("Win1");
//						stage.close();
						youWin(stage);
						System.out.println("you win");
					}
	
				}
		

			}else if(monster.dead()){
				changeGold(monster.getGold());
				time.stop();
				monstersTimeline.remove(time);
				road.get(currentRoad).clearMonster(monster);
				img.setVisible(false);
	
				if(count >= monsters.size()-1&&monstersTimeline.isEmpty() ) {
					if(stageNum < 3) {
						stageNum++;
						askNext();	
					}
					else {
						System.out.println("Win2");
						youWin(stage);
//						stage.close();
						System.out.println("you win");
					}
	
				}

			}else {
				if(moveLeft()) {
					
					img.setTranslateX(img.getTranslateX()-1.0);
				}else if(moveRight()) {
					
					img.setTranslateX(img.getTranslateX()+1.0);
				}else if (moveUp()) {
					//time.setRate(2.0);
					img.setTranslateY(img.getTranslateY()+1.0);
				}else if(moveDown()) {
					img.setTranslateY(img.getTranslateY()-1.0);
					
				}
				
				if(mcount >=RECTSIZE) {
					mcount = 0;
					road.get(currentRoad).clearMonster(monster);
					currentRoad++;
					road.get(currentRoad).setMonster(monster);
					if(currentRoad < road.size()-1) {
						nextPoint  = road.get(currentRoad+1);
					}
				}
				
			}
			grid.getChildren().add(img);
			mcount++;
		}
		
		/**
		 * this method simply add the timeline into this class
		 * @param timeline is a Timeline object of a MonsterTimeline
		 */		
		public void addTimeline(Timeline time) {
			this.time = time;
		}
		
		/**
		 * this method will decide if monster will move to left based on road
		 * @return a boolean value tell us if it will move to left or not
		 */
		private boolean moveLeft() {
			Point point = road.get(currentRoad);
			if(point.getX() == nextPoint.getX() && point.getY() > nextPoint.getY()) {
				return true;
			}
			
			return point.equals(nextPoint);
		}
		
		/**
		 * this method will decide if monster will move to right based on road
		 * @return a boolean value tell us if it will move to left or not
		 */
		private boolean moveRight() {
			Point point = road.get(currentRoad);
			if(point.getX() == nextPoint.getX() && point.getY() < nextPoint.getY()) {
				return true;
			}
			
			return point.equals(nextPoint);
		}
		
		/**
		 * this method will decide if monster will move to up based on road
		 * @return a boolean value tell us if it will move to left or not
		 */
		private boolean moveUp() {
			Point point = road.get(currentRoad);
			if(point.getY() == nextPoint.getY() && point.getX() < nextPoint.getX()) {
				return true;
			}
			
			return point.equals(nextPoint);
		}
		
		/**
		 * this method will decide if monster will move to down based on road
		 * @return a boolean value tell us if it will move to left or not
		 */
		private boolean moveDown() {
			Point point = road.get(currentRoad);
			if(point.getY() == nextPoint.getY() && point.getX() > nextPoint.getX()) {
				return true;
			}
			
			return point.equals(nextPoint);
		}
	}	
	
	/**
	 * this method is used to draw the main grid pane and build the basic game
	 * sense fill with rectangle objects and set background image based on it is
	 * a start, end, road or other place.
	 * @param grid is the grid pane that represent the 
	 */
	public void setGridPane(GridPane grid) {
		Point point;
		Image image = new Image("/img/start.png");

		for (int i = 0; i < controller.HEIGHT; i++) {
			for (int j = 0; j < controller.WIDTH; j++) {
				Rectangle rectangle = new Rectangle();
				
				rectangle.setWidth(RECTSIZE);
				rectangle.setHeight(RECTSIZE);

				Point currentPoint = model.getMap().getGraph()[i][j];
				if(currentPoint.isRoad()) {
					
					rectangle.setFill(new ImagePattern(images.getRoad2()));
				
					point = model.getMap().getGraph()[i][j];
					if(point.isEnd()) {
						System.out.println("end");
						rectangle.setFill(new ImagePattern(images.getHome()));
					}
					if (point.isStart()) {
						rectangle.setFill(new ImagePattern(image));
					}
				}else {
					ImagePattern img = null;
					if (currentPoint.isdisabled()) {
						img = new ImagePattern(new Image("/img/obstacle.png"));
					}
					else {
						img = new ImagePattern(images.getgameOverback());
						
					}
					rectangle.setFill(img);
					doRectangle(rectangle, img, i, j);
				}
				this.rectangles[i][j] = rectangle;
				grid.add(rectangle, j, i);
			}
		}
	}
	
	/**
	 * this pause method enable the pause function with the "pause" image
	 * on the graph
	 * @param image is the "pause" image on the game interface
	 */
	private void doPause(ImageView image) {
		image.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(isPause) {
					isPause = false;
					synchronized (gameThread) {
			            try {
			                gameThread.notify();
			            } catch(Exception e) {	
			            }
			        }
				}else {
					isPause = true;
				}
				
				if(isPause) {
					for(Timeline monster:monstersTimeline) {
						monster.stop();
					}
					if(!BulletsTimeline.isEmpty()) {
						for(Timeline bullet:BulletsTimeline.values()) {
							bullet.stop();
						}
					}
				}else {
					for(Timeline monster:monstersTimeline) {
						monster.play();
					}
					if(!BulletsTimeline.isEmpty()) {
						for(Timeline bullet:BulletsTimeline.values()) {
							bullet.play();
						}
					}
				}	
			}
		});
	}
	
	/**
	 * this method will enable the mouse event for images on the selection bar
	 * which let the player could buy and sell towers
	 * @param image is the tower image or sell image on the selection bar
	 * @param currTower is the current tower that the mouse currently selected
	 */
	private void doImg(ImageView image, Tower currTower) {
		// handle the mouse_entered event
		image.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
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
		
		// handle the mouse_exited event
		image.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				image.setFitHeight(50);
				image.setFitWidth(50);
			}
		});
		
		// this method handle the mouse_clicked event which let the tower image
		// or sell image on the selection bar could be selected and memorized 
		// by the program.
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
	
	/**
	 * this method will allow the rectangle on the game interface be able to
	 * be clicked to set tower and sell existing towers
	 * @param ret is the current selected rectangle object
	 * @param img is a ImagePatter class represent 
	 * 			the current image on the rectangle
	 * @param x is the current selected rectangle's x position
	 * @param y is the current selected rectangle's y position
	 */
	private void doRectangle(Rectangle ret, ImagePattern img, int x, int y) {
		
		ret.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				ImagePattern roadPattern = new ImagePattern(images.getRoad2());
				if(img != roadPattern) {
					
					if(current == images.getSell()) {
						if (controller.getModel().getMap().getGraph()[x][y].getTower() != null) {
							controller.sellTower(x, y);
							current = null;
						}
						
					}
					else {
						//System.out.println("here, "+ controller.canSetTower(x, y));
						if (controller.canSetTower(x,y) && currentTower != null) {
							controller.buildTower(x, y, currentTower);
							current = null;
							currentTower = null;
						}
					}	
				}
			}
		});
	}
	
	/**
	 * this method will change the gold shown on the screen with the input
	 * value
	 * @param gold is the current value of gold that player has
	 */
	private void changeGold(int gold) {
		model.getMap().getPlayer().changeMoney(gold);
		totalGold = model.getMap().getPlayer().getMoney();
		System.out.println(totalGold);
		
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
	
	/**
	 * this method will decide whether the game is over and show a game over
	 * screen for the player
	 * @param stage
	 */
	private void gameOver(Stage stage) {
		Stage newStage = new Stage();
		stage.close();
		if(language.equals("English")) {
			newStage.setTitle("Protect Earth");
		}
		else {
			newStage.setTitle("保卫地球大作战");
		}
		BorderPane window = new BorderPane();
		GridPane grid = new GridPane();
		grid.setPrefSize(800, 450);
		grid.setBackground(new Background(new BackgroundImage(images.getgameOverback(), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT
				,BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
		images.getgameOverbackV().setFitHeight(450);
		images.getgameOverbackV().setFitWidth(800);
		
		grid.add(images.getgameOverbackV(), 0, 0);
		HBox hb = new HBox();
		hb.getChildren().add(images.getOver());
		hb.setAlignment(Pos.CENTER);
		grid.getChildren().add(hb);
		grid.setAlignment(Pos.CENTER);
		
		MenuBar menuBar = new MenuBar();
		Menu menu;
		MenuItem newgame;
		MenuItem exit;
		
		if(language.equals("English")) {
			menu = new Menu("File"); 
			newgame = new MenuItem("New Game");
			exit = new MenuItem("Exit");
		}
		else {
			menu = new Menu("文件"); 
			newgame = new MenuItem("新游戏");
			exit = new MenuItem("退出");
		}
		menu.getItems().addAll(newgame, exit);  // add menu bar and menu items
		menuBar.getMenus().add(menu);
		
		newgame.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				newStage.close();
				Stage stageB = new Stage();
				BorderPane window = new BorderPane();
				GameMenu menu = new GameMenu(stageB, window);
			}
		});
		
		exit.setOnAction(new EventHandler<ActionEvent>() {
			@SuppressWarnings("deprecation")
			public void handle(ActionEvent event) {
				
				newStage.close();
			}
		});
		
		window.setTop(menuBar);
		window.setCenter(grid);
		Scene scene = new Scene(window);
		newStage.setScene(scene);
		newStage.show();
	}
	
	/**
	 * this method define a new pop-up windows for the user when the user
	 * wins the game and it allows the user to select whether it want to
	 * continue or not 
	 */
	private void askNext() {
		Stage newStage = new Stage();
		BorderPane windowNext = new BorderPane();
		Label moveOn = new Label("DO YOU WANT TO CONTIUNE?");
		ToggleGroup groupN = new ToggleGroup();
		RadioButton yes = new RadioButton("YES");
		RadioButton no = new RadioButton("NO");
		yes.setToggleGroup(groupN);
		no.setToggleGroup(groupN);
		HBox hb = new HBox();
		hb.getChildren().addAll(moveOn, yes, no);
		
		GridPane grid = new GridPane();
		grid.add(hb, 0, 0);
		grid.setAlignment(Pos.CENTER);
		grid.setPrefSize(300, 150);
		
		windowNext.setCenter(grid);
		
		Scene scene = new Scene(windowNext);
		newStage.setScene(scene);
		newStage.show();
		
		yes.setOnMouseClicked((eventY) -> {
			
			newStage.close();
			stage.close();
			GameStage newGame = new GameStage(stageNum, language);
			newGame.createNewGame();
			
		});
		no.setOnMouseClicked((eventNo) -> {
			
			newStage.close();
			stage.close();
		});
	}
	
	/**
	 * this method defines the action for the event when the player wins
	 * the game
	 * @param stage is the game stage
	 */
	private void youWin(Stage stage) {
		stage.close();
		Stage newStage = new Stage();
		BorderPane windowWin = new BorderPane();
		GridPane grid = new GridPane();
		grid.setPrefSize(800, 450);
		grid.setBackground(new Background(new BackgroundImage(images.getgameOverback(), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT
				,BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
		images.getgameOverbackV().setFitHeight(450);
		images.getgameOverbackV().setFitWidth(800);
		
		grid.add(images.getgameOverbackV(), 0, 0);
		HBox hb = new HBox();
		hb.getChildren().add(images.getWinV());
		hb.setAlignment(Pos.CENTER);
		grid.getChildren().add(hb);
		grid.setAlignment(Pos.CENTER);
		
		windowWin.setCenter(grid);
		Scene scene = new Scene(windowWin);
		newStage.setScene(scene);
		newStage.show();
	}
}
