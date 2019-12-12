package model;

/**
 * @author Peilin Feng
 * @author Xiaomin Zhao
 * @author Dongzhe Chen
 * @author Jiacheng He
 * CSC 335, Fall 2019
 * team project, Map.java
 * 
 * purpose: This is a class for map, with represents the game board.
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
	/**
	 * get method to get player
	 * @return a player class obj
	 */
	public Player getPlayer() {
		return player;
	}
	/**
	 * get method to get the graph
	 * @return, the graph of points
	 */
	public Point[][] getGraph(){
		return graph;
	}
	/**
	 * add the point to the road
	 * @param point, the point to add to road
	 */
	public void addRoad(Point point) {
		road.add(point);
	}
	/**
	 * get method, return the road list
	 * @return the list of point that are road
	 */
	public ArrayList<Point> getRoads(){
		return road;
	}
	/**
	 * make a location to be a point
	 * @param x, location of the point in map
	 * @param y, location of the point in map
	 * @param point, the point obj
	 */
	public void update(int x, int y,Point point) {
		graph[x][y] = point;
	}
	/**
	 * make the point unable to click
	 * @param point, the location point obj
	 */
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
