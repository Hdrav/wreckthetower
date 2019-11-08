package view.utilities;

public enum ArmorTypes {


	LEATHER_LIGHT_ARMOR("leather light armor", 10, 7, 1.0, 1.0,4)
	,IRON_MEDIUM_ARMOR("iron medium armor", 10, 15, 0.8, 1.0,4)
	,GOLDEN_HEAVY_ARMOR("golden heavy armor", 65, 52, 0.58, 3.2,5)
	,NOTHING("nothing", 0, 0, 1.0, 0.7,4);
	
	private static int NUMBER_OF_ARMOR=4;
	private String armorName;
	private int armorCost;
	private int armorLife;
	private double unitSpeedPercentage;
	private double armorBuildingTime;
	private int bodySize;
	
	ArmorTypes(String armorName, int armorCost, int armorLife,
				double unitSpeedPercentage,double armorBuildingTime,int bodySize){
		
		this.armorName=armorName;
		this.armorCost=armorCost;
		this.armorLife=armorLife;
		this.unitSpeedPercentage=unitSpeedPercentage;
		this.armorBuildingTime=armorBuildingTime;
		this.bodySize= bodySize;
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
	
	public double getUnitSpeedPercentage() {
		return this.unitSpeedPercentage;
	}
	
	public double getArmorBuildingTime() {
		return this.armorBuildingTime;
	}
	
	public int getBodySize() {
		return this.bodySize;
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
