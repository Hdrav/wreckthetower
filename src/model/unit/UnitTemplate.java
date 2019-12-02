package model.unit;

import model.utilities.ArmorTypes;
import model.utilities.WeaponTypes;

public class UnitTemplate {
	/**
	 * the template of a unit used to add new unit 
	 * in the user
	 */
	private static int INITIAL_COST=10;
	private static double INITIAL_TIME=0.4;
	private WeaponTypes weapon;
	private ArmorTypes armor;
	
	/**
	 * the constructor 
	 */
	public UnitTemplate(WeaponTypes weapon,ArmorTypes armor) {
		this.armor=armor;
		this.weapon=weapon;
	}
	
	//public UnitTemplate() {}
	
	/**
	 * 
	 * @param weapon to set 
	 */
	public void setWeapon(WeaponTypes weapon) {
		this.weapon=weapon;
	}
	/**
	 * 
	 * @param armor to set
	 */
	public void setArmor(ArmorTypes armor) {
		this.armor=armor;
	}
	/**
	 * 
	 * @return weapon
	 */
	public WeaponTypes getWeapon() {
		return this.weapon;
	}
	/**
	 * 
	 * @return armor
	 */
	public ArmorTypes getArmor() {
		return this.armor;
	}
	/**
	 * 
	 * @return initial cost of the unit
	 */
	public static int getINITIAL_COST() {
		return INITIAL_COST;
	}
	/**
	 * 
	 * @return the building time
	 */
	public double getBuildingTime() {
		return this.weapon.getWeaponBuildingTime()+this.armor.getArmorBuildingTime()+UnitTemplate.INITIAL_TIME;
	}

	public double getSpeed() {
		return (this.armor.getUnitSpeedValue());
	}
	
	public int getCost() {
		return (UnitTemplate.INITIAL_COST+this.weapon.getWeaponCost()+
				this.armor.getArmorCost());
	}
}
