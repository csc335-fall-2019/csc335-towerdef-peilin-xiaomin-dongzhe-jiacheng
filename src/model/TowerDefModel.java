/**
 * This is the model class.
 */
package model;

import java.util.ArrayList;
import java.util.Observable;

public class TowerDefModel extends Observable {
	private Map map;
	private ArrayList<Tower> availTowers;
	private ArrayList<Monster> monsters;
	
	public TowerDefModel() { //constructor
		//this.map = new Map();
		this.availTowers = new ArrayList<Tower>();
		this.monsters = new ArrayList<Monster>();
	}
	
	public Map getMap() { //getter, return the game board as map
		return this.map;
	}
	
	public ArrayList<Monster> getMonsters() { //getter, return all the monsters in the game board
		return this.monsters;
	}
	public void addMonsters(Monster monster) { // add the monster in the game board
		 monsters.add(monster);
	}
	public ArrayList<Tower> getAvailTowers() { // return the list of towers that are in use
		return this.availTowers;
	}
	
	public void addTowers(Tower tower) { // add a tower to the game board
		this.availTowers.add(tower);
	}
	
	public void setMap(Map map) { // set the game board map
		this.map = map;
	}
/**
 * This function is to buy tower
 * @param tower, a tower.
 * @param row, the position in the game board
 * @param col, the position in the game board
 */
	public void setTower(Tower tower, int row, int col) {
		tower.setPoint(this.map.getGraph()[row][col]); // put the tower in a location point
		// tower.setAttackRange(this.map);
		this.map.getGraph()[row][col].setTower(tower); // put the tower in the map
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
		Tower tower = this.map.getGraph()[row][col].getTower(); //get the kind of tower
		tower.setPoint(null); // clear the tower at the point
		this.map.getGraph()[row][col].sellTower(); // sell the tower
		System.out.println("abc");

		TowerDefMoveMessage msg = new TowerMessage(row, col, tower,(int)Math.floor(tower.getCost()*0.8));
									//add the 80% of original price of the tower
		
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
