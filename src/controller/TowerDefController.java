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
import model.Monster;
import model.Player;
import model.Point;
import model.Tower;
import model.TowerDefModel;
import model.Turret;

public class TowerDefController {

	
	public final int WIDTH = 10;
	public final int HEIGHT = 6;
	
	private TowerDefModel model;
	
	
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
	public void buildBasicStage() {
		Player newPlayer = new Player(20);
		Map newMap = new Map(newPlayer, HEIGHT, WIDTH);
		// Point start = null;
		for (int row = 0; row < HEIGHT; row++) {
			for (int col = 0; col < WIDTH; col++) {
				Point point = new Point(row, col, false);
				if ((row == 1 && col <= 7) 
					|| (row == 4 && col <= 7) 
					|| (col == 7 && row >= 1 && row <= 4)) {
					point.setRoad();
//					newMap.addRoad(point);
				}
				if (row == 1 && col == 0) {
					point.setStart();
					newMap.setStart(point);
				}
				if (row == 4 && col == 0) {
					point.setEnd();
					newMap.setEnd(point);
				}
				newMap.update(row, col, point);
			}
		}
		this.buildRoad(newMap);
		System.out.println(newMap.getRoad());
		model.setMap(newMap);
		model.addAvailTower(new BasicTower());
		model.addAvailTower(new Turret());
		for(int i =0;i<10;i++) {
			model.addMonster(new BasicMonster());
		}
	}
	
	public void buildStage() {
		Player newPlayer = new Player(20);
		Map newMap = new Map(newPlayer, HEIGHT, WIDTH);
		// Point start = null;
		for (int row = 0; row < HEIGHT; row++) {
			for (int col = 0; col < WIDTH; col++) {
				Point point = new Point(row, col, false);
				if ((row == 1 && col <= 7) 
					|| (row == 4 && col <= 7) 
					|| (col == 7 && row >= 1 && row <= 4)) {
					point.setRoad();
					
				}
				if (row == 1 && col == 0) {
					point.setStart();
					newMap.setStart(point);
					// start = point;
				}
				if (row == 4 && col == 0) {
					point.setEnd();
					newMap.setEnd(point);
				}
				newMap.update(row, col, point);
			}
		}
		this.buildRoad(newMap);
		System.out.println(newMap.getRoad());
		model.setMap(newMap);
		model.addAvailTower(new BasicTower());
		model.addAvailTower(new Turret());
		for(int i =0;i<10;i++) {
			model.addMonster(new BasicMonster());
		}
	}
	
	
	public void buildRoad(Map newMap) {
		Point start = newMap.getStart();
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
	
	public boolean canBuyTower(Tower tower) {
		return model.getMap().getPlayer().canBuyTower(tower.getCost());
	}
	
	public boolean canSetTower(int x,int y) {
		return model.getMap().getGraph()[x][y].canSetTower();
	}
	
	public void buildTower(int x,int y,Tower tower) {		
		model.setTower(tower, x, y);
	}
	
	public void sellTower(int x, int y) {
		System.out.println("sell");
		model.sellTower(x, y);
	}
	
	public void updateMonsterPoint(Point nextPoint) {
		
	}
	
	public void monsterWaves() {
		
	}
	
//	public void moveMonster() {
//		int currentWaves = waves - 1;
//		for (Monster currMonster : monsterWaves[currentWaves]) {
//			Point currPoint = currMonster.getPoint();
//			Point nextRoad = map.getRoad().get(map.getRoad().indexOf(currPoint));
//			
//		}
//	}
	
//	public void lossHealth(Monster monster) {
//		this.model.lossHealth(monster);
//	}
	
	public void changeMoney(int money) {
		this.model.getMap().getPlayer().changeMoney(money);
	}
	
}
