package model;

import java.util.ArrayList;

public class Point {
	private int x;
	private int y;
	private boolean road;
	private Tower tower;
	private ArrayList<Monster> monsters = new ArrayList<>();
	private boolean start = false;
	private boolean end = false;
	public Point(int x,int y,boolean isRoad) {
		this.x = x;
		this.y = y;
		this.road = isRoad;
	}
	
	public boolean isRoad() {
		return road;
	}
	
	public void setRoad() {
		this.road = true;
	}
	public void setTower(Tower tower) {
		this.tower = tower;
	}
	public boolean hasTower() {
		return tower != null;
	}
	public void setMonster(Monster monster) {
		monsters.add(monster);
	}
	public Tower getTower() {
		return tower;
	}
	public ArrayList<Monster> getMonster() {
		return monsters;
	}

	public void sellTower() {
		this.tower = null;
	}

	public void clearMonster() {
		for(Monster monster:monsters) {
			if(monster.dead()) {
				monsters.remove(monster);
				monster = null;
			}
		}
	}
	public void setStart() {
		start = true;
	}
	public void setEnd() {
		end = true;
	}
	public boolean getStart() {
		return start;
	}
	public boolean getEnd() {
		return end;
	}
	public String toString() {
		if(start) return "s";
		if(end) return "e";
		if (road) return "-";
		return "0";
	}
}
