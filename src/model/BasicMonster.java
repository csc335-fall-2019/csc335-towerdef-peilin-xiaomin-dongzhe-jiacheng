package model;

public class BasicMonster extends Monster {
	private int gold = 10;
	private int speed = 1;
	private int armor = 1;
	private int health = 10;
	private int energy = 10;
	public BasicMonster() {
		super.setArmor(armor);
		super.setEnergy(energy);
		super.setGold(gold);
		super.setSpeed(speed);
		super.setHealth(health);
	}
}
