/**
 * This is a controller class.
 * @author xiaominzhao, JiachengHe,PeilinFeng, DongzheChen
 */
package controller;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import model.BasicMonster;
import model.BasicTower;
import model.Map;
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
import model.TowerDefModel;
import model.Turret;


public class TowerDefController {

	
	public final int WIDTH = 10;  // this is the size of the game board
	public final int HEIGHT = 6;
	public final int MIN_AVAIL_POINT = 5;
	
	private TowerDefModel model;
//	private Player player;
//	private Map map;
	//private Point start;
	public Point point;
	
	
	public TowerDefController(TowerDefModel model) {
		this.model = model;
	}
	
	/**
	 * A get method, return the model
	 * @return
	 */
	public TowerDefModel getModel() {
		return model;
	}
	
	 /**
	  * This method is used to build the map
	  */
	public void buildBasicStage(int stageNum) {
		Player newPlayer = new Player(20);
		Map newMap = new Map(newPlayer, HEIGHT, WIDTH);
		if (stageNum == 1) {
			this.buildFirstPath(newMap);
		}
		else if (stageNum == 2) {
			this.buildSecondPath(newMap);
		}
		else if (stageNum == 3) {
			this.buildThirdPath(newMap);
		}
		System.out.println(newMap.getRoads());
		model.setMap(newMap);
		this.disablePoint();

		model.addTowers(new BasicTower());
		model.addTowers(new Turret());

		model.addTowers(new Tower3());
		model.addTowers(new Tower4());
		model.addTowers(new Tower5());
		
	}

/**
 * This is a function to build the first stage
 * @param newMap, the map of the game board
 */
	public void buildFirstPath(Map newMap) {
		Point start = null;
		for (int row = 0; row < HEIGHT; row++) {
			for (int col = 0; col < WIDTH; col++) {
				point = new Point(row, col, false);
				if ((row == 1 && col <= 7) 
					|| (row == 4 && col <= 7) 
					|| (col == 7 && row >= 1 && row <= 4)) {
					point.setRoad();  // set these points for monster to walk throught
//					newMap.addRoad(point);
				}
				if (row == 1 && col == 0) {
					point.setStart();
					start = point;  // mark the beginning of the road
				}
				if (row == 4 && col == 0) {
					point.setEnd(); // mark the end of the road
				}
				newMap.update(row, col, point); //update this to be a pointer object
			}
		}
		this.buildRoad(newMap, start); 
		
		for(int i =0;i<10;i++) {   // mutiple waves of different monster
			model.addMonsters(new BasicMonster());
		}
		for(int i =0;i<3;i++) {
			model.addMonsters(new SecondMonster());
		}
		for(int i =0;i<2;i++) {
			model.addMonsters(new Monster3());
		}
		model.addMonsters(new Monster4());
		model.addMonsters(new Monster5());
		model.addMonsters(new Monster6());

	}

/**
 * Build the second stage.
 * @param newMap, the map of the game board
 */
	public void buildSecondPath(Map newMap) {
		Point start = null;
		for (int row = 0; row < HEIGHT; row++) {
			for (int col = 0; col < WIDTH; col++) {
				point = new Point(row, col, false);
				if ((row == 2 && col >= 2 && col <= 6) 
					|| (row == 4 && col >= 2)
					|| (col == 6 && row <= 2)
					|| (col == 2 && row >= 2 && row <=4)) {
					point.setRoad();
				}
				if (row == 0 && col == 6) {
					point.setStart();
					start = point;
				}
				if (row == 4 && col == 9) {
					point.setEnd();
				}
				newMap.update(row, col, point);
			}
		}
		this.buildRoad(newMap, start);
		for(int i =0;i<10;i++) {   // set the waves of different monster
			model.addMonsters(new BasicMonster());
		}
		for(int i =0;i<3;i++) {
			model.addMonsters(new SecondMonster());
		}
		for(int i =0;i<2;i++) {
			model.addMonsters(new Monster3());
		}
		model.addMonsters(new Monster4());
		model.addMonsters(new Monster5());
		model.addMonsters(new Monster6());
	}
	

/**
 * The map of the third stage
 * @param newMap, the map of the game board
 */
	public void buildThirdPath(Map newMap) {

	
		Point start = null;
		for (int row = 0; row < HEIGHT; row++) {
			for (int col = 0; col < WIDTH; col++) {  // set the road
				point = new Point(row, col, false);
				if (col == 0 || col == 2 
					|| ((col == 5 || col == 8) && row <= 3)
					|| (row == 5 && col == 1)
					|| (row == 0 && (col == 3 || col == 4 || col == 9))
					|| (row == 3 && (col == 6 || col == 7))) {
					point.setRoad();
				}
				if (row == 0 && col == 0) {
					point.setStart();
					start = point;
				}
				if (row == 0 && col == 9) {
					point.setEnd();
				}
				newMap.update(row, col, point); // update if the point is road or not 
			}
		}
		this.buildRoad(newMap, start);
		for(int i =0;i<10;i++) {    // set waves of different monster
			model.addMonsters(new BasicMonster());
		}
		for(int i =0;i<3;i++) {
			model.addMonsters(new SecondMonster());
		}
		for(int i =0;i<2;i++) {
			model.addMonsters(new Monster3());
		}
		model.addMonsters(new Monster4());
		model.addMonsters(new Monster5());
		model.addMonsters(new Monster6());
	}
	
/**
 * Find the road, 
 * @param newMap, the map of the game board
 * @param start, the beginning of the road
 */
	public void buildRoad(Map newMap, Point start) {
		int x = start.getX();
		int y = start.getY();
		
		boolean[][] visited = new boolean[HEIGHT][WIDTH];
		for (int row = 0; row < HEIGHT; row++) {
			for (int col = 0; col < WIDTH; col++) {
				if (newMap.getGraph()[row][col].isRoad()) {
					visited[row][col] = false;
				} else {
					visited[row][col] = true;
				}
			}
		}
		
		Queue<Point> availRoad = new LinkedList<>();
		availRoad.add(start);
		visited[x][y] = true;
		
		while (!availRoad.isEmpty()) {
			Point p = availRoad.poll();
			newMap.addRoad(p);
			
			if (p.isEnd()) {			
				return;
			}
			if (p.getX() - 1 >= 0 && visited[p.getX() - 1][p.getY()] == false) {
				availRoad.add(newMap.getGraph()[p.getX() - 1][p.getY()]);
				visited[p.getX() - 1][p.getY()] = true;
				continue;
			}
			if (p.getX() + 1 < HEIGHT && visited[p.getX() + 1][p.getY()] == false) {
				availRoad.add(newMap.getGraph()[p.getX() + 1][p.getY()]);
				visited[p.getX() + 1][p.getY()] = true;
				continue;
			}
			if (p.getY() - 1 >=0 && visited[p.getX()][p.getY() - 1] == false) {
				availRoad.add(newMap.getGraph()[p.getX()][p.getY() - 1]);
				visited[p.getX()][p.getY() - 1] = true;
				continue;
			}
			if (p.getY() + 1 < WIDTH && visited[p.getX()][p.getY() + 1] == false) {
				availRoad.add(newMap.getGraph()[p.getX()][p.getY() + 1]);
				visited[p.getX()][p.getY() + 1] = true;
				continue;
			}
		}
		
	}
/**
 * This function is to return the ability to buy tower
 * @param tower, a specified tower.
 * @return a boolean, if the player can buy the tower.
 */
	public boolean canBuyTower(Tower tower) {
		return model.getMap().getPlayer().canBuyTower(tower.getCost());
	}
/**
 * This function is to return the ability to put a tower in a location
 * @param x, the x-value of the point.
 * @param y, the y- value of the point.
 * @return , boolean the ability to put the tower in that location 
 */
	public boolean canSetTower(int x,int y) {
		return model.getMap().getGraph()[x][y].canSetTower();
	}
/**
 * 
 * @param x, the x-value of the point.
 * @param y, the y-value of the point.
 * @param tower, a specified tower.
 */
	public void buildTower(int x,int y,Tower tower) {		
		model.setTower(tower, x, y);  // put the tower in the map
	}
	

/**
 * This function is to sell the Tower.
 * @param x, x-value of the current location.
 * @param y, y-value of the current location
 */
	public void sellTower(int x, int y) {
		System.out.println("sell");
		model.sellTower(x, y); // to sell the tower
	}
	
	

	public void disablePoint() {
		ArrayList<Point> road = this.getModel().getMap().getRoads();
		int maxAvailPoints = WIDTH * HEIGHT - MIN_AVAIL_POINT - road.size();
		// random a number at most with the number of available non-road points minus 5; 
		int random = (int) (Math.random() * ((maxAvailPoints - MIN_AVAIL_POINT) + 1)) + MIN_AVAIL_POINT;
		int disabled = 0;
		while (disabled < random) {
			this.disableOnePoint();
			disabled++;
		}
	}
	
	/**
	 * helper method for disablePoint method, this function will disable
	 * one random point, which is not disabled and not a road.
	 * 
	 */
	private void disableOnePoint() {
		int randX = (int) (Math.random() * HEIGHT);
		int randY = (int) (Math.random() * WIDTH);
		Point randPoint = model.getMap().getGraph()[randX][randY];
		while (randPoint.isdisabled() && randPoint.isRoad()) {
			randX = (int) (Math.random() * HEIGHT);
			randY = (int) (Math.random() * WIDTH);
			randPoint = model.getMap().getGraph()[randX][randY];
		}
		randPoint.setDisable();
	}
	
}
