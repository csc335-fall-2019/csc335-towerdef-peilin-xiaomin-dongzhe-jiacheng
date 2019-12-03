package model;

import java.util.ArrayList;
import java.util.Observable;

public class TowerDefModel extends Observable {
	private Map map;
	private ArrayList<Tower> availTowers;
	
	public TowerDefModel() {
		
	}
	
	public Map getMap() {
		return this.map;
	}
	
	public ArrayList<Tower> getAvailTowers() {
		return this.availTowers;
	}
	
	public void setMap(Map map) {
		this.map = map;
	}
	
	
	public void setTower(Tower tower, int row, int col) {
		this.map.getGraph()[row][col].setTower(tower);
		TowerDefMoveMessage msg = new TowerMessage(row, col, tower,-tower.getCost());
		
		setChanged();
        notifyObservers(msg);
	}
	
	public void sellTower(int row, int col) {
		Tower tower = this.map.getGraph()[row][col].getTower();
		this.map.getGraph()[row][col].sellTower();
		TowerDefMoveMessage msg = new TowerMessage(row, col, tower,tower.getCost());
		
		setChanged();
        notifyObservers(msg);
	}
	
	public void monsterDie(int xPos, int yPos) {
		this.map.getGraph()[xPos][yPos].clearMonster();
		
		setChanged();
        notifyObservers();
	}
	
	public void monsterMove(int xPos, int yPos, int preX, int preY) {
		
	}
}
