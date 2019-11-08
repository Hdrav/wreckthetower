package controller;

import gameModelStructure.*;
import view.utilities.*;

public class ControllerImpl implements Controller {
	
	private static ControllerImpl controller;
	private  Player user;
	private  Enemy enemy;
	//private final MovementManager movementManager;


	public void setEnemy (Enemy enemy) {
		this.enemy=enemy;

	}

	public ControllerImpl() {
		Tower towerPlayer= new TowerImpl();
		Tower towerEnemy= new TowerImpl();
		LineUp lineupPlayer= new LineUpImpl();
		LineUp lineupEnemy= new LineUpImpl();
		
		this.user = new User(80, towerPlayer, lineupPlayer);
		this.enemy = new Enemy(80, towerEnemy,lineupEnemy);
		/*
		 * enemy standard unit setting 
		 * 
		 * */
		this.enemy.getUnitTemplateList().get(0).setWeapon(WeaponTypes.SHORT_SWORD);
		this.enemy.getUnitTemplateList().get(0).setArmor(ArmorTypes.IRON_MEDIUM_ARMOR);
		this.enemy.getUnitTemplateList().get(1).setWeapon(WeaponTypes.SHORT_BOW);
		this.enemy.getUnitTemplateList().get(1).setArmor(ArmorTypes.LEATHER_LIGHT_ARMOR);
		this.enemy.getUnitTemplateList().get(2).setWeapon(WeaponTypes.GOLDEN_SPEAR);
		this.enemy.getUnitTemplateList().get(2).setArmor(ArmorTypes.GOLDEN_HEAVY_ARMOR);
		
		/*
		 * enemy strategy setting
		 * 
		 */
		this.enemy.setBattleStrategy(new EasyStrategy());
	}
	
	public Player getUser() {
		return this.user;
	}
	
	public void setWeaponUnitTemplate(int index, String name) {
		this.user.getUnitTemplateList().get(index).setWeapon(WeaponTypes.getWeapon(name));
		this.user.getUnitTemplateList().get(index).setArmor(ArmorTypes.getArmor(name));
	}
	  
	  

	  
	public static synchronized Controller getLog() {
	        if (controller == null) {
	            controller = new ControllerImpl();
	        }
	        return controller;
	}
	
	
	
	

}
