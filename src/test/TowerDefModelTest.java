package test;

/**
 * @author Peilin Feng
 * @author Xiaomin Zhao
 * @author Dongzhe Chen
 * @author Jiacheng He
 * CSC 335, Fall 2019
 * team project, TowerDefModelTest.java
 * 
 * purpose: this is our model test method which test the all of our
 * 			models for this project
 */
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import javafx.scene.image.ImageView;
import model.BasicMonster;
import model.BasicTower;
import model.Map;
import model.Monster;
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
import model.Tower6;
import model.TowerDefModel;
import model.TowerDefMoveMessage;
import model.Turret;

public class TowerDefModelTest {
	
	private static final double DELTA = 1e-15;
	
	@Test
	public void testTowers() {
		Tower tower = new Tower();
		Point point = new Point(1, 2, false);
		ImageView img = new ImageView();
		ImageView bulletImg = new ImageView();
		tower.setAttack(10);
		tower.setRange(3);
		tower.setBullet(bulletImg);
		tower.setCost(5);
		tower.setImg(img);
		tower.setPoint(point);
		tower.setSpeed(8.0);
		tower.addAttackRange(point);
		
		assertEquals(10, tower.getAttack());
		assertEquals(bulletImg, tower.getBullet());
		assertEquals(5, tower.getCost());
		assertEquals(img, tower.getImg());
		assertEquals(point, tower.getPoint());
		assertTrue(tower.getAttackRange().contains(point));
		assertEquals(tower.getSpeed(), 8.0, DELTA);

	}
	
	@Test
	public void testMonsters() {
		Monster monster1 = new Monster();
		Monster monster2 = new Monster(10, 3.0, 20.0, 2);
		
		Point point = new Point(1, 2, false);
		ImageView img = new ImageView();
		monster1.setGold(15);
		monster1.setHealth(50.0);
		monster1.setImg(img);
		monster1.setLossPlayerHealth(5);
		monster1.setPoint(point);
		monster1.setSpeed(5.0);
		
		assertEquals(15, monster1.getGold());
		assertEquals(monster1.getHealth(), 50.0, DELTA);
		assertEquals(monster1.getImg(), img);
		assertEquals(monster1.getPoint(), point);
		assertEquals(monster1.getSpeed(), 5.0, DELTA);
		assertEquals(monster1.lossPlayerHealth(), 5);
		assertEquals(monster2.getGold(), 10);
		assertEquals(monster2.getHealth(), 20.0, DELTA);
		assertEquals(monster2.getSpeed(), 3.0, DELTA);
		assertEquals(monster2.lossPlayerHealth(), 2);
		
		monster1.healthLoss(2.0);
		assertFalse(monster1.dead());
		monster1.healthLoss(48.0);
		assertTrue(monster1.dead());
		
	}
	
	@Test
	public void testPoint() {
		Point point1 = new Point(1, 1, false);
		Point point2 = new Point(2, 4, false);
		Point point3 = new Point(3, 3, false);
		
		assertEquals(point1.getX(), 1);
		assertEquals(point1.getY(), 1);
		assertEquals(point2.getX(), 2);
		assertEquals(point2.getY(), 4);
		
		assertFalse(point1.isRoad());
		point1.setRoad();
		assertTrue(point1.isRoad());
		
		assertFalse(point1.canSetTower());
		assertTrue(point2.canSetTower());
		assertTrue(point3.canSetTower());
		point3.setDisable();
		assertFalse(point3.canSetTower());
		assertTrue(point3.isdisabled());
		Tower tower = new Tower();
		point2.setTower(tower);
		assertFalse(point2.canSetTower());
		assertEquals(tower, point2.getTower());
		point2.sellTower();
		assertTrue(point2.canSetTower());
		
		Monster monster = new Monster();
		point1.setMonster(monster);
		assertTrue(point1.getMonster().contains(monster));
		point1.clearMonster(monster);
		assertFalse(point1.getMonster().contains(monster));
		
		point1.setStart();
		point1.setEnd();
		assertTrue(point1.isStart());
		assertTrue(point1.isEnd());
		assertFalse(point2.isStart());
		assertFalse(point2.isEnd());
		
	}
	 
	@Test
	public void testPlayer() {
		Player player = new Player(50);
		player.lossHealth(3);
		player.changeMoney(20);
		player.changeMoney(-5);
		assertTrue(player.canBuyTower(10));
		
		assertEquals(50-3, player.getHealth());
		assertEquals(75+20-5, player.getMoney());
	}
	
	@Test
	public void testMap() {
		Player player = new Player(20);
		Map map = new Map(player, 10, 10);
		
		assertEquals(player, map.getPlayer());
		assertArrayEquals(new Point[10][10], map.getGraph());
		
		Point point = new Point(1, 1, false);
		map.update(1, 1, point);
		assertEquals(new ArrayList<Point>(), map.getRoads());
		map.addRoad(point);
		assertEquals(1, map.getRoads().size());
		map.setPointDisable(point);
	}
	
	@Test
	public void testTowerDefModel() {
		TowerDefModel model = new TowerDefModel();
		
		Player player = new Player(20);
		Map map = new Map(player, 10, 10);
		Point point = new Point(1, 1, false);
		map.update(1, 1, point);
		Monster monster = new Monster();
		Tower tower = new Tower();
		
		model.setMap(map);
		model.addTowers(tower);
		model.setTower(tower, 1, 1);
		model.sellTower(1, 1);
		model.addMonsters(monster);
		model.lossHealth(monster);
		
		assertEquals(map, model.getMap());
		assertEquals(monster, model.getMonsters().get(0));
		assertEquals(tower, model.getAvailTowers().get(0));
		
	}
	
	@Test
	public void testTowerDefMoveMessage() {
		Tower tower = new Tower();
		TowerDefMoveMessage msg = new TowerDefMoveMessage(1,2,tower,10);
		assertEquals(1, msg.getRow());
		assertEquals(2, msg.getColumn());
		assertEquals(tower, msg.getTower());
		assertEquals(10, msg.getMoney());
	}
	
	@Test
	public void testBasicTower() {
		Tower tower = new BasicTower();
	}
	
	@Test
	public void testTurret() {
		Tower tower = new Turret();
	}	
	
	@Test
	public void testTower3() {
		Tower tower = new Tower3();
	}	
	
	@Test
	public void testTower4() {
		Tower tower = new Tower4();
	}	
	
	@Test
	public void testTower5() {
		Tower tower = new Tower5();
	}
	
	@Test
	public void testTower6() {
		Tower tower = new Tower6();
	}
	
	@Test
	public void testBasicMonster() {
		Monster monster = new BasicMonster();
	}	
	
	@Test
	public void testSecondMonster() {
		Monster monster = new SecondMonster();
	}
	
	@Test
	public void testMonster3() {
		Monster monster = new Monster3();
	}
	
	@Test
	public void testMonster4() {
		Monster monster = new Monster4();
	}
	
	@Test
	public void testMonster5() {
		Monster monster = new Monster5();
	}
	
	@Test
	public void testMonster6() {
		Monster monster = new Monster6();
	}
	
}
