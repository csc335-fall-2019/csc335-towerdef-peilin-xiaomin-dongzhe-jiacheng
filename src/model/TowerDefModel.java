package model;

/**
 * @author Peilin Feng
 * @author Xiaomin Zhao
 * @author Dongzhe Chen
 * @author Jiacheng He
 * CSC 335, Fall 2019
 * team project, TowerDefModel.java
 * 
 * purpose: this is the towerDefModel which defines the model for this project
 * 			and set up the setTower, sellTower method and lossHealth method 
 * 			which send message to the GUI to change the corresponding
 * 			properties of the game
 */

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
	/**
	 * getter, return the game board as map
	 * @return, the map
	 */
	public Map getMap() {
		return this.map;
	}
	/**
	 * getter, return all the monsters in the game board
	 * @return all the monsters in the game board
	 */
	public ArrayList<Monster> getMonsters() {
		return this.monsters;
	}

	/**
	 * add a monster into monsters list
	 * @param monster object
	 */
	public void addMonsters(Monster monster) {
		 monsters.add(monster);
	}
	/**
	 * return the list of towers that are in use
	 * @return list of towers that are in use
	 */
	public ArrayList<Tower> getAvailTowers() {
		return this.availTowers;
	}
	/**
	 * add a tower to the game board
	 * @param tower, the tower obj
	 */
	public void addTowers(Tower tower) {
		this.availTowers.add(tower);
	}
	/**
	 * set the game board map
	 * @param map, the map obj
	 */
	public void setMap(Map map) {
		this.map = map;
	}
	/**
	 * This function is to buy tower
	 * @param tower, a tower.
	 * @param row, the position in the game board
	 * @param col, the position in the game board
	 */
	public void setTower(Tower tower, int row, int col) {
		tower.setPoint(this.map.getGraph()[row][col]);
		// tower.setAttackRange(this.map);
		this.map.getGraph()[row][col].setTower(tower);
		TowerDefMoveMessage msg = new TowerDefMoveMessage(row, col, tower,-tower.getCost());
		
		setChanged();
		
        notifyObservers(msg);
        System.out.println("Check"+map.getPlayer().getMoney());
	}
	/**
	 * This function is to sell towers
	 * @param row, the position in the game board
	 * @param col, the position in the game board
	 */
	public void sellTower(int row, int col) {
		Tower tower = this.map.getGraph()[row][col].getTower();
		tower.setPoint(null);
		this.map.getGraph()[row][col].sellTower();
		System.out.println("abc");
		TowerDefMoveMessage msg = new TowerDefMoveMessage(row, col, tower,(int)Math.floor(tower.getCost()*0.8));
		
		setChanged();
        notifyObservers(msg);
	}

	/**
	 * This function is used to minus the life, when monster attack
	 * @param monster, a type of monster
	 */
	public void lossHealth(Monster monster) {
		map.getPlayer().lossHealth(monster.lossPlayerHealth());
		setChanged();
        notifyObservers(1);
	}
	
}
