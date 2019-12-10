package model;

import java.util.ArrayList;

public class Map {
	private Player player;
	private Point[][] graph;
	private ArrayList<Point> road;
	private Point start;
	private Point end;
	
	public Map(Player player, int height, int width) {
		this.player = player;
		this.graph = new Point[height][width];
		this.road = new ArrayList<Point>();
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public Point[][] getGraph(){
		return graph;
	}
	
	public ArrayList<Point> getRoad() {
		return road;
	}
	
	public Point getStart() {
		return start;
	}
	
	public Point getEnd() {
		return end;
	}
	
	public void setStart(Point start) {
		this.start = start;
	}
	
	public void setEnd(Point end) {
		this.end = end;
	}
	
	public void addRoad(Point point) {
		this.road.add(point);
	}
	
	public void update(int x, int y,Point point) {
		this.graph[x][y] = point;
	}
	
	public String toString() {
		String str = "";
		for(int i =0;i<graph.length;i++) {
			for(int j = 0;j<graph[i].length;j++) {
				str += graph[i][j];
			}
			str+="\n";
		}
		return str;
	}
	
}
