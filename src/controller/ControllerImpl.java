package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import gameModelStructure.*;
import view.utilities.*;

public class ControllerImpl implements Controller {
	
	private static ControllerImpl controller;
	private  Player user;
	private  Enemy enemy;
	private int level;
	//private final MovementManager movementManager;


	public void setEnemy (Enemy enemy) {
		this.enemy=enemy;

	}

	public ControllerImpl() {
		Tower towerPlayer= new TowerImpl();
		Tower towerEnemy= new TowerImpl();
		
		this.user = new User(160, towerPlayer,3);
		this.enemy = new Enemy(0,towerEnemy,2);
		/*
		 * enemy standard unit setting 
		 * 
		 * */
		/*this.enemy.getUnitTemplateList().get(0).setWeapon(WeaponTypes.SHORT_SWORD);
		this.enemy.getUnitTemplateList().get(0).setArmor(ArmorTypes.IRON_MEDIUM_ARMOR);
		this.enemy.getUnitTemplateList().get(1).setWeapon(WeaponTypes.SHORT_BOW);
		this.enemy.getUnitTemplateList().get(1).setArmor(ArmorTypes.LEATHER_LIGHT_ARMOR);
		this.enemy.getUnitTemplateList().get(2).setWeapon(WeaponTypes.GOLDEN_SPEAR);
		this.enemy.getUnitTemplateList().get(2).setArmor(ArmorTypes.GOLDEN_HEAVY_ARMOR);*/
		
		/*
		 * enemy strategy setting
		 * 
		 */
		this.enemy.setBattleStrategy(new LevelOneStrategy());
		this.level=1;
		this.enemy.setUnitTemplateList(new ArrayList<>());
		this.enemy.getUnitTemplateList().add(new UnitTemplate (WeaponTypes.NOTHING,ArmorTypes.NOTHING));
		this.enemy.getUnitTemplateList().add(new UnitTemplate (WeaponTypes.SHORT_SWORD,ArmorTypes.LEATHER_LIGHT_ARMOR));
	}
	
	public Player getUser() {
		return this.user;
	}
	
	public Player getEnemy() {
		return this.enemy;
	}
	
	public boolean canUserBuy(int index) {
		if(this.user.getUnitTemplateList().get(index).getCost()>=this.user.getMoney())
		return false;
		else return true;
	}
	
	public void setEquipmentUnitTemplate() {

		String[] equipmentName= new String[2];
		for(int i=0; i<this.user.getNumberOfUnit(); i++) {
			try {
			equipmentName=this.extractEquipmentNameFromSetting(i);
			}catch(IOException e) {}
		
			user.getUnitTemplateList().get(i).setWeapon(WeaponTypes.getWeapon(equipmentName[0]));
			user.getUnitTemplateList().get(i).setArmor(ArmorTypes.getArmor(equipmentName[1]));
		}
		if(this.level==1) {
			this.enemy.getUnitTemplateList().add(new UnitTemplate (WeaponTypes.GOLDEN_SPEAR,ArmorTypes.NOTHING));
			this.enemy.getUnitTemplateList().add(new UnitTemplate (WeaponTypes.SHORT_SWORD,ArmorTypes.LEATHER_LIGHT_ARMOR));
		}
	}
	  

	public String[] extractEquipmentNameFromSetting(int index) throws IOException {
		BufferedReader file = new BufferedReader(new FileReader(new File("").getAbsolutePath()+"/res/setting_files/unit_setting.txt"));
	    String extractedLine=new String("");
	    String line=new String("");
	    String equipmentName[];
	    int lineIndex=0;
	    
	    while (line!=null && lineIndex<=index ) {
    		if(lineIndex==index) {
    			extractedLine=file.readLine();
    		}
    		else{
    			line=file.readLine();
    		}
    		lineIndex=lineIndex+1;
	    }
	    
	    file.close();
	    equipmentName=extractedLine.split(",");
	    
	   
		return equipmentName;
	}

	  
	public UnitToMake nextUnitToMake() {
		return this.enemy.battleStrategy();
	}
	
	
	public static synchronized Controller getLog() {
	        if (controller == null) {
	            controller = new ControllerImpl();
	        }
	        return controller;
	}
	
	
	
	

}
