/**
 * This is a controller class.
 * @author xiaominzhao, JiachengHe,PeilinFeng, DongzheChen
 */
package controller;

import model.BasicTower;
import model.Map;
import model.Player;
import model.Point;
import model.Tower;
import model.TowerDefModel;
import model.Turret;

public class TowerDefController {

	
	public final int WIDTH = 10;
	public final int HEIGHT = 6;
	
	private TowerDefModel model;
//	private Player player;
//	private Map map;
	

	/**
	 * Controller constructor.
	 * @param model
	 */
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
				}
				if (row == 4 && col == 0) {
					point.setEnd();
				}
				newMap.update(row, col, point);
			}
		}
		model.setMap(newMap);
		model.addTowers(new BasicTower());
		model.addTowers(new Turret());
//		this.map = model.getMap();
//		this.player = this.map.getPlayer();
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
		model.sellTower(x, y);
	}
	
}
