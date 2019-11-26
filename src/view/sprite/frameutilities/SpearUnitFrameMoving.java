package view.sprite.frameutilities;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

public enum SpearUnitFrameMoving {
	
	/*
	 * spear movement
	 * 
	 * */
	 GOLDEN_SPEAR_MOVEMENT("golden_spear0",2)
	 ,GOLDEN_SPEAR_GIANT_MOVEMENT("giant/golden_spear_giant0",2)
	
	 /*
	  * 
	  * spear attack
	  * 
	  * */
	 ,GOLDEN_SPEAR_ATTACK("golden_spear_attack0",2)
	 ,GOLDEN_SPEAR_GIANT_ATTACK("giant/golden_spear_giant_attack0",2)
	 
	 /*
	  * unit attack
	  * 
	  * */
	 ,LANCER_ATTACK("lancer_attack0",2)
	 ,LANCER_GIANT_ATTACK("giant/lancer_giant_attack0",2)
	 
	 
	 /*
	  * armors movement
	  * 
	  * */
	 ,LANCER_IRON_ARMOR("iron_A_attack0",2)
	 ,LANCER_LEATHER_ARMOR("leather_A_attack0",2)
	 ,LANCER_GOLDEN_ARMOR("giant/golden_A_attack0",2)
	 
	 /*
	  * 
	  * weapon dying
	  * 
	  * */
	 ,GOLDEN_SPEAR_DYING("golden_spear_dying0",2)
	 ,GOLDEN_SPEAR_GIANT_DYING("giant/golden_spear_giant_dying0",3);
	 
	private  String path=new String("/animations/lancer/");
	private int numberOfFrame;
	private List<Image> animationList=new ArrayList<>();
	
	SpearUnitFrameMoving(String imageNameId, int frame){
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
