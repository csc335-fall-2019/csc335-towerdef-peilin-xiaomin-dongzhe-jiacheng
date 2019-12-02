package model;

public class Map {
	private Player player;
	private Point[][] graph;
//	private int health;
	

	
	public Map(Player player,int height,int width) {
		graph = new Point[height][width];
		this.player = player;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public Point[][] getGraph(){
		return graph;
	}
	
}
