package model;

public class Point {
	private int x;
	private int y;
	private boolean road;
	private Tower tower;
	private Monster monster;
	public Point(int x,int y,boolean isRoad) {
		this.x = x;
		this.y = y;
		this.road = isRoad;
	}
	
	public boolean isRoad() {
		return road;
	}
	public void setTower(Tower tower) {
		this.tower = tower;
	}
	public boolean hasTower() {
		return tower != null;
	}
	public void setMonster(Monster monster) {
		this.monster = monster;
	}
	public Tower getTower() {
		return tower;
	}
	public Monster getMonster() {
		return monster;
	}
	
	public void sellTower() {
		this.tower = null;
	}
	
	public void clear() {
		if(tower != null) tower = null;
		if(monster != null) monster = null;
	}
}
