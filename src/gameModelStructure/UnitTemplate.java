package gameModelStructure;

import view.utilities.ArmorTypes;
import view.utilities.WeaponTypes;

public class UnitTemplate {
	
	private static int INITIAL_COST=10;
	private WeaponTypes weapon;
	private ArmorTypes armor;
	
	
	public UnitTemplate(WeaponTypes weapon,ArmorTypes armor) {
		this.armor=armor;
		this.weapon=weapon;
		
	}
	
	public UnitTemplate() {}
	
	
	public void setWeapon(WeaponTypes weapon) {
		this.weapon=weapon;
	}

	public void setArmor(ArmorTypes armor) {
		this.armor=armor;
	}
	
	public WeaponTypes getWeapon() {
		return this.weapon;
	}

	public ArmorTypes getArmor() {
		return this.armor;
	}
	/*public static class Builder{
	
		private WeaponTypes weaponB;
		private ArmorTypes armorB;
		
		public Builder setWeapon(WeaponTypes weapon) {
			this.weaponB=weapon;
			return this;
		}
		
		public Builder setArmor(ArmorTypes armor) {
			this.armorB=armor;
			return this;
		}
		
		public Builder() {}
		
		public UnitTemplate build() throws IllegalStateException {
			if (this.weaponB==null || this.armorB==null)
				throw new IllegalStateException("weapon and armor field can't be null");
			return new UnitTemplate(this);
		}
	}
	private UnitTemplate(){}
	
	public UnitTemplate(Builder builder) {
		this.armor=builder.armorB;
		this.weapon=builder.weaponB;
	}
	*/
	public double getSpeed() {
		return (this.armor.getUnitSpeedValue());
	}
	
	public int getCost() {
		return (UnitTemplate.INITIAL_COST+this.weapon.getWeaponCost()+
				this.armor.getArmorCost());
	}
}
