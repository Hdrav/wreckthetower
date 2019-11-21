package view.sprite;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

public enum BowUnitFrameMoving {

	/*
	 * bow movement
	 * 
	 * */
	 SHORT_BOW_MOVEMENT("short_bow0",2)
	 ,SHORT_BOW_GIANT_MOVEMENT("giant/bow_giant0",2)
	
	 /*
	  * bow charging
	  * 
	  * */
	 ,SHORT_BOW_CHARGING("short_bow_charging0",2)
	 ,SHORT_BOW_GIANT_CHARGING("giant/bow_giant_charging0",2)
	 
	 /*
	  * 
	  * bow attack
	  * 
	  * */
	 ,SHORT_BOW_ATTACK("short_bow_arrow0",2)
	 ,SHORT_BOW_GIANT_ATTACK("short_bow_giant_arrow0",2)
	 
	 
	 /*
	  * unit charging arrow
	  * 
	  * */
	 ,ARCHER_CHARGING("archer_charging0",2)
	 ,ARCHER_GIANT_CHARGING("giant/archer_giant_charging0",2)
	 
	 
	 /*
	  * unit attack
	  * 
	  * */
	 ,ARCHER_ATTACK("archer_arrow0",2)
	 ,ARCHER_GIANT_ATTACK("giant/archer_giant_arrow0",2)
	 
	 
	 
	 /*
	  * armors body charging movement
	  * 
	  * */
	 ,ARCHER_IRON_ARMOR_CHARGE("archer_arrow_ironA0",2) 
	 ,ARCHER_LEATHER_ARMOR_CHARGE("archer_leather_A0",2)
	 ,ARCHER_GOLDEN_ARMOR_CHARGE("giant/archer_golden_A0",2)
	 
	 
	 /*
	  * armors body attack movement
	  * 
	  * */
	 ,ARCHER_IRON_ARMOR_ATTACK("archer_arrow_ironA0",2)
	 ,ARCHER_LEATHER_ARMOR_ATTACK("archer_leather_arrow_A0",2)
	 ,ARCHER_GOLDEN_ARMOR_ATTACK("giant/archer_golden_A_arrow0",2)
	 
	 
	 /*
	  * 
	  * weapon dying
	  * 
	  * */
	 ,SHORT_BOW_DYING("short_bow_dying0",2);
	
	 
	private  String path=new String("/animations/swordman/");
	private int numberOfFrame;
	private List<Image> animationList=new ArrayList<>();
	
	BowUnitFrameMoving(String imageNameId, int frame){
		this.animationList=new ArrayList<>();
		for(int i=0; i<this.numberOfFrame; i++) {
			animationList.add(new Image(new String(path+imageNameId+i+".png")));
		}
	}
	
	public List<Image> getFrameList() {
		return this.animationList;
	}
}
