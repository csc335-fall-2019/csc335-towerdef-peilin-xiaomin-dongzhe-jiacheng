package model;

public class Map {
	private Player player;
	private Point[][] graph;
//	private int health;
	
	
	
	public Map(Player player,int x,int y) {
		graph = new Point[x][y];
		this.player = player;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public Point[][] getGraph(){
		return graph;
	}
	
}
