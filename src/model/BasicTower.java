package model;

public class BasicTower extends Tower {
	private int cost =1;
	private int attack = 2;
	private int abilityPower = 0;
	private int range =10;
	private int attackSpeed = 1;
	public BasicTower() {
		super.setCost(cost);
		super.setAttack(attack);
		super.setAbilityPower(abilityPower);
		super.setAttackSpeed(attackSpeed);
		super.setRange(range);
	}
	
}
