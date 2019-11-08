package view.utilities;

public enum ArmorsPath {
	
	
	LEATHER_LIGHT_ARMOR("leather_light_A_icon.png")
	
	,IRON_MEDIUM_ARMOR("iron_medium_A_icon.png")
	,GOLDEN_HEAVY_ARMOR("golden_heavy_A_icon.png");

	private static final String ARMOR_PATH="/armors_icon/";
	private String selectedArmor;
	
	ArmorsPath(final String armorName){
		
		this.selectedArmor=armorName;
	}
	
	public String getPath() {
		return ArmorsPath.ARMOR_PATH + this.selectedArmor;
	}
}


