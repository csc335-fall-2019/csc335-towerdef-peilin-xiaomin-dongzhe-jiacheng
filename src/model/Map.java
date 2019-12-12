/**
 * A map to represent the game board.
 */
package model;

import java.util.ArrayList;

public class Map {

	private Player player; //user player
	private Point[][] graph; // the game board graph
	private ArrayList<Point> road; // a list to store all the road point
//	private int health;
	


	public Map(Player player,int height,int width) { // construct the map class
		graph = new Point[height][width]; // game board
		road = new ArrayList<Point>();  // list of road points
		this.player = player;
	}
	
	public Player getPlayer() { //get method return the player
		return player;
	}
	
	public Point[][] getGraph(){ // get method return the game board as a 2d point list
		return graph;
	}
	public void addRoad(Point point) { // add the point to the road
		road.add(point);
	}
	public ArrayList<Point> getRoads(){ // get method, return the road list
		return road;
	}
	public void update(int x, int y,Point point) { //make a location to be a point
		graph[x][y] = point;
	}

	public String toString() { // print the game board as 2d list
		String str = "";
		for(int i =0;i<graph.length;i++) {
			for(int j = 0;j<graph[i].length;j++) {
				str += graph[i][j];
			}
			str+="\n";
		}
		return str;
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
