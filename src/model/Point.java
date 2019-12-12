/**
 * @author chendongzhe, xiaominzhao, peilinfeng, jiacheng he
 * This is a class to represent a location in the game board.
 */
package model;

import java.util.ArrayList;

public class Point {
	private int x; //x-value of the game board
	private int y; //y-value of the game board
	private boolean road; // indicate if the location is the road
	private Tower tower;
	private ArrayList<Monster> monsters = new ArrayList<Monster>(); // list to store monsters
	private boolean start = false;
	private boolean end = false;
	private boolean disable = false;
	
	public Point(int x,int y,boolean isRoad) {
		this.x = x;
		this.y = y;
		this.road = isRoad;
	}
/**
 * get the location 
 * @return the x-value of the location 
 */
	public int getX() {
		return x;
	}
	/**
	 * get the location 
	 * @return the y-value of the location 
	 */
	public int getY() {
		return y;
	}	
	/**
	 * return if the location is road
	 * @return if the position is road
	 */
	public boolean isRoad() {
		return road;
	}
	/**
	 * get method, return the tower at the location
	 * @return the tower at the location
	 */
	public Tower getTower() {
		return tower;
	}
	/**
	 * get method, return the list of all monster in location
	 * @return the list of all monster in location
	 */
	public ArrayList<Monster> getMonster() {
		return monsters;
	}
	/**
	 * return if the location is the beginning of road
	 * @return if is the beginning of the road
	 */
	public boolean isStart() {
		return start;
	}
	/**
	 * check if the location is the end of road
	 * @return if is the end of the road
	 */
	public boolean isEnd() {
		return end;
	}
	/**
	 * check if the position can click
	 * @return the ability
	 */
	public boolean isdisabled() {
		return disable;
	}
	/**
	 * set this location to road
	 */
	public void setRoad() {
		this.road = true;
	}
	/**
	 * indicate of this location can put a tower
	 * @return the ability
	 */
	public boolean canSetTower() {
		return !disable && !road && tower == null;
	}
	/**
	 * get method, return the tower at the location
	 * @param tower, the tower
	 */
	public void setTower(Tower tower) {
		this.tower = tower;
	}
	/**
	 * sell the tower at the location
	 */
	public void sellTower() {
		this.tower = null;
	}
	/**
	 * set the monster at this location 
	 * @param monster, a monster obj
	 */
	public void setMonster(Monster monster) {
		monsters.add(monster);
	}
	/**
	 * to delete a monster from the game board, when it die
	 * @param monster, the monster obj
	 */
	public void clearMonster(Monster monster) {
		for(Monster monsterIn:monsters) {
			if(monster.getHealth() == monsterIn.getHealth()) {
				monsters.remove(monsterIn);
				break;
			}
		}
	}
	/**
	 * set the location be the beginning of road
	 */
	public void setStart() {
		start = true;
	}
	/**
	 * check if the location is the end of road
	 */
	public void setEnd() {
		end = true;
	}
	/**
	 * make the position not available to click
	 */
	public void setDisable() {
		this.disable = true;
	}
	
//	public String toString() {
//		if(start) return "s" +disable + " `(" + x + "," + y + ")";
//		if(end) return "e" +disable+ " `(" + x + "," + y + ")";
//		if (road) return "-" +disable+ " `(" + x + "," + y + ")";
//		return "0" +disable+ " `(" + x + "," + y + ")";
//	}
}
