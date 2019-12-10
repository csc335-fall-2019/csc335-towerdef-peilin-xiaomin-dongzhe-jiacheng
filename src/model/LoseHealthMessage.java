package model;

import java.io.Serializable;

public class LoseHealthMessage implements Serializable {
	
	private Monster monster;
	private Player player;

	public LoseHealthMessage(Monster monster, Player player) {
		this.monster = monster;
		this.player = player;
	}
	
	public Monster getMonster() {
		return this.monster;
	}
	
	public Player getPlayer() {
		return this.player;
	}
}
