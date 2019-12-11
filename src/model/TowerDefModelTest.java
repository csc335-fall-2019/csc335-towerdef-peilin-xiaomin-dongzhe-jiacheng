package model;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

public class TowerDefModelTest {
	
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
		assertTrue(point2.canSetTower());
		Tower tower1 = new BasicTower();
		Tower tower2 = new Turret();
		point2.setTower(tower1);
		point3.setTower(tower2);
		
		
		assertFalse(point2.canSetTower());
		Tower getTower = point2.getTower();
		assertEquals(tower1, getTower);
		point2.sellTower();
		assertTrue(point2.canSetTower());
		
		
		Monster monster = new BasicMonster();
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
		
	}
	
	@Test
	public void testMap() {
		Player player1 = new Player(20);
		Map map = new Map(player1, 10, 10);
		Player player2 = new Player(20);
	}

	
	@Test
	public void testImages() {
		
	}
	
	@Test
	public void testTowers() {
		
	}
	
	@Test
	public void testMonsters() {
		
	}
	
	@Test
	public void testTowerDefModel() {
		
	}
	
	
}
