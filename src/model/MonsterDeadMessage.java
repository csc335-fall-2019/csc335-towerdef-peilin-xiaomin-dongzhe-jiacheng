package model;

import java.io.Serializable;

public class MonsterDeadMessage implements Serializable {
	
	private Monster monster;
	private int money;

	public MonsterDeadMessage(Monster monster,int money) {
		this.monster = monster;
		this.money = money;
	}
	
	public Monster getMonster() {
		return monster;
	}
	
	public int getMoney() {
		return money;
	}

}
