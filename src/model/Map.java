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
	public void update(int x, int y,Point point) {
		graph[x][y] = point;
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
