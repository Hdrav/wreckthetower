package model.unit;

import model.utilities.ArmorTypes;
import model.utilities.WeaponTypes;

public class UnitImpl implements Unit {

	private static int INITIAL_LIFE=10;
	private int life;
	private int currentLife;
	private double speed;
	private int damage;
	private boolean alive;
	private int value;

	
	public UnitImpl(WeaponTypes weapon,ArmorTypes armor){
		this.life=(armor.getArmorLife()+UnitImpl.INITIAL_LIFE);
		this.currentLife=this.life;
		this.speed=armor.getUnitSpeedValue();
		this.damage=weapon.getWeaponDamage();
		this.alive=true;
		this.value=UnitTemplate.getINITIAL_COST()+((weapon.getWeaponCost()+armor.getArmorCost())*115/100);
	}
	
	public int getCurrentLife() {
		return this.currentLife;
	}

	public int getDamage() {
		return this.damage;
	}

	public double getSpeed() {
		return this.speed;
	}

	public int getLife() {
		return this.life;
	}
	
	public boolean isAlive() {
		return this.alive;
	}
	
	public void reduceHealth(int damage) {
		if(this.currentLife<damage) {
			this.alive=false;
			this.currentLife=0;
		}
		else this.currentLife-=damage;
	}

	public int getValue() {
		return value;
	}
	
}
