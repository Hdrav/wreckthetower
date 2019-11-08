package view.utilities;

public enum WeaponTypes {
	

	SHORT_SWORD("short sword", 10, 5, 0.8, 0.6, 2)
	,SHORT_BOW("short bow", 25, 4, 1.0, 1.0,12)
	,GOLDEN_SPEAR("golden spear", 45, 15, 1, 2.3,4)
	,NOTHING("nothing", 0, 1, 0.7, 0.5,2);

	private static int NUMBER_OF_WEAPON=4;
	private String weaponName;
	private int weaponCost;
	private int weaponDamage;
	private double weaponAttackSpeed;
	private double weaponBuildingTime;
	private int weaponReach;
	
	WeaponTypes(String weaponName, int weaponCost, int weaponDamage,
				double weaponAttackSpeed,double weaponBuildingTime,int weaponReach){
		
		this.weaponName=weaponName;
		this.weaponCost=weaponCost;
		this.weaponDamage=weaponDamage;
		this.weaponAttackSpeed=weaponAttackSpeed;
		this.weaponBuildingTime=weaponBuildingTime;
		this.weaponReach=weaponReach;
	}

	public static int getNumberOfWeapon() {
		return WeaponTypes.NUMBER_OF_WEAPON;
	}
	
	public static String getName(int id) {
		switch(id) {
		case 0: return "short sword";
		case 1: return "short bow";
		case 2: return "golden spear";
		case 3: return "nothing";
		default: return "";
		}
		
	}
	
	public static int getWeaponCostView(String name) {
		switch(name) {
		case "short sword": {
			return WeaponTypes.SHORT_SWORD.getWeaponCost();
		}
		case "short bow":{
			return WeaponTypes.SHORT_BOW.getWeaponCost();
		}
		
		case "golden spear":{
			return WeaponTypes.GOLDEN_SPEAR.getWeaponCost();
		}
		case "nothing":{
			return WeaponTypes.NOTHING.getWeaponCost();
		}
		default:return 0;
		}
	}
	
	public int getWeaponCost() {
		return this.weaponCost;
	}
	
	public int getWeaponDamage() {
		return this.weaponDamage;
	}
	
	public double getWeaponAttackSpeed() {
		return this.weaponAttackSpeed;
	}
	
	public double getWeaponBuildingTime() {
		return this.weaponBuildingTime;
	}
	
	public int getWeaponReach() {
		return this.weaponReach;
	}
	
	public String toString(){
		return this.weaponName;
	}
	
	
	public static WeaponTypes getWeapon(String name) {
		switch(name) {
		case "short sword": {
			return WeaponTypes.SHORT_SWORD;
		}
		case "short bow":{
			return WeaponTypes.SHORT_BOW;
		}
		
		case "golden spear":{
			return WeaponTypes.GOLDEN_SPEAR;
		}
		case "nothing":{
			return WeaponTypes.NOTHING;
		}
		}
		return WeaponTypes.NOTHING;
	}
}
