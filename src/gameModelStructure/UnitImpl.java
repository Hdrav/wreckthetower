package gameModelStructure;

import view.utilities.ArmorTypes;
import view.utilities.WeaponTypes;

public class UnitImpl implements Unit {

	private static int INITIAL_LIFE=10;
	private static int STARTING_FOOT_POSITION=-1;
	
	private int life;
	private int currentLife;
	private double timeOfTraining;
	private double speed;
	private int damage;
	private boolean alive;
	private int bodySize;
	private int footPosition;
	private double weaponDelay;
//	private boolean playerUnit;
	
	public UnitImpl(WeaponTypes weapon,ArmorTypes armor){
	//	this.playerUnit=playerUnit;
		this.life=(armor.getArmorLife()+UnitImpl.INITIAL_LIFE);
		this.currentLife=this.life;
		this.timeOfTraining=(armor.getArmorBuildingTime()
							 +weapon.getWeaponBuildingTime());
		this.speed=armor.getUnitSpeedValue();
		this.damage=weapon.getWeaponDamage();
		this.alive=true;
		this.bodySize=armor.getBodySize();
		this.footPosition=UnitImpl.STARTING_FOOT_POSITION;
		this.weaponDelay=weapon.getWeaponDelay();
	}
	
	@Override
	public int getCurrentLife() {
		return this.currentLife;
	}

	@Override
	public int getDamage() {
		return this.damage;
	}

	@Override
	public double getSpeed() {
		return this.speed;
	}

	@Override
	public int getLife() {
		return this.life;
	}

	@Override
	public double getTimeOfTraining() {
		return this.timeOfTraining;
	}
	
	public int getFootPosition() {
		return this.footPosition;
	}
	
	public boolean isAlive() {
		return this.alive;
	}
	
	public boolean isPlayerUnit(){
		return this.playerUnit;
	}

	public int getUnitReach() {
		return this.unitReach;
	}
	@Override
	public void reduceHealth(int damage) {
		if(this.currentLife<damage) {
			this.alive=false;
			this.currentLife=0;
		}
		else this.currentLife-=damage;
	}
	
	public int getBodySize() {
		return this.bodySize;
	}
	
}
