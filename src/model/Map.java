package model;

/**
 * @author Peilin Feng
 * @author Xiaomin Zhao
 * @author Dongzhe Chen
 * @author Jiacheng He
 * CSC 335, Fall 2019
 * team project, Map.java
 * 
 * purpose: 
 */

import java.util.ArrayList;

public class Map {
	private Player player;
	private Point[][] graph;
	private ArrayList<Point> road;
	
	public Map(Player player,int height,int width) {
		graph = new Point[height][width];
		road = new ArrayList<Point>();
		this.player = player;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public Point[][] getGraph(){
		return graph;
	}
	public void addRoad(Point point) {
		road.add(point);
	}
	public ArrayList<Point> getRoads(){
		return road;
	}
	public void update(int x, int y,Point point) {
		graph[x][y] = point;
	}
	
	public void setPointDisable(Point point) {
		this.graph[point.getX()][point.getY()].setDisable();
	}
	
	
//	public String toString() {
//		String str = "";
//		for(int i =0;i<graph.length;i++) {
//			for(int j = 0;j<graph[i].length;j++) {
//				str += graph[i][j];
//			}
//			str+="\n";
//		}
//		return str;
//	}
	
}
