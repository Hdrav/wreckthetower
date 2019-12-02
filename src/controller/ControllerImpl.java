package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;


import controller.audio.AudioManager;
import javafx.animation.Timeline;
import javafx.stage.Stage;
import model.level.LevelData;
import model.player.Player;
import model.player.User;
import model.player.enemy.Enemy;
import model.tower.Tower;
import model.tower.TowerImpl;
import model.unit.UnitToMake;
import model.utilities.ArmorTypes;
import model.utilities.WeaponTypes;
import view.utilities.*;

public class ControllerImpl implements Controller {
	
	private static ControllerImpl controller;
	public static synchronized Controller getLog() {
	        if (controller == null) {
	            controller = new ControllerImpl();
	        }
	        return controller;
	}
	
	private  Player user;
	private  Enemy enemy;
	private LevelData levelData;
	private AudioManager audio;
	private int level;



	private int maxLevel;
	
	/*
	 * constructor
	 * */
	public ControllerImpl()  {
		this.audio=new AudioManager();
		try {
			this.extractLevelReached();
		} catch (FileNotFoundException e) {}		
	}
	

	public boolean canUserBuy(int index) {
		if(this.user.getUnitTemplateList().get(index).getCost()>this.user.getMoney().getMoney()) {
		return false;
		}
		else return true;
	}
	
	public String[] extractEquipmentNameFromSetting(int index) throws IOException {
		BufferedReader file = new BufferedReader(new FileReader(new File("").getAbsolutePath()+"/resources/setting_files/unit_setting.txt"));
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
	
	
	public void extractLevelReached() throws FileNotFoundException {
		BufferedReader file = new BufferedReader(new FileReader(new File("").getAbsolutePath()+"/resources/setting_files/level_setting.txt"));
	    String extractedLine=new String("");
	    try {
			extractedLine=file.readLine();
		} catch (IOException e) {}
	    String level= extractedLine.substring(12);
	    try {
			file.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	   int result= Integer.parseInt(level);
	   this.maxLevel=result;
	
	}
	
	public Enemy getEnemy() {
		return this.enemy;
	}
	
	@Override
	public int getLevel() {
		return this.level;
	}
	
	
	public int getMaxLevel() {
		return this.maxLevel;
	}
	
	public Player getUser() {
		return this.user;
	}
	
	
	public void initializeGame(int level) {
		this.level=level;
		levelData=LevelData.getLevelData(level);
		Tower towerPlayer= new TowerImpl(this.levelData.getTowerUserLife());
		Tower towerEnemy= new TowerImpl(this.levelData.getTowerEnemyLife());
		this.user= new User(levelData.getGold(),towerPlayer,levelData.getNumberOfUnit());
		this.enemy= new Enemy(0,towerEnemy,2);
		this.enemy.setBattleStrategy(levelData.getEnemyStrategy());
		
	}
	  

	public UnitToMake nextUnitToMake() {
		return this.enemy.battleStrategy();
	}

	public void setEnemy (Enemy enemy) {
		this.enemy=enemy;

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
	
	
	@Override
	public void setLevel(int level) {
		this.level=level;
		
	}

	
	public void setLevelReach(int level) throws FileNotFoundException {
		if(level>=this.maxLevel) {
			FileOutputStream file = new FileOutputStream(new File("").getAbsolutePath()+"/resources/setting_files/level_setting.txt");
		    StringBuffer inputBuffer = new StringBuffer();
		    inputBuffer.append("level reach:"+(this.level+1));
		    try {
			 file.write(inputBuffer.toString().getBytes());
			 file.close();
		    } catch (IOException e) {}
		    this.maxLevel=level;
		}
	}

	public void startMusic() {
		if(!this.audio.getMusic().isPlaying()) {
			this.audio.getMusic().play();
		}
	}

	public void stopMusic() {
		this.audio.getMusic().stop();
	}


}
