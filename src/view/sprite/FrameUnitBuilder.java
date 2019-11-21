package view.sprite;

import java.io.IOException;
import java.util.List;

import javafx.scene.image.Image;
import view.scenecontroller.SceneControllerImpl;

public class FrameUnitBuilder extends SceneControllerImpl {
	
	private String armorName;
	private String weaponName;
	private List<Image> bodyMovingFrameList;
	private List<Image> armorMovingFrameList;
	private List<Image> weaponMovingFrameList;
	
	private List<Image> bodyAttackFrameList;
	private List<Image> armorAttackFrameList;
	private List<Image> weaponAttackFrameList;
	
	private List<Image> bodyDyingFrameList;
	private List<Image> armorDyingFrameList;
	private List<Image> weaponDyingFrameList;
	
	private double height;
	private double width;
	private double boundaryHeight;
	private double boundaryWidth;
	private double xOffset;
	private double yOffset;

	
	private FrameUnitBuilder(int index) {
		
		try {
			this.armorName=this.getController().extractEquipmentNameFromSetting(index)[1];
			this.weaponName=this.getController().extractEquipmentNameFromSetting(index)[0];
		} catch (IOException e) {
			
			e.printStackTrace();
		}

		if(armorName.contains("heavy")){
			this.height=120;
			this.width=120;
			this.boundaryHeight=54;
			this.boundaryWidth=57;
			this.xOffset=30;
			this.yOffset=-66;
			
			
			this.initializeHeavyUnit(weaponName);
		}
		else { 
			this.height=120;
			this.width=120;
			this.boundaryHeight=40;
			this.boundaryWidth=49;
			this.xOffset=38;
			this.yOffset=-80;
			this.bodyMovingFrameList=BasicUnitFrameMoving.BODY.getFrameList();
			
			if(weaponName.contains("sword")) {
				System.out.println("ha la spada");
				this.setSwordman(armorName);
			}
			else if(weaponName.contains("golden spear")) {
				
				this.setSpearman(armorName);
			}
			else if(weaponName.equals("nothing")) {
				
				this.setNothingman(armorName);
			}
		}
	}
	
	public static FrameUnitBuilder newFrameUnitBuilder(int index) {
		return new FrameUnitBuilder(index);
	}
	
	private void setIronMediumArmor() {
		this.armorMovingFrameList=BasicUnitFrameMoving.IRON_MEDIUM_ARMOR.getFrameList();
		this.armorDyingFrameList=BasicUnitFrameMoving.IRON_MEDIUM_ARMO_DYING.getFrameList();
		
	}
	
	private void setLeatherLightArmor() {
		
		this.armorMovingFrameList=BasicUnitFrameMoving.LEATHER_LIGHT_ARMOR.getFrameList();
		this.armorDyingFrameList=BasicUnitFrameMoving.LEATHER_LIGHT_ARMOR_DYING.getFrameList();
		
	}
	
	
	private void setGiantSword() {
		this.bodyAttackFrameList=SwordUnitFrameMoving.SWORDMAN_GIANT_ATTACK.getFrameList();
		this.armorAttackFrameList=SwordUnitFrameMoving.SWORDMAN_GOLDEN_ARMOR.getFrameList();
		this.weaponMovingFrameList=SwordUnitFrameMoving.SHORT_SWORD_GIANT_MOVEMENT.getFrameList();
		this.weaponAttackFrameList=SwordUnitFrameMoving.SHORT_SWORD_GIANT_ATTACK.getFrameList();
		this.weaponDyingFrameList=SwordUnitFrameMoving.SHORT_SWORD_GIANT_DYING.getFrameList();
		
	}
	
	private void setSwordman(String armorName) {
		this.bodyAttackFrameList=SwordUnitFrameMoving.SWORDMAN_ATTACK.getFrameList();
		this.weaponMovingFrameList=SwordUnitFrameMoving.SHORT_SWORD_MOVEMENT.getFrameList();
		this.weaponAttackFrameList=SwordUnitFrameMoving.SHORT_SWORD_ATTACK.getFrameList();
		this.weaponDyingFrameList=SwordUnitFrameMoving.SHORT_SWORD_DYING.getFrameList();
		
		if(armorName.equals("iron medium armor")) {
			this.setIronMediumArmor();
			this.armorAttackFrameList=SwordUnitFrameMoving.SWORDMAN_IRON_ARMOR.getFrameList();
		}
		if(armorName.equals("leather light armor")) {
			this.setLeatherLightArmor();
			this.armorAttackFrameList=SwordUnitFrameMoving.SWORDMAN_LEATHER_ARMOR.getFrameList();
		}
		
	}
	
	private void setGiantNothing() {
		this.bodyAttackFrameList=NothingUnitFrameMoving.NOTHING_GIANT_ATTACK.getFrameList();
		this.armorAttackFrameList=NothingUnitFrameMoving.NOTHING_GOLDEN_ARMOR.getFrameList();
	}
	
	
	private void setNothingman(String armorName) {
		this.bodyAttackFrameList=NothingUnitFrameMoving.NOTHING_ATTACK.getFrameList();
		
		
		if(armorName.equals("iron medium armor")) {
			this.setIronMediumArmor();
			this.armorAttackFrameList=NothingUnitFrameMoving.NOTHING_IRON_ARMOR.getFrameList();
		}
		if(armorName.equals("leather light armor")) {
			this.setLeatherLightArmor();
			this.armorAttackFrameList=NothingUnitFrameMoving.NOTHING_LEATHER_ARMOR.getFrameList();
		}
	}
	
	
	
	private void setGiantGoldenSpear() {
		this.bodyAttackFrameList=SpearUnitFrameMoving.LANCER_GIANT_ATTACK.getFrameList();
		this.armorAttackFrameList=SpearUnitFrameMoving.LANCER_GOLDEN_ARMOR.getFrameList();
		this.weaponMovingFrameList=SpearUnitFrameMoving.GOLDEN_SPEAR_GIANT_MOVEMENT.getFrameList();
		this.weaponAttackFrameList=SpearUnitFrameMoving.GOLDEN_SPEAR_GIANT_ATTACK.getFrameList();
		this.weaponDyingFrameList=SpearUnitFrameMoving.GOLDEN_SPEAR_GIANT_DYING.getFrameList();
	}
	
	private void setSpearman(String armorName) {
		this.bodyAttackFrameList=SpearUnitFrameMoving.LANCER_ATTACK.getFrameList();
		this.weaponMovingFrameList=SpearUnitFrameMoving.GOLDEN_SPEAR_MOVEMENT.getFrameList();
		this.weaponAttackFrameList=SpearUnitFrameMoving.GOLDEN_SPEAR_ATTACK.getFrameList();
		this.weaponDyingFrameList=SpearUnitFrameMoving.GOLDEN_SPEAR_DYING.getFrameList();
		
		if(armorName.equals("iron medium armor")) {
			this.setIronMediumArmor();
			this.armorAttackFrameList=SpearUnitFrameMoving.LANCER_IRON_ARMOR.getFrameList();
		}
		if(armorName.equals("leather light armor")) {
			this.setLeatherLightArmor();
			this.armorAttackFrameList=SpearUnitFrameMoving.LANCER_LEATHER_ARMOR.getFrameList();
		}
		
		
	}
	
	private void initializeHeavyUnit(String weaponName) {
		
		this.bodyMovingFrameList=BasicUnitFrameMoving.HUGEBODY.getFrameList();
		this.bodyDyingFrameList=BasicUnitFrameMoving.HUGEBODY_DYING.getFrameList();
		this.armorMovingFrameList=BasicUnitFrameMoving.GOLDEN_HEAVY_ARMOR.getFrameList();
		this.armorDyingFrameList=BasicUnitFrameMoving.GOLDEN_HEAVY_DYING.getFrameList();
		
		if(weaponName.equals("short sword")) {
			this.setGiantSword();
		}
		else if(weaponName.equals("golden spear")) {
			this.setGiantGoldenSpear();
		}
		else if(weaponName.equals("nothing")) {
			
			this.setGiantNothing();
		}
		
	}
		
	public FrameUnitManager build() {
		return new FrameUnitManager(this.weaponName,this.armorName,this.bodyMovingFrameList,this.armorMovingFrameList,
									this.weaponMovingFrameList,this.bodyAttackFrameList,this.armorAttackFrameList,
									this.weaponAttackFrameList,this.bodyDyingFrameList,this.armorDyingFrameList,
									this.weaponDyingFrameList,this.boundaryHeight,this.boundaryWidth,this.height,
									this.width,this.xOffset,this.yOffset);
	}

}
