package model;

public class Monster {
	protected int gold;
	protected int speed;
	protected int armor;
	protected int magicResist;
	protected int health;
	//protected boolean dead = false;
	public Monster(int gold,int speed,int armor,int magicResist,int health) {
		this.gold =gold;
		this.speed = speed;
		this.armor = armor;
		this.magicResist = magicResist;
		this.health = health;
	}
	
	public int getGold() {
		return gold;
	}
	public int getSpeed() {
		return speed;
	}
	public int getArmor() {
		return armor;
	}
	public int getMagicResist() {
		return magicResist;
	}
	public int getHealth() {
		return health;
	}
	public void newHealth(int health) {
		this.health = health;
	}
	public boolean dead() {
		return health == 0;
	}
}
