package model;

public class Monster {
	protected int gold;
	protected int speed;
	protected int armor;
	protected int magicResist;
	protected int health;
	protected int energy;
	//protected boolean dead = false;
	public Monster() {
		
	}
	public Monster(int gold,int speed,int armor,int magicResist,int health, int energy) {
		this.gold =gold;
		this.speed = speed;
		this.armor = armor;
		this.magicResist = magicResist;
		this.health = health;
		this.energy = energy;
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
	public int getEnergy() {
		return energy;
	}
	public void newHealth(int health) {
		this.health = health;
	}
	public boolean dead() {
		return health == 0;
	}
	public void newSpeed(int speed) {
		this.speed = speed;
	}
}
