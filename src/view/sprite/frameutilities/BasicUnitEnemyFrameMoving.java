package view.sprite.frameutilities;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

public enum BasicUnitEnemyFrameMoving {

	
	/*
	 * body frames initialization 
	 * 
	 * */
	BODY("unit_movement0",2)
	,HUGEBODY("giant/unit_giant_movement0",2)
	
	/*
	 * armor moving frames initialization
	 */
	,LEATHER_LIGHT_ARMOR("leather_armor0",2)
	,IRON_MEDIUM_ARMOR("iron_armor0",2)
	,GOLDEN_HEAVY_ARMOR("giant/golden_armor0",2)
	,NOTHING("",0)
	
	/*
	 * body death initialization
	 * 
	 * */
	,BODY_DYING("unit_dying0",3)
	,HUGEBODY_DYING("giant/unit_giant_dying0",4)
	
	/*  
	 * armor death initialization
	 *  
	 * */
	,LEATHER_LIGHT_ARMOR_DYING("leather_A_dying0",2)
	,GOLDEN_HEAVY_DYING("giant/golden_A_dying0",4)
	,IRON_MEDIUM_ARMO_DYING("iron_A_dying0",2);
	
	
	private  String path=new String("/animations/enemies_animation/basic_player_animations/");
	private int numberOfFrame;
	private List<Image> animationList=new ArrayList<>();
	
	BasicUnitEnemyFrameMoving(String imageNameId,int numberOfFrame){
		this.numberOfFrame=numberOfFrame;
			for(int i=1; i<=this.numberOfFrame; i++) {
				animationList.add(new Image(new String(path+imageNameId+i+".png")));
			}
		}
	
	public List<Image> getFrameList() {
		return this.animationList;
	}
	
	
}




