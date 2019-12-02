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
	
	public void autoUpdateMoney() {
		this.map.getPlayer().autoMoney();
		
		setChanged();
        notifyObservers();
	}
	
	public void updateMoney(int earned) {
		this.map.getPlayer().earnMoney(earned);
		
		setChanged();
        notifyObservers();
	}
	
	public void buyTower(Tower tower, int xPos, int yPos) {
		this.map.getGraph()[xPos][yPos].setTower(tower);
		
		setChanged();
        notifyObservers();
	}
	
	public void sellTower(int xPos, int yPos) {
		this.map.getGraph()[xPos][yPos].sellTower();
		
		setChanged();
        notifyObservers();
	}
	
	public void monsterDie(int xPos, int yPos) {
		if (this.map.getGraph()[xPos][yPos].getMonster().dead()) {
			this.map.getGraph()[xPos][yPos].clearMonster();
		}
	}
}
