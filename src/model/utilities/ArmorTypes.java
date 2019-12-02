package model.utilities;

public enum ArmorTypes {
	/**
	 * utility containing the 
	 * armor stats
	 */

	LEATHER_LIGHT_ARMOR("leather light armor", 10, 7, 0.75, 1.0)
	,IRON_MEDIUM_ARMOR("iron medium armor", 10, 15, 1.1, 1.0)
	,GOLDEN_HEAVY_ARMOR("golden heavy armor", 65, 52, 1.30, 3.2)
	,NOTHING("nothing", 0, 0, 1.0, 0.7);
	
	private static int NUMBER_OF_ARMOR=4;
	private String armorName;
	private int armorCost;
	private int armorLife;
	private double unitSpeedValue;
	private double armorBuildingTime;

	
	ArmorTypes(String armorName, int armorCost, int armorLife,
				double unitSpeedValue,double armorBuildingTime){
		
		this.armorName=armorName;
		this.armorCost=armorCost;
		this.armorLife=armorLife;
		this.unitSpeedValue=unitSpeedValue;
		this.armorBuildingTime=armorBuildingTime;
	}
	
	public static int getNumberOfArmor() {
		return ArmorTypes.NUMBER_OF_ARMOR;
	}

	public static String getName(int id) {
		switch(id) {
		case 0: return "leather light armor";
		case 1: return "iron medium armor";
		case 2: return "golden heavy armor";
		case 3: return "nothing";
		default: return "";
		}
	}
	public int getArmorCost() {
		return this.armorCost;
	}
	
	public static int getArmorCostView(String name) {
		switch(name) {
		case "leather light armor": {
			return ArmorTypes.LEATHER_LIGHT_ARMOR.getArmorCost();
		}
		case "iron medium armor":{
			return ArmorTypes.IRON_MEDIUM_ARMOR.getArmorCost();
		}
		
		case "golden heavy armor":{
			return ArmorTypes.GOLDEN_HEAVY_ARMOR.getArmorCost();
		}
		case "nothing":{
			return ArmorTypes.NOTHING.getArmorCost();
		}
		default: return 0;
		}
		 
	}
	
	public int getArmorLife() {
		return this.armorLife;
	}
	
	public double getUnitSpeedValue() {
		return this.unitSpeedValue;
	}
	
	public double getArmorBuildingTime() {
		return this.armorBuildingTime;
	}
	
	
	public String toString(){
		return this.armorName;
	}
	
	public static ArmorTypes getArmor(String name) {
		switch(name) {
		case "leather light armor": {
			return ArmorTypes.LEATHER_LIGHT_ARMOR;
		}
		case "iron medium armor":{
			return ArmorTypes.IRON_MEDIUM_ARMOR;
		}
		
		case "golden heavy armor":{
			return ArmorTypes.GOLDEN_HEAVY_ARMOR;
		}
		case "nothing":{
			return ArmorTypes.NOTHING;
		}
		}
		return ArmorTypes.NOTHING;
	}
	
}
