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
	}
}
