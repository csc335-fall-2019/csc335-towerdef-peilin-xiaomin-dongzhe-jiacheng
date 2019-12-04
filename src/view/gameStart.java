package view;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import controller.TowerDefController;
import javafx.event.EventHandler;
import javafx.scene.Scene;
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
import javafx.stage.Stage;
import model.Tower;
import model.TowerDefModel;
import model.TowerDefMoveMessage;
import model.TowerMessage;

public class gameStart implements Observer {
	
	private GridPane grid;

	private ImageView current;
	private Tower currentTower;
	private ImageView sellImg;
	
	private Rectangle[][] rectangles;
	private TowerDefModel model;
	private TowerDefController controller;
	
	public gameStart() {
		this.rectangles = new Rectangle[10][20];
		this.model = new TowerDefModel();
		this.controller = new TowerDefController(model);
		controller.buildBasicStage();
		controller.getModel().addObserver(this);
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
			System.out.println(model.getMap().getPlayer().getMoney());
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

		Image first = new Image("/img/TOWER.png");
		ImageView firstImg = new ImageView(first);
		
		Image second = new Image("/img/tower2.png");
		ImageView secondImg = new ImageView(second);
		
		Image sell = new Image("/img/sell.png");
		sellImg = new ImageView(sell);
		

		
		
		HBox hb = new HBox();
		
		firstImg.setFitHeight(50);
		firstImg.setFitWidth(50);
		
		
		secondImg.setFitHeight(50);
		secondImg.setFitWidth(50);
		
		
		sellImg.setFitHeight(50);
		sellImg.setFitWidth(50);
		
		
		hb.getChildren().addAll(firstImg, secondImg, sellImg);
		
		grid2.getChildren().add(hb);
		
		doImg(firstImg, availTowers.get(0));
		doImg(secondImg, availTowers.get(1));
		doImg(sellImg, null);
		
		window.setTop(grid3);
		window.setCenter(grid);
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
	
	
	private void doImg(ImageView image, Tower currTower) {
		
		image.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub 
				if ((image == sellImg && currTower == null)
					|| controller.canBuyTower(currTower)) {
					image.setFitHeight(65);
					image.setFitWidth(65);
				}
				else {
					
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
				for (int i = 0; i < 10; i++) {
					for (int j = 0; j < 20; j++) {
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
}
