package test;

import static org.junit.Assert.*;

import org.junit.Test;

import controller.TowerDefController;
import javafx.scene.image.ImageView;
import model.Map;
import model.Monster;
import model.Player;
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
		//ImageView img = new ImageView();
		//ImageView bulletImg = new ImageView();
		tower.setAttack(10);
		tower.setRange(3);
		//tower.setBullet(bulletImg);
		tower.setCost(5);
		//tower.setImg(img);
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
		Point point = new Point(1, 5, false);
		//ImageView img = new ImageView();
		//ImageView bulletImg = new ImageView();
		tower.setAttack(10);
		tower.setRange(3);
		//tower.setBullet(bulletImg);
		tower.setCost(5);
		//tower.setImg(img);
		tower.setPoint(point);
		tower.setSpeed(8.0);
		tower.addAttackRange(point);
		
		assertTrue(controller.canBuyTower(tower));
		assertTrue(controller.canSetTower(1, 5));
		controller.buildTower(1, 5, tower);
		assertFalse(controller.canSetTower(1, 5));
		controller.sellTower(1, 5);
	}
	
	@Test
	public void testControllerThirdStage() {
		TowerDefModel model = new TowerDefModel();
		TowerDefController controller = new TowerDefController(model);
		assertEquals(model, controller.getModel());
		controller.buildBasicStage(3);
		
		Tower tower = new Tower();
		Point point = new Point(3, 1, false);
		//ImageView img = new ImageView();
		//ImageView bulletImg = new ImageView();
		tower.setAttack(10);
		tower.setRange(3);
		//tower.setBullet(bulletImg);
		tower.setCost(5);
		//tower.setImg(img);
		tower.setPoint(point);
		tower.setSpeed(8.0);
		tower.addAttackRange(point);
		
		assertTrue(controller.canBuyTower(tower));
		assertTrue(controller.canSetTower(3, 1));
		controller.buildTower(3, 1, tower);
		assertFalse(controller.canSetTower(3, 1));
		controller.sellTower(3, 1);
	}
	
	

}
