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

	
	public final int WIDTH = 10;
	public final int HEIGHT = 6;
	public final int MIN_AVAIL_POINT = 8;
	
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
		model.addTowers(new BasicTower());
		model.addTowers(new Turret());
		model.addTowers(new Tower3());
		model.addTowers(new Tower4());
		model.addTowers(new Tower5());
		
	}
	
	private void buildFirstPath(Map newMap) {
		Point start = null;
		Point end = null;
		for (int row = 0; row < HEIGHT; row++) {
			for (int col = 0; col < WIDTH; col++) {
				point = new Point(row, col, false);
				if ((row == 1 && col <= 7) 
					|| (row == 4 && col <= 7) 
					|| (col == 7 && row >= 1 && row <= 4)) {
					point.setRoad();
//					newMap.addRoad(point);
				}
				if (row == 1 && col == 0) {
					point.setStart();
					start = point;
				}
				if (row == 4 && col == 0) {
					point.setEnd();
					end = point;
				}
				newMap.update(row, col, point);
			}
		}
		this.buildRoad(newMap, start);
		this.disableEndSurround(newMap, end);
		for (int row = 0; row < HEIGHT; row++) {
			for (int col = 0; col < WIDTH; col++) {
				System.out.print(newMap.getGraph()[row][col]);
			}
			System.out.println();
		}
			
		for(int i =0;i<10;i++) {
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
	
	private void buildSecondPath(Map newMap) {
		Point start = null;
		Point end = null;
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
					end = point;
				}
				newMap.update(row, col, point);
			}
		}
		this.buildRoad(newMap, start);
		this.disableEndSurround(newMap, end);
		this.disablePoint(newMap, newMap.getRoads().size());
		for (int row = 0; row < HEIGHT; row++) {
			for (int col = 0; col < WIDTH; col++) {
				System.out.print(newMap.getGraph()[row][col]);
			}
			System.out.println();
		}
		
		for(int i =0;i<10;i++) {
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
	
	
	private void buildThirdPath(Map newMap) {
		Point start = null;
		Point end = null;
		for (int row = 0; row < HEIGHT; row++) {
			for (int col = 0; col < WIDTH; col++) {
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
					end = point;
				}
				newMap.update(row, col, point);
			}
		}
		this.buildRoad(newMap, start);
		this.disableEndSurround(newMap, end);
		this.disablePoint(newMap, newMap.getRoads().size());
		for (int row = 0; row < HEIGHT; row++) {
			for (int col = 0; col < WIDTH; col++) {
				System.out.print(newMap.getGraph()[row][col]);
			}
			System.out.println();
		}
		
		for(int i =0;i<10;i++) {
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
	
	

	/**
	 * this method will disable random number of points and at each time it
	 * will call the help method disableOnePoint to disable one random point
	 * on the map
	 */
	public void disablePoint(Map newMap, int roadSize) {
		int maxAvailPoints = WIDTH * HEIGHT - MIN_AVAIL_POINT - roadSize;
		// random a number at most with the number of available non-road points minus 5; 
		int random = (int) (Math.random() * ((maxAvailPoints - MIN_AVAIL_POINT) + 1)) + MIN_AVAIL_POINT;
		int disabled = 0;
		while (disabled < random) {
			this.disableOnePoint(newMap);
			disabled++;
		}
	}
	
	
	
	/**
	 * helper method for disablePoint method, this function will disable
	 * one random point, which is not disabled and not a road.
	 * 
	 */
	private void disableOnePoint(Map newMap) {
		int randX = (int) (Math.random() * HEIGHT);
		int randY = (int) (Math.random() * WIDTH);
		Point randPoint = newMap.getGraph()[randX][randY];
		while (randPoint.isdisabled() || randPoint.isRoad()) {
			randX = (int) (Math.random() * HEIGHT);
			randY = (int) (Math.random() * WIDTH);
			randPoint = newMap.getGraph()[randX][randY];
		}
		randPoint.setDisable();
	}
	
	/**
	 * this will disable the surround points of the end point after
	 * the road of the map has been build
	 * @param end is the end point object
	 */
	public void disableEndSurround(Map newMap, Point end) {
		int endX = end.getX();
		int endY = end.getY();
		System.out.println(endX + "," + endY);
		if (endX - 1 >= 0) {
			Point endLeft = newMap.getGraph()[endX-1][endY];
			if ((!endLeft.isRoad()) && (!endLeft.isdisabled())) {
				endLeft.setDisable();
			}
		}
		if (endX + 1 < HEIGHT) {
			Point endRight = newMap.getGraph()[endX+1][endY];
			if ((!endRight.isRoad()) && (!endRight.isdisabled())) {
				endRight.setDisable();
			}
		}
		
		if (endY - 1 >= 0) {
			Point endUp = newMap.getGraph()[endX][endY-1];
			if ((!endUp.isRoad()) && (!endUp.isdisabled())) {
				endUp.setDisable();
			}
		}
		if (endY + 1 < WIDTH) {
			Point endDown = newMap.getGraph()[endX][endY+1];
			if ((!endDown.isRoad()) && (!endDown.isdisabled())) {
				endDown.setDisable();
			}
		}
	}
}
