package model;

import java.util.ArrayList;
import java.util.Observable;

public class TowerDefModel extends Observable {
	private Map map;
	private ArrayList<Tower> availTowers;
	private ArrayList<Monster> monsters;
	
	public TowerDefModel() {
		//this.map = new Map();
		this.availTowers = new ArrayList<Tower>();
		this.monsters = new ArrayList<Monster>();
	}
	
	public Map getMap() {
		return this.map;
	}
	
	public ArrayList<Monster> getMonsters() {
		return this.monsters;
	}
	public void addMonsters(Monster monster) {
		 monsters.add(monster);
	}
	public ArrayList<Tower> getAvailTowers() {
		return this.availTowers;
	}
	
	public void addTowers(Tower tower) {
		this.availTowers.add(tower);
	}
	
	public void setMap(Map map) {
		this.map = map;
	}
	
	public void setTower(Tower tower, int row, int col) {
		this.map.getGraph()[row][col].setTower(tower);
		TowerDefMoveMessage msg = new TowerMessage(row, col, tower,-tower.getCost());
		
		setChanged();
		
        notifyObservers(msg);
        System.out.println("Check"+map.getPlayer().getMoney());
	}
	
	public void sellTower(int row, int col) {
		Tower tower = this.map.getGraph()[row][col].getTower();
		this.map.getGraph()[row][col].sellTower();
		TowerDefMoveMessage msg = new TowerMessage(row, col, tower,(int)Math.floor(tower.getCost()*0.8));
		
		setChanged();
        notifyObservers(msg);
	}
	
	
	public void lossHealth(Monster monster) {
		if(monster instanceof BasicMonster) {
			map.getPlayer().lossHealth(monster.lossPlayerHealth());
		}
		
		setChanged();
        notifyObservers(1);
	}
	
	public void monsterMove(int xPos, int yPos, int preX, int preY) {
		
	}
}
