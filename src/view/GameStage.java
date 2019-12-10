package view;

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
import model.Images;
import model.Monster;

import model.Point;
import model.Tower;
import model.TowerDefModel;
import model.TowerDefMoveMessage;
import model.TowerMessage;
import model.Turret;

public class GameStage implements Observer {
	
	public final double RECTSIZE = 70.0f;
	private GridPane grid;
	private ImageView current;
	private Tower currentTower;

	private ImageView sellImg;
	private int monsterLeft = 1;

	private Thread gameThread;

	private Rectangle[][] rectangles;
	private TowerDefModel model;
	private TowerDefController controller;
	private int totalGold;
	private Label goldL;
	private HBox hb;
	private HBox hb2;
//	private Image sell;
//	private Image health;
//	private ImageView healthImg;
	private Label number;
	private Label healL;
	private int heal;
//	private Image gold;
//	private ImageView goldImg;
	private Label number2;
	private GridPane grid2;
	private GridPane grid3;
	//private int getRoad = 0;
	private ArrayList<Point> road;
	private ArrayList<Monster> monsters;
//	private Label currName;
//	private String towername;
	private HashMap<Point,Timeline> BulletsTimeline;
	private HashMap<Point,ImageView> BulletsImageView;
	private Images images;
	private Stage stage; 
	private VBox vb;
	private GameStage2 level2;
	
	private int deadMonsters;
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
		images = new Images();
		
	}
	
	
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		try {
			TowerDefMoveMessage msg = (TowerDefMoveMessage) arg;
			if (msg instanceof TowerMessage) {

				Tower tower = (Tower) msg.getObj();;
				Point point = tower.getPoint();
				// Point point = model.getMap().getGraph()[msg.getRow()][msg.getColumn()];
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
				} else if(msg.getMoney()< 0) {
					rectangles[msg.getRow()][msg.getColumn()].setFill(new ImagePattern(current.getImage()));
					Image bImg = new Image("/img/bullet1.JPG");
					ImageView bulletImg = new ImageView(bImg);
					bulletImg.setFitHeight((int) RECTSIZE / 4);
					bulletImg.setFitWidth((int) RECTSIZE / 4);

					BulletHandler move = new BulletHandler(bulletImg,point);
					KeyFrame BulletKey = new KeyFrame(Duration.millis(1000/70),move);

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
				
				
				
				grid3.getChildren().clear();
				hb = new HBox();
				hb2 = new HBox();
				hb.getChildren().addAll(images.getHealth(), number, healL);
				hb2.getChildren().addAll(images.getGold(), number2, goldL);
				grid3.add(hb, 0, 0);
				grid3.add(hb2, 1, 0);
				grid3.setHgap(30);
				
				
				//rectangles[msg.getRow()][msg.getColumn()].setFill(Color.RED);
				// update on stage;
			}
		}catch (Exception e) {
			heal = model.getMap().getPlayer().getHealth();
			healL.setText(String.valueOf(heal));	
			if(heal == 0) {
				gameOver(stage);
			}

		}
		
		
		
		
	}
	
	public void createNewGame(Stage stageA) {
//		Stage stage = new Stage();
		stage = stageA;
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
		
		
		
		gameThread = new Thread() {
			int count = 0;
			public void run() {
				deadMonsters = 0; 
				while(count<monsters.size()&& model.getMap().getPlayer().getHealth() > 0 ) {
					//System.out.println(1);
					createMonster(monsters.get(count), stageA);
					count++;
					try {
						Thread.sleep(2000);
						System.out.println(count);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		

//		gameThread.start();
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
		
		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("File"); 
		MenuItem newgame = new MenuItem("New Game");
		MenuItem exit = new MenuItem("Exit");
		MenuItem start = new MenuItem("Start");
		menu.getItems().addAll(start, newgame, exit);  // add menu bar and menu items
		menuBar.getMenus().add(menu);
		
		newgame.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
//				stage.close();
//				Stage stageB = new Stage();
//				BorderPane window = new BorderPane();
//				GameMenu menu = new GameMenu(stageB, window);
			}
		});
		
		
//		exit.setOnAction(new EventHandler<ActionEvent>() {
//			public void handle(ActionEvent event) {
//				stage.close();
//			}
//		});
		
		start.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				gameThread.start();
				
			}
		});
		
		VBox vb = new VBox();
		vb.getChildren().addAll(menuBar, grid3);

		window.setTop(vb);
		window.setCenter(grid);
		window.setBottom(grid2);
		
		Scene scene = new Scene(window);
		stage.setScene(scene);
		stage.show();
		
		
	}


	
	private void createMonster(Monster monster, Stage stage) {

		if(monster instanceof BasicMonster) {
			ImageView monsterImg = monster.getImg();
			monsterImg.setFitHeight((int) RECTSIZE / 2);
			monsterImg.setFitWidth((int) RECTSIZE / 2);
			MonsterHandler move = new MonsterHandler(monsterImg,monster, stage);
			KeyFrame monsterKey = new KeyFrame(Duration.millis(1000/60),move);
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
				if(model.getMap().getGraph()[point.getX()][point.getY()-1].getMonster().size()!=0) {
					img.setVisible(true);
					model.getMap().getGraph()[point.getX()][point.getY()-1].getMonster().get(0).healthLoss(0.2);
					img.setTranslateX(img.getTranslateX()-2.0);
				}else {
					img.setVisible(false);
				}
				
			}else if(moveRight()) {
				if(model.getMap().getGraph()[point.getX()][point.getY()+1].getMonster().size()!=0) {
					img.setVisible(true);
					model.getMap().getGraph()[point.getX()][point.getY()+1].getMonster().get(0).healthLoss(0.2);
					img.setTranslateX(img.getTranslateX()+2.0);
				}else {
					img.setVisible(false);
				}
				
			}else if (moveUp()) {
				if(model.getMap().getGraph()[point.getX()+1][point.getY()].getMonster().size()!=0) {
					img.setVisible(true);
					model.getMap().getGraph()[point.getX()+1][point.getY()].getMonster().get(0).healthLoss(0.2);
					img.setTranslateY(img.getTranslateY()+2.0);
				}else {
					img.setVisible(false);
				}
	
			}else if(moveDown()) {
				if(model.getMap().getGraph()[point.getX()-1][point.getY()].getMonster().size()!=0) {
					img.setVisible(true);
					model.getMap().getGraph()[point.getX()-1][point.getY()].getMonster().get(0).healthLoss(0.2);
					img.setTranslateY(img.getTranslateY()-2.0);
				}else {
					img.setVisible(false);
				}
				
			}
			
			if(count >=RECTSIZE) {
				count = 0;
				img.setTranslateX(point.getY()*RECTSIZE+RECTSIZE/8);
				img.setTranslateY(point.getX()*RECTSIZE);
			}
			grid.getChildren().add(img);
			count++;
		}
		
		public void attack(ImageView img) {
			Path path = new Path();
			
			path.getElements().add(new MoveTo(50, 50));
			path.getElements().add(new LineTo(300, 50));
			PathTransition pathTransition = new PathTransition();
			pathTransition.setDuration(Duration.millis(5000));
			pathTransition.setNode(img);
			pathTransition.setPath(path);
			pathTransition.play();
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
//		private Rectangle[][] rectangles;
		public MonsterHandler(ImageView monsterImg,Monster monster, Stage stage) {
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
//				images.getHomeV().setVisible(false);
				if(nextPoint.isEnd()) {
					deadMonsters++;
					System.out.println(nextPoint.getX()+" "+nextPoint.getY());
					System.out.println("getend");
					rectangles[nextPoint.getX()][nextPoint.getY()].setFill(new ImagePattern(images.getHomeend()));
//					rectangle.setFill(new ImagePattern(images.getHomeend()));
					if(deadMonsters == monsters.size() && model.getMap().getPlayer().getHealth() > 0) {
						System.out.println("you win");
						stage.close();
//						level2.createLevel2();
						
					}
				}
//				setHome(road.get(currentRoad), images.getHomeend());
				
				model.lossHealth(monster);
				//time.setRate(value);
				//System.out.println(1);
			}else if(heal == 0){
				time.stop();


			}else if(monster.dead()){
				//monsters.remove(monster);
				deadMonsters++; 
				monsterLeft++;
				road.get(currentRoad).clearMonster(monster);
				time.stop();
				img.setVisible(false);
				
				if(deadMonsters == monsters.size() && model.getMap().getPlayer().getHealth() > 0) {
					System.out.println("you win");
					stage.close();
//					level2.createLevel2();
					
				}

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
						setHome(rectangle, images.getHome());
					}
					if (point.isStart()) {
						rectangle.setFill(new ImagePattern(image));
					}
				}else {
					ImagePattern img = new ImagePattern(images.getgameOverback());
					rectangle.setFill(img);
					doRectangle(rectangle, img );
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
	
	
	private void doRectangle(Rectangle ret, ImagePattern img) {
		ret.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				ImagePattern roadPattern = new ImagePattern(images.getRoad2());
				if(img != roadPattern) {
					
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
	
	private void setHome(Rectangle ret, Image image) {
		ret.setFill(new ImagePattern(image));
	}
	
	
	private void gameOver(Stage stage) {
		Stage newStage = new Stage();
		stage.close();
		newStage.setTitle("Tower Defense");
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
		Menu menu = new Menu("File"); 
		MenuItem newgame = new MenuItem("New Game");
		MenuItem exit = new MenuItem("Exit");
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
}
