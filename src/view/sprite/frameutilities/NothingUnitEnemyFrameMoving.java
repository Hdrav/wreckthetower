package view.sprite.frameutilities;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

public enum NothingUnitEnemyFrameMoving {

	 /*
	  * unit attack
	  * 
	  * */
	 NOTHING_ATTACK("nothing_punch0",2)
	 ,NOTHING_GIANT_ATTACK("giant/unit_giant_nudge0",2)
	 
	 
	 /*
	  * armors movement
	  * 
	  * */
	 ,NOTHING_IRON_ARMOR("iron_A_punching0",2)
	 ,NOTHING_LEATHER_ARMOR("leather_A_punching0",2)
	 ,NOTHING_GOLDEN_ARMOR("giant/golden_armor_nothing0",2);
	 
	 
	private  String path=new String("/animations/enemies_animation/nothing/");
	private int numberOfFrame;
	private List<Image> animationList;
	
	NothingUnitEnemyFrameMoving(String imageNameId, int frame){
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
