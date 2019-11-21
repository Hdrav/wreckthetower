package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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
	
	public void setEquipmentUnitTemplate() {

		String[] equipmentName= new String[2];
		for(int i=0; i<this.user.getNumberOfUnit(); i++) {
			try {
			equipmentName=this.extractEquipmentNameFromSetting(i);
			}catch(IOException e) {}
		
			user.getUnitTemplateList().get(i).setWeapon(WeaponTypes.getWeapon(equipmentName[0]));
			user.getUnitTemplateList().get(i).setArmor(ArmorTypes.getArmor(equipmentName[1]));
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

	  
	public static synchronized Controller getLog() {
	        if (controller == null) {
	            controller = new ControllerImpl();
	        }
	        return controller;
	}
	
	
	
	

}
