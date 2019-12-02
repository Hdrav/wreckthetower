package view.sprite.frameutilities;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

public enum SwordUnitEnemyFrameMoving {
	
	/*
	 * sword utility frame animation 
	 * 
	 */
	
	/*
	 * sword moving
	 * 
	 * */
	SHORT_SWORD_MOVEMENT("short_sword0",2)
    ,SHORT_SWORD_GIANT_MOVEMENT("giant/short_sword_giant0",2)
	
    
    /*
     * 
     * sword attack
     * 
     * */
    ,SHORT_SWORD_ATTACK("short_sword_attack0",2)
    ,SHORT_SWORD_GIANT_ATTACK("giant/short_sword_giant_attack0",2)
    
    /*
	 * 
	 * unit attack
	 * 
	 * */
    ,SWORDMAN_ATTACK("swordman_movement0",2)
    ,SWORDMAN_GIANT_ATTACK("giant/swordman_giant_movement0",2)
  
    
    /*
     * armors movement
     * 
     * */
    ,SWORDMAN_IRON_ARMOR("swordman_iron_A0",2)
    ,SWORDMAN_LEATHER_ARMOR("swordman_leather_A0",2)
    ,SWORDMAN_GOLDEN_ARMOR("giant/swordman_golden_A0",2)
    
    /*
     * 
     * weapon dying
     * 
     * */
    ,SHORT_SWORD_DYING("short_sword_dying0",2)
    ,SHORT_SWORD_GIANT_DYING("giant/short_sword_giant_dying0",2)
    
    
	
	,NOTHING("",0);
	
	
	
	private  String path=new String("/animations/enemies_animation/swordman/");
	private int numberOfFrame;
	private List<Image> animationList=new ArrayList<>();
	
	SwordUnitEnemyFrameMoving(String imageNameId, int frame){
		this.numberOfFrame=frame;
		this.animationList=new ArrayList<>();
		for(int i=1; i<=this.numberOfFrame; i++) {
			animationList.add(new Image(new String(path+imageNameId+i+".png")));
			
		}
	}
	
	public List<Image> getFrameList() {
		return this.animationList;
	}

}
