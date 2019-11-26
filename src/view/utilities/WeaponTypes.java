package view.utilities;

public enum WeaponTypes {
	

	SHORT_SWORD("short sword",true, 10, 5, 0.8, 0.6, 2,0.7)
	,SHORT_BOW("short bow", false,25, 4, 1.0, 1.0,12,0.8)
	,GOLDEN_SPEAR("golden spear",true, 45, 15, 1, 2.3,4,1.3)
	,NOTHING("nothing",true, 0, 2, 0.7, 0.5,2,0.9);

	private static int NUMBER_OF_WEAPON=4;
	private String weaponName;
	private Boolean melee;
	private int weaponCost;
	private int weaponDamage;
	private double weaponAttackSpeed;
	private double weaponBuildingTime;
	private double weaponDelay;
	
	WeaponTypes(String weaponName,Boolean melee, int weaponCost, int weaponDamage,
				double weaponAttackSpeed,double weaponBuildingTime,int weaponReach,double weaponDelay){

		this.weaponName=weaponName;
		this.melee=melee;
		this.weaponCost=weaponCost;
		this.weaponDamage=weaponDamage;
		this.weaponAttackSpeed=weaponAttackSpeed;
		this.weaponBuildingTime=weaponBuildingTime;
		this.weaponDelay=weaponDelay;
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
	
	public boolean isMelee() {
		return this.melee;
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
	
	public double getWeaponDelay() {
		return this.weaponDelay;
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
