package model;

public class BasicTower extends Tower {
	private int cost =1;
	private int attack = 2;
	private int range =10;
	private double attackSpeed = 1;
	public BasicTower() {
		super.setCost(cost);
		super.setAttack(attack);
		super.setAttackSpeed(attackSpeed);
		super.setRange(range);
	}
	
}
