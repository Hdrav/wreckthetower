package view.sprite;


import java.util.List;


import javafx.scene.image.Image;
import view.scenecontroller.SceneControllerImpl;
import view.sprite.frameutilities.BasicUnitEnemyFrameMoving;
import view.sprite.frameutilities.BowUnitEnemyFrameMoving;
import view.sprite.frameutilities.BowUnitFrameMoving;
import view.sprite.frameutilities.NothingUnitEnemyFrameMoving;
import view.sprite.frameutilities.SpearUnitEnemyFrameMoving;
import view.sprite.frameutilities.SwordUnitEnemyFrameMoving;


public class FrameUnitEnemyBuilder extends SceneControllerImpl{
	private String armorName;
	private String weaponName;
	private List<Image> bodyMovingFrameList;
	private List<Image> armorMovingFrameList;
	private List<Image> weaponMovingFrameList;
	
	private List<Image> bodyAttackFrameList;
	private List<Image> armorAttackFrameList;
	private List<Image> weaponAttackFrameList;
	
	private List<Image> bodyChargingFrameList;
	private List<Image> armorChargingFrameList;
	private List<Image> weaponChargingFrameList;
	
	private List<Image> arrowFrameList;
	
	private List<Image> bodyDyingFrameList;
	private List<Image> armorDyingFrameList;
	private List<Image> weaponDyingFrameList;
	
	private double height;
	private double width;
	private double boundaryHeight;
	private double boundaryWidth;
	private double xOffset;
	private double yOffset;

	private double weaponBoundaryHeight;
	private double weaponBoundaryWidth;
	private double weaponXOffset;
	private double weaponYOffset;
	
	private FrameUnitEnemyBuilder(int index) {
		this.armorName=this.getController().getEnemy().getUnitTemplateList().get(index).getArmor().toString();
		this.weaponName=this.getController().getEnemy().getUnitTemplateList().get(index).getWeapon().toString();
		
		
		
		if(armorName.contains("heavy")){
			this.height=120;
			this.width=120;
			this.boundaryHeight=54;
			this.boundaryWidth=57;
			this.xOffset=33;
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
			this.bodyMovingFrameList=BasicUnitEnemyFrameMoving.BODY.getFrameList();
			this.bodyDyingFrameList=BasicUnitEnemyFrameMoving.BODY_DYING.getFrameList();
			if(weaponName.contains("sword")) {
				this.setSwordman(armorName);
			}
			else if(weaponName.contains("golden spear")) {
				
				this.setSpearman(armorName);
			}
			else if(weaponName.contains("nothing")) {
				this.setNothingman(armorName);
			}
			else if(weaponName.contains("bow")) {
				this.setArcher(armorName);
			}
		}
	}
	

	public static FrameUnitEnemyBuilder newFrameUnitBuilder(int index) {
		return new FrameUnitEnemyBuilder(index);
	}
	
	private void setIronMediumArmor() {
		this.armorMovingFrameList=BasicUnitEnemyFrameMoving.IRON_MEDIUM_ARMOR.getFrameList();
		this.armorDyingFrameList=BasicUnitEnemyFrameMoving.IRON_MEDIUM_ARMO_DYING.getFrameList();
		
	}
	
	private void setLeatherLightArmor() {
		
		this.armorMovingFrameList=BasicUnitEnemyFrameMoving.LEATHER_LIGHT_ARMOR.getFrameList();
		this.armorDyingFrameList=BasicUnitEnemyFrameMoving.LEATHER_LIGHT_ARMOR_DYING.getFrameList();
		
	}
	
	
	private void setGiantSword() {
		
		this.weaponBoundaryHeight=54;
		this.weaponBoundaryWidth=82;
		this.weaponXOffset=5;
		this.weaponYOffset=-66;
		
		this.bodyAttackFrameList=SwordUnitEnemyFrameMoving.SWORDMAN_GIANT_ATTACK.getFrameList();
		this.armorAttackFrameList=SwordUnitEnemyFrameMoving.SWORDMAN_GOLDEN_ARMOR.getFrameList();
		this.weaponMovingFrameList=SwordUnitEnemyFrameMoving.SHORT_SWORD_GIANT_MOVEMENT.getFrameList();
		this.weaponAttackFrameList=SwordUnitEnemyFrameMoving.SHORT_SWORD_GIANT_ATTACK.getFrameList();
		this.weaponDyingFrameList=SwordUnitEnemyFrameMoving.SHORT_SWORD_GIANT_DYING.getFrameList();
		
	}
	
	private void setSwordman(String armorName) {
		
		this.weaponBoundaryHeight=54;
		this.weaponBoundaryWidth=82;
		this.weaponXOffset=13;
		this.weaponYOffset=-66;
		
		this.bodyAttackFrameList=SwordUnitEnemyFrameMoving.SWORDMAN_ATTACK.getFrameList();
		this.weaponMovingFrameList=SwordUnitEnemyFrameMoving.SHORT_SWORD_MOVEMENT.getFrameList();
		this.weaponAttackFrameList=SwordUnitEnemyFrameMoving.SHORT_SWORD_ATTACK.getFrameList();
		this.weaponDyingFrameList=SwordUnitEnemyFrameMoving.SHORT_SWORD_DYING.getFrameList();
		
		
		if(armorName.equals("iron medium armor")) {
			this.setIronMediumArmor();
			this.armorAttackFrameList=SwordUnitEnemyFrameMoving.SWORDMAN_IRON_ARMOR.getFrameList();
		}
		if(armorName.equals("leather light armor")) {
			this.setLeatherLightArmor();
			this.armorAttackFrameList=SwordUnitEnemyFrameMoving.SWORDMAN_LEATHER_ARMOR.getFrameList();
		}
		
	}
	
	private void setGiantNothing() {
		this.weaponBoundaryHeight=54;
		this.weaponBoundaryWidth=100;
		this.weaponXOffset=15;
		this.weaponYOffset=-66;
		this.bodyAttackFrameList=NothingUnitEnemyFrameMoving.NOTHING_GIANT_ATTACK.getFrameList();
		this.armorAttackFrameList=NothingUnitEnemyFrameMoving.NOTHING_GOLDEN_ARMOR.getFrameList();
		
	}
	
	
	private void setNothingman(String armorName) {
		
		this.weaponBoundaryHeight=54;
		this.weaponBoundaryWidth=82;
		this.weaponXOffset=23;
		this.weaponYOffset=-66;
		this.bodyAttackFrameList=NothingUnitEnemyFrameMoving.NOTHING_ATTACK.getFrameList();
		
		if(armorName.equals("iron medium armor")) {
			this.setIronMediumArmor();
			this.armorAttackFrameList=NothingUnitEnemyFrameMoving.NOTHING_IRON_ARMOR.getFrameList();
		}
		if(armorName.equals("leather light armor")) {
			this.setLeatherLightArmor();
			this.armorAttackFrameList=NothingUnitEnemyFrameMoving.NOTHING_LEATHER_ARMOR.getFrameList();
		}
	}
	
	
	
	private void setGiantGoldenSpear() {
		this.weaponBoundaryHeight=54;
		this.weaponBoundaryWidth=100;
		this.weaponXOffset=-18;
		this.weaponYOffset=-66;
		
		
		this.bodyAttackFrameList=SpearUnitEnemyFrameMoving.LANCER_GIANT_ATTACK.getFrameList();
		this.armorAttackFrameList=SpearUnitEnemyFrameMoving.LANCER_GOLDEN_ARMOR.getFrameList();
		this.weaponMovingFrameList=SpearUnitEnemyFrameMoving.GOLDEN_SPEAR_GIANT_MOVEMENT.getFrameList();
		this.weaponAttackFrameList=SpearUnitEnemyFrameMoving.GOLDEN_SPEAR_GIANT_ATTACK.getFrameList();
		this.weaponDyingFrameList=SpearUnitEnemyFrameMoving.GOLDEN_SPEAR_GIANT_DYING.getFrameList();
	}
	
	private void setSpearman(String armorName) {
		
		this.weaponBoundaryHeight=54;
		this.weaponBoundaryWidth=100;
		this.weaponXOffset=-10;
		this.weaponYOffset=-66;
		
		
		
		this.bodyAttackFrameList=SpearUnitEnemyFrameMoving.LANCER_ATTACK.getFrameList();
		this.weaponMovingFrameList=SpearUnitEnemyFrameMoving.GOLDEN_SPEAR_MOVEMENT.getFrameList();
		this.weaponAttackFrameList=SpearUnitEnemyFrameMoving.GOLDEN_SPEAR_ATTACK.getFrameList();
		this.weaponDyingFrameList=SpearUnitEnemyFrameMoving.GOLDEN_SPEAR_DYING.getFrameList();
		
		if(armorName.equals("iron medium armor")) {
			this.setIronMediumArmor();
			this.armorAttackFrameList=SpearUnitEnemyFrameMoving.LANCER_IRON_ARMOR.getFrameList();
		}
		if(armorName.equals("leather light armor")) {
			this.setLeatherLightArmor();
			this.armorAttackFrameList=SpearUnitEnemyFrameMoving.LANCER_LEATHER_ARMOR.getFrameList();
		}
		
		
	}
	
	
	private void setArcher(String armorName) {
		this.weaponBoundaryHeight=54;
		this.weaponBoundaryWidth=180;
		this.weaponXOffset=-130;
		this.weaponYOffset=-66;
		
		this.bodyAttackFrameList=BowUnitEnemyFrameMoving.ARCHER_ATTACK.getFrameList();
		this.bodyChargingFrameList=BowUnitEnemyFrameMoving.ARCHER_CHARGING.getFrameList();
		this.weaponMovingFrameList=BowUnitEnemyFrameMoving.SHORT_BOW_MOVEMENT.getFrameList();
		this.weaponAttackFrameList=BowUnitEnemyFrameMoving.SHORT_BOW_ATTACK.getFrameList();
		this.weaponChargingFrameList=BowUnitEnemyFrameMoving.SHORT_BOW_CHARGING.getFrameList();
		this.weaponDyingFrameList=BowUnitEnemyFrameMoving.SHORT_BOW_DYING.getFrameList();
		this.arrowFrameList=BowUnitFrameMoving.SHORT_BOW_ARROW.getFrameList();
		if(armorName.equals("iron medium armor")) {
			this.setIronMediumArmor();
			this.armorAttackFrameList=BowUnitEnemyFrameMoving.ARCHER_IRON_ARMOR_ATTACK.getFrameList();
			this.armorChargingFrameList=BowUnitEnemyFrameMoving.ARCHER_IRON_ARMOR_CHARGE.getFrameList();
		}
		if(armorName.equals("leather light armor")) {
			this.setLeatherLightArmor();
			this.armorAttackFrameList=BowUnitEnemyFrameMoving.ARCHER_LEATHER_ARMOR_ATTACK.getFrameList();
			this.armorChargingFrameList=BowUnitEnemyFrameMoving.ARCHER_LEATHER_ARMOR_CHARGE.getFrameList();
		}
		
		
	}
	
	
	private void setGiantArcher() {
		
		this.weaponBoundaryHeight=54;
		this.weaponBoundaryWidth=180;
		this.weaponXOffset=-122;
		this.weaponYOffset=-66;
		
		this.bodyAttackFrameList=BowUnitEnemyFrameMoving.ARCHER_GIANT_ATTACK.getFrameList();
		this.bodyChargingFrameList=BowUnitEnemyFrameMoving.ARCHER_GIANT_CHARGING.getFrameList();
		this.weaponMovingFrameList=BowUnitEnemyFrameMoving.SHORT_BOW_GIANT_MOVEMENT.getFrameList();
		this.weaponAttackFrameList=BowUnitEnemyFrameMoving.SHORT_BOW_GIANT_ATTACK.getFrameList();
		this.weaponChargingFrameList=BowUnitEnemyFrameMoving.SHORT_BOW_GIANT_CHARGING.getFrameList();
		this.weaponDyingFrameList=BowUnitEnemyFrameMoving.SHORT_BOW_DYING.getFrameList();
		this.arrowFrameList=BowUnitFrameMoving.SHORT_BOW_ARROW.getFrameList();

		this.armorAttackFrameList=BowUnitEnemyFrameMoving.ARCHER_GOLDEN_ARMOR_ATTACK.getFrameList();
		this.armorChargingFrameList=BowUnitEnemyFrameMoving.ARCHER_GOLDEN_ARMOR_CHARGE.getFrameList();
	
	}

	
	private void initializeHeavyUnit(String weaponName) {
		
		this.bodyMovingFrameList=BasicUnitEnemyFrameMoving.HUGEBODY.getFrameList();
		this.bodyDyingFrameList=BasicUnitEnemyFrameMoving.HUGEBODY_DYING.getFrameList();
		this.armorMovingFrameList=BasicUnitEnemyFrameMoving.GOLDEN_HEAVY_ARMOR.getFrameList();
		this.armorDyingFrameList=BasicUnitEnemyFrameMoving.GOLDEN_HEAVY_DYING.getFrameList();
		
		if(weaponName.equals("short sword")) {
			this.setGiantSword();
		}
		else if(weaponName.equals("golden spear")) {
			this.setGiantGoldenSpear();
		}
		else if(weaponName.equals("nothing")) {
			
			this.setGiantNothing();
		}
		else if(weaponName.equals("short bow")) {
			this.setGiantArcher();
		}
	}
		
	public FrameUnitManager build() {
		return new FrameUnitManager(this.weaponName,this.armorName,this.bodyMovingFrameList,this.armorMovingFrameList,
									this.weaponMovingFrameList,this.bodyAttackFrameList,this.armorAttackFrameList,
									this.weaponAttackFrameList,this.bodyChargingFrameList,
									this.armorChargingFrameList,this.weaponChargingFrameList,this.arrowFrameList,
									this.bodyDyingFrameList,this.armorDyingFrameList,
									this.weaponDyingFrameList,this.boundaryHeight,this.boundaryWidth,this.height,
									this.width,this.xOffset,this.yOffset,this.weaponBoundaryHeight,this.weaponBoundaryWidth,
									this.weaponXOffset,this.weaponYOffset);

	}


	public String getArmorName() {
		return armorName;
	}


	public String getWeaponName() {
		return weaponName;
	}


	public List<Image> getBodyMovingFrameList() {
		return bodyMovingFrameList;
	}


	public List<Image> getArmorMovingFrameList() {
		return armorMovingFrameList;
	}


	public List<Image> getWeaponMovingFrameList() {
		return weaponMovingFrameList;
	}


	public List<Image> getBodyAttackFrameList() {
		return bodyAttackFrameList;
	}


	public List<Image> getArmorAttackFrameList() {
		return armorAttackFrameList;
	}


	public List<Image> getWeaponAttackFrameList() {
		return weaponAttackFrameList;
	}


	public List<Image> getBodyChargingFrameList() {
		return bodyChargingFrameList;
	}


	public List<Image> getArmorChargingFrameList() {
		return armorChargingFrameList;
	}


	public List<Image> getWeaponChargingFrameList() {
		return weaponChargingFrameList;
	}


	public List<Image> getArrowFrameList() {
		return arrowFrameList;
	}


	public List<Image> getBodyDyingFrameList() {
		return bodyDyingFrameList;
	}


	public List<Image> getArmorDyingFrameList() {
		return armorDyingFrameList;
	}


	public List<Image> getWeaponDyingFrameList() {
		return weaponDyingFrameList;
	}


	public double getHeight() {
		return height;
	}


	public double getWidth() {
		return width;
	}


	public double getBoundaryHeight() {
		return boundaryHeight;
	}


	public double getBoundaryWidth() {
		return boundaryWidth;
	}


	public double getxOffset() {
		return xOffset;
	}


	public double getyOffset() {
		return yOffset;
	}


	public double getWeaponBoundaryHeight() {
		return weaponBoundaryHeight;
	}


	public double getWeaponBoundaryWidth() {
		return weaponBoundaryWidth;
	}


	public double getWeaponXOffset() {
		return weaponXOffset;
	}


	public double getWeaponYOffset() {
		return weaponYOffset;
	}
	
}