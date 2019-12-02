package controller;

import model.Map;
import model.Player;
import model.Point;
import model.TowerDefModel;

public class TowerDefController {
	private TowerDefModel model;
	
	public TowerDefController(TowerDefModel model) {
		this.model = model;
	}
	
	public TowerDefModel getModel() {
		return model;
	}
	
	public void buildBasicStage() {
		Player player = new Player(20);
		int width = 20;
		int height = 10;
		Map map = new Map(player, height, width);
		
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				Point point = new Point(row, col, false);
				if ((row == 2 && col <= 15) 
					|| (row == 8 && col <=15) 
					|| (col == 15 && row >= 2 && row <= 8)) {
					point.setRoad();
				}
				if (row == 2 && col == 0) {
					point.setStart();
				}
				if (row == 8 && col == 0) {
					point.setEnd();
				}
				map.update(row, col, point);
			}
		}
		model.setMap(map);
		
	}

}
