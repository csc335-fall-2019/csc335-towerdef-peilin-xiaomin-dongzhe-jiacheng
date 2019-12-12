/**
 * This is a class to represent a location in the game board.
 */
package model;

import java.util.ArrayList;

public class Point {

	private int x; //x-value of the game board
	private int y; //y-value of the game board
	private boolean road; // indicate if the location is the road
	private boolean isPass = false; 
	private Tower tower; 
	private ArrayList<Monster> monsters = new ArrayList<Monster>(); // list to store monsters
	private boolean start = false; 
	private boolean end = false;

	private ArrayList<Point> adjacentPoints = new ArrayList<Point>(); // list to store neiborgh locations

	private boolean disable = false;
	
	public Point(int x,int y,boolean isRoad) {  //construct a locate
		this.x = x;
		this.y = y;
		this.road = isRoad;
	}

	public void pass() {
		isPass = true;
	}
	public boolean getPass() {
		return isPass;
	}
	public int getX() { //get method return the x-value of the location

		return x;
	}
	public int getY() { //get method return the y-value of the location
		return y;

	}
	
	public boolean isRoad() { //return if the location is road
		return road;
	}


	public boolean isdisabled() {
		return disable;
	}
	
	public void setRoad() {  //set this location to road
		this.road = true;
	}
	public boolean canSetTower() { //indicate of this location can put a tower
		return !road && tower == null; //put when is not road and no tower in the location
		}
	public void setTower(Tower tower) {
		this.tower = tower; // set the tower at the location
	}

	public boolean hasTower() { // return if the location has a tower
		return tower != null;
	}

	public void setMonster(Monster monster) { //set the monster at this location
		monsters.add(monster); 
	}

	public Tower getTower() { //get method, return the tower at the location
		return tower;
	}
	public ArrayList<Monster> getMonster() { //get method, return the list of all monster in location
		return monsters;
	}

	public void sellTower() { //sell the tower at the location
		this.tower = null;
	}

	public void clearMonster() { // to delete a monster from the game board, when it die
		for(Monster monster:monsters) {
			if(monster.dead()) {
				monsters.remove(monster);
			}
		}
	}
	public void clearMonster(Monster monster) { // to delete the monster from game board, when monster reach the end

	
		for(Monster monsterIn:monsters) {
			if(monster.getHealth() == monsterIn.getHealth()) {
				monsters.remove(monsterIn);
				break;
			}
		}
	}

	public void setStart() { // set the location be the beginning of road

		start = true;
	}
	public void setEnd() { // set the location be the end of the road
		end = true;
	}

	public boolean isStart() { // return if the location is the beginning of road
		return start;
	}
	
	public void setDisable() {
		this.disable = true;
	}

	public boolean isEnd() { // check if the location is the end of road
		return end;
	}
	public String toString() { //used to debug, print the location and the property of the location
		if(start) return "s"+ " `(" + x + "," + y + ")";
		if(end) return "e" + " `(" + x + "," + y + ")";
		if (road) return "-" + " `(" + x + "," + y + ")";
		return "0" + " `(" + x + "," + y + ")";
	}

	
//	public String toString() {
//		if(start) return "s"+ " `(" + x + "," + y + ")";
//		if(end) return "e" + " `(" + x + "," + y + ")";
//		if (road) return "-" + " `(" + x + "," + y + ")";
//		return "0" + " `(" + x + "," + y + ")";
//	}
}
