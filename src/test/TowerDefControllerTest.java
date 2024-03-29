package test;

/**
 * @author Peilin Feng
 * @author Xiaomin Zhao
 * @author Dongzhe Chen
 * @author Jiacheng He
 * CSC 335, Fall 2019
 * team project, TowerDefControllerTest.java
 * 
 * purpose: this is our controller test method which test the 
 * 			controller class for this project
 */

import static org.junit.Assert.*;

import org.junit.Test;

import controller.TowerDefController;

import model.Point;
import model.Tower;
import model.TowerDefModel;

public class TowerDefControllerTest {
	
	@Test
	public void testControllerFirstStage() {
		TowerDefModel model = new TowerDefModel();
		TowerDefController controller = new TowerDefController(model);
		assertEquals(model, controller.getModel());
		controller.buildBasicStage(1);
		
		Tower tower = new Tower();
		Point point = new Point(0, 2, false);
		tower.setAttack(10);
		tower.setRange(3);
		tower.setCost(5);
		tower.setPoint(point);
		tower.setSpeed(8.0);
		tower.addAttackRange(point);
		
		assertTrue(controller.canBuyTower(tower));
		assertTrue(controller.canSetTower(0, 2));
		controller.buildTower(0, 2, tower);
		assertFalse(controller.canSetTower(0, 2));
		controller.sellTower(0, 2);
	}
	
	@Test
	public void testControllerSecondStage() {
		TowerDefModel model = new TowerDefModel();
		TowerDefController controller = new TowerDefController(model);
		assertEquals(model, controller.getModel());
		controller.buildBasicStage(2);
		
		Tower tower = new Tower();
		tower.setAttack(10);
		tower.setRange(3);
		tower.setCost(5);
		tower.setSpeed(8.0);
		
		assertTrue(controller.canBuyTower(tower));
		for (int i = 0; i < controller.HEIGHT; i++) {
			for (int j = 0; j < controller.WIDTH; j++) {
				Point point = new Point(i, j, false);
				if (controller.canSetTower(i, j)) {
					controller.buildTower(i, j, tower);
					tower.setPoint(point);
					assertFalse(controller.canSetTower(i, j));
					controller.sellTower(i, j);
				}
			}
		}

	}
	
	@Test
	public void testControllerThirdStage() {
		TowerDefModel model = new TowerDefModel();
		TowerDefController controller = new TowerDefController(model);
		assertEquals(model, controller.getModel());
		controller.buildBasicStage(3);
		
		Tower tower = new Tower();
		tower.setAttack(10);
		tower.setRange(3);
		tower.setCost(5);
		tower.setSpeed(8.0);
		
		assertTrue(controller.canBuyTower(tower));
		for (int i = 0; i < controller.HEIGHT; i++) {
			for (int j = 0; j < controller.WIDTH; j++) {
				Point point = new Point(i, j, false);
				if (controller.canSetTower(i, j)) {
					controller.buildTower(i, j, tower);
					tower.setPoint(point);
					assertFalse(controller.canSetTower(i, j));
					controller.sellTower(i, j);
				}
			}
		}
	}
	
}
