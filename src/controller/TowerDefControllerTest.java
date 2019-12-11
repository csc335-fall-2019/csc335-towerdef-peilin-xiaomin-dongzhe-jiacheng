package controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import model.Map;
import model.Monster;
import model.Player;
import model.Point;
import model.Tower;
import model.TowerDefModel;

public class TowerDefControllerTest {
	
	@Test
	public void testController() {
		TowerDefModel model = new TowerDefModel();
		TowerDefController controller = new TowerDefController(model);
		controller.buildBasicStage(1);
		
		Tower tower = 
		
		
		
		Player player = new Player(20);
		Map map = new Map(player, 10, 10);
		Point point = new Point(1, 1, false);
		map.update(1, 1, point);
		Tower tower = new Tower();
		
		model.setMap(map);
		model.addTowers(tower);
		model.setTower(tower, 1, 1);
		
		TowerDefController controller = new TowerDefController(model);
		assertEquals(model, controller.getModel());
		
		controller
		
	}
	

}
