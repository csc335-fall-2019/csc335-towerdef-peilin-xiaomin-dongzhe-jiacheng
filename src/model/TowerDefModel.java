package model;

import java.util.ArrayList;
import java.util.Observable;

public class TowerDefModel extends Observable {
	private Map map;
	private int moves;
	private boolean pause;
	private ArrayList<Tower> availTowers;
	private ArrayList<Tower> activeTowers;
	private ArrayList<Monster> aliveMonsters;
	
	private int waves = 1;
	private ArrayList<Monster>[] monsterWaves;
	
	
	public TowerDefModel() {
		this.moves = 0;
		this.pause = false;
		this.availTowers = new ArrayList<Tower>();
		this.activeTowers = new ArrayList<Tower>();
		this.aliveMonsters = new ArrayList<Monster>();
		
		this.monsterWaves = new ArrayList[1];
		for (int i = 0; i < waves; i++) {
			monsterWaves[i] = new ArrayList<Monster>();
		}
	}
	
	public Map getMap() {
		return this.map;
	}
	
	public int getMoves() {
		return this.moves;
	}
	
	public boolean getPause() {
		return this.pause;
	}
	
	public ArrayList<Tower> getAvailTowers() {
		return this.availTowers;
	}
	public ArrayList<Tower> getActiveTowers() {
		return this.activeTowers;
	}
	
	public ArrayList<Monster> getAliveMonsters() {
		return this.aliveMonsters;
	}
	
	public int getWaves() {
		return this.waves;
	}
	
	public ArrayList<Monster>[] getMonsterWaves() {
		return this.monsterWaves;
	}
	
	
	
	public void setMap(Map map) {
		this.map = map;
	}
	
	public void setPause() {
		this.pause = true;
	}
	
	public void addAvailTower(Tower tower) {
		this.availTowers.add(tower);
	}
	
	public void addActiveTower(Tower tower) {
		this.activeTowers.add(tower);
	}
	
	public void addAliveMonster(Monster monster) {
		this.aliveMonsters.add(monster);
	}
	
	// not finished
	public void addMonster(Monster monster) {
		this.monsterWaves[0].add(monster);
	}
	
	public void tick() {
		int num = 0;
		// create monster (depends on number of moves)
		System.out.println(moves);
		while (num < 10) {
			if (moves % 60 == 0) {
				createMonster(num);
			}
			num++;
		}
		
		// move all alive monster
		for (Monster currMonster : aliveMonsters) {
			this.moveOneMonster(currMonster);
		}
		
		for (Tower currTower : activeTowers) {
			//	clear the curr availmonster list(delete dead monster)
			if (currTower.getAvailMonsters() != null && 
				!aliveMonsters.containsAll(currTower.getAvailMonsters())) {
				currTower.clearDeadMonsters();
			}
			int range = currTower.getRange();
			
			double towerX = currTower.getImg().getTranslateY();
			double towerY = currTower.getImg().getTranslateX();
			
			// int spee = 0;
			
			for (Monster currMonster : aliveMonsters) {
				double monsterX = currMonster.getImg().getTranslateY();
				double monsterY = currMonster.getImg().getTranslateX();
				
				double distX = Math.abs(monsterX - towerX);
				double distY = Math.abs(monsterY - towerY);
				
				double distance = Math.sqrt(Math.pow(distX, 2.0) + Math.pow(distY, 2.0));
				
				// monster move away the range
				if (currTower.getAvailMonsters() != null && currTower.getAvailMonsters().contains(currMonster) && distance > range) {
					currTower.removeMonster(currMonster);
				}
				// monster move into the range
				if (distance <= range) {
					currTower.addMonster(currMonster);
					// spee = currTower.getSpeed();
				}
			}
			
			ArrayList<Monster> towerAvailMonsters = currTower.getAvailMonsters();
			if (!towerAvailMonsters.isEmpty()) {
				// int speed = currTower.getSpeed();
				int attack = currTower.getAttack();
				Monster attackedMonster = towerAvailMonsters.get(0);
				attackedMonster.bloodLoss(attack);
				
				if (attackedMonster.isDead()) {
					this.monsterDead(attackedMonster);
					this.aliveMonsters.remove(attackedMonster);
					currTower.removeMonster(attackedMonster);
				}
			}	
		}
		System.out.println("here");

		//
		//for each tower go through tower list:
		
		// 	if monster move into tower's range
		// 		add monster to tower's availmonster list
		// 
		// 	find the first monster in availmonster list
		//		attack monster (minus health)
		//		if monster dead:
		// 			remove monster obj on map
		//			remove monster from availmonster list

		moves++;
		
		setChanged();
		notifyObservers();
	}
	
	public void setTower(Tower tower, int row, int col) {
		tower.setPoint(this.map.getGraph()[row][col]);
		this.activeTowers.add(tower);
		this.map.getGraph()[row][col].setTower(tower);
		TowerChangeMessage msg = new TowerChangeMessage(row, col, tower, -tower.getCost());
		// TowerDefMoveMessage msg = new TowerMessage(row, col, tower,-tower.getCost());
		
		setChanged();
		
        notifyObservers(msg);
        System.out.println("Check"+map.getPlayer().getMoney());
	}
	
	public void sellTower(int row, int col) {
		Tower tower = this.map.getGraph()[row][col].getTower();
		this.activeTowers.remove(tower);
		tower.setPoint(null);
		this.map.getGraph()[row][col].sellTower();
		int money = (int) Math.floor(tower.getCost()*0.8);
		TowerChangeMessage msg = new TowerChangeMessage(row, col, tower, money);
		
		setChanged();
        notifyObservers(msg);
	}
	
	public void createMonster(int num) {
		Point start = this.map.getStart();
		Point next = map.getRoad().get(map.getRoad().indexOf(start));
		Monster currMonster = monsterWaves[0].get(num % 10);
		int x = start.getX();
		int y = start.getY();
		currMonster.setPoint(map.getGraph()[x][y]);
		map.getGraph()[x][y].addMonster(currMonster);
		this.aliveMonsters.add(currMonster);
		MonsterMoveMessage msg = new MonsterMoveMessage(start, next, currMonster);
		
		setChanged();
        notifyObservers(msg);
	}
	
	public void moveOneMonster(Monster monster) {
		Point currPoint = monster.getPoint();
		Point nextRoad = map.getRoad().get(map.getRoad().indexOf(currPoint));
		MonsterMoveMessage msg = new MonsterMoveMessage(currPoint, nextRoad, monster);
		
		setChanged();
		notifyObservers(msg);
	}
	
	public void updateMonster(Monster monster) {
		double monsterX = monster.getImg().getTranslateY();
		double monsterY = monster.getImg().getTranslateX();
		
		
	}
	

	public void monsterDead(Monster monster) {
		//int row = monster.getPoint().getX();
		// int col = monster.getPoint().getY();
		int gold = monster.getGold();
		MonsterDeadMessage msg = new MonsterDeadMessage(monster, gold);
		
		setChanged();
		notifyObservers(msg);
	}
	
	public void loseHealth(Monster monster) {
		
		this.aliveMonsters.remove(monster);
		this.map.getPlayer().lossHealth(1);
		Player player = this.map.getPlayer();
		
		LoseHealthMessage msg = new LoseHealthMessage(monster, player);
		
		setChanged();
		notifyObservers(msg);
	}
	


	
//	public void lossHealth(Monster monster) {
//		if(monster instanceof BasicMonster) {
//			map.getPlayer().lossHealth(4);
//		}
//		
//		setChanged();
//        notifyObservers(1);
//	}
	
}
