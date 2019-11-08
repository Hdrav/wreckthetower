package view.utilities;

public enum WeaponsPath {
	
	
	SHORT_SWORD("short_sword.png")
	
	,SHORT_BOW("short_bow_icon.png")
	,GOLDEN_SPEAR("golden_spear_icon.png");

	private static final String WEAPON_PATH="/weapon_icon/";
	private String selectedWeapon;
	
	WeaponsPath(final String weaponName){
		
		this.selectedWeapon=weaponName;
	}
	
	public String getPath() {
		return WeaponsPath.WEAPON_PATH + this.selectedWeapon;
	}
}
